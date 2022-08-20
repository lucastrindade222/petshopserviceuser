package br.com.lucas.petshopserviceuse.service;

import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.model.Role;

public interface RoleService {

    public abstract Role findId(String id);

    public abstract Role findByName(String name);

}
