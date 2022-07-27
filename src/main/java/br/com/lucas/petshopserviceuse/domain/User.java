package br.com.lucas.petshopserviceuse.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    


    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String name;
    private String avatarUrl;


    public User(String email,String password, String name,String avatarUrl){

        this.email = email;
        this.password = password;
        this.name = name;
        this.avatarUrl = avatarUrl;

    }


}
