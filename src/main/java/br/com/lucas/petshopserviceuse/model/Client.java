package br.com.lucas.petshopserviceuse.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "client")
public class Client extends Person {

    private long purchases;

    public Client(String email, String password, String name, String avatarUrl) {
        super(email, password, name, avatarUrl);
    }

    

}