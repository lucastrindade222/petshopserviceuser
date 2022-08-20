package br.com.lucas.petshopserviceuse.repository;

import br.com.lucas.petshopserviceuse.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositoy extends MongoRepository<Admin, String> {

    Admin findByEmail(String email);
}
