package br.com.lucas.petshopserviceuse.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.petshopserviceuse.domain.Client;
import br.com.lucas.petshopserviceuse.repository.ClientRepositoy;
import br.com.lucas.petshopserviceuse.service.ClientService;
import br.com.lucas.petshopserviceuse.service.exception.DatabaseException;
import br.com.lucas.petshopserviceuse.service.exception.ObjectNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepositoy repo;

    @Override
    public List<Client> findAll() {

        return repo.findAll();
    }

    @Override
    public Client findId(String id) {
        Optional<Client> client = repo.findById(id);
        return client.orElseThrow(
                () -> new ObjectNotFoundException("Cliente não encontrado:" + id + ", Tipo" + Client.class.getName()));
    }

    @Override
    public Client save(Client client) {

        
        return repo.save(client);
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

}
