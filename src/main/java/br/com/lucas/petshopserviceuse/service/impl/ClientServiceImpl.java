package br.com.lucas.petshopserviceuse.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.lucas.petshopserviceuse.repository.RoleRepositoy;
import br.com.lucas.petshopserviceuse.repository.UserRepositoy;
import br.com.lucas.petshopserviceuse.utils.JWTUtil;
import br.com.lucas.petshopserviceuse.service.RoleService;
import br.com.lucas.petshopserviceuse.service.exception.UniqueFieldException;
import br.com.lucas.petshopserviceuse.utils.UTILS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.repository.ClientRepositoy;
import br.com.lucas.petshopserviceuse.service.ClientService;
import br.com.lucas.petshopserviceuse.service.exception.DatabaseException;
import br.com.lucas.petshopserviceuse.service.exception.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;

import static br.com.lucas.petshopserviceuse.model.enumDomain.Profile.ROLE_CLIENTE;

@Service
public class ClientServiceImpl implements ClientService {





    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RoleRepositoy roleRepositoy;
    @Autowired
    private ClientRepositoy repo;
    @Autowired
    private UserRepositoy usaUserRepositoy;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Override
    public List<Client> findAll() {
        return repo.findAll();
    }

    @Override
    public Client findId(String id) {
        Optional<Client> client = repo.findById(id);
        return client.orElseThrow(
                () -> new ObjectNotFoundException("Cliente não encontrado:" + id ));
    }

    @Override
    public Client findByMySelf() {
        String idUsuario = jwtUtil.getIdDoUsuario(jwtUtil.getTokenFromRequest(request));
        return this.findId(idUsuario);
    }

    @Override
    public Client save(Client client) {
    this.checkEmailExists(client.getEmail());


            try {
                client =(Client) UTILS.now().encryptPassword(client);
                var role = roleService.findId(ROLE_CLIENTE.name());
                client.addRole(role);
                repo.save(client);

                return  usaUserRepositoy.save(client);
            }catch (Exception e){
             throw new DatabaseException(e.getMessage());

            }



    }

    @Override
    public Client update(Client clientNew) {

        Client dataBase = this.findId(clientNew.getId());

        return repo.save(dataBase);
    }

    @Override
    public void delete(String id) {

        try {
            this.findId(id);
            repo.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    @Override
    public void from(Client clientNew, Client dataBase) {
 

  
  
    }

    @Override
    public void checkEmailExists(String email){
        var client = repo.findByEmail(email);

        if (client != null) {
            throw new UniqueFieldException("O e-mail já existe no sistema.");
        }

    }




}
