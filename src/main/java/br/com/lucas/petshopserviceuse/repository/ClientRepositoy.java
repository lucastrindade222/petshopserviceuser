package br.com.lucas.petshopserviceuse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lucas.petshopserviceuse.domain.Client;

public interface ClientRepositoy extends MongoRepository<Client,String> {
    
}
