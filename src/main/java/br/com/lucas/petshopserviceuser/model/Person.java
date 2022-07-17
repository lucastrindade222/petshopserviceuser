package br.com.lucas.petshopserviceuser.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person extends User {

    private String name;
    private String lastname;
    private Fone fone;
    private String Address;
   

    

}
