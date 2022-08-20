package br.com.lucas.petshopserviceuse.repository;


import br.com.lucas.petshopserviceuse.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoy extends MongoRepository<User, String> {
    User findByEmail(String email);
}
