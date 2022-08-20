package br.com.lucas.petshopserviceuse.repository;

import br.com.lucas.petshopserviceuse.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Admin extends MongoRepository<Admin, String> {
    Admin  findByEmail(String email);
}
