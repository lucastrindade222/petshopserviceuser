package br.com.lucas.petshopserviceuse.service.impl;

import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.model.Role;
import br.com.lucas.petshopserviceuse.repository.RoleRepositoy;
import br.com.lucas.petshopserviceuse.service.RoleService;
import br.com.lucas.petshopserviceuse.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ROleServiceImpl implements RoleService {

    @Autowired
    private RoleRepositoy repo;

    @Override
    public Role findId(String id) {
        Optional<Role> role = repo.findById(id);

        return  role.orElseThrow(()-> new ObjectNotFoundException("Role não encontrado:" + id ));
    }
}
