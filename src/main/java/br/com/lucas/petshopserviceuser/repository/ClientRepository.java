package br.com.lucas.petshopserviceuser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucas.petshopserviceuser.model.Client;

 
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {



}
