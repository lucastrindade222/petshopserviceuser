package br.com.lucas.petshopserviceuse.service;

import java.util.List;

import br.com.lucas.petshopserviceuse.domain.Client;

public interface ClientService {

    public abstract List<Client> findAll();

    public abstract Client findId(String id);

    public abstract Client save(Client client);

    public abstract Client update(Client client);

    public abstract void delete(String id);

    public abstract void from(Client clientNew, Client dataBase);

}
