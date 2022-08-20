package br.com.lucas.petshopserviceuse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lucas.petshopserviceuse.model.Client;

import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoy extends MongoRepository<Client, String> {
    Client findByEmail(String email);
}
