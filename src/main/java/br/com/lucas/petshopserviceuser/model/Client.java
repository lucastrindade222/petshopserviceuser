package br.com.lucas.petshopserviceuser.model;

import javax.persistence.Entity;

 

@Entity
public class Client extends Person {


    public Client() {
    }
    public Client(String name, String lastname, Fone fone, String Address) {
        super(name, lastname, fone, Address);
    }

  


    
}
