package br.com.lucas.petshopserviceuse.repository;

import br.com.lucas.petshopserviceuse.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoy extends MongoRepository<Role, String> {


    Role findByNameRole(String toString);
}
