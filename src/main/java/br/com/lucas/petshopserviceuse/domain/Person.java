package br.com.lucas.petshopserviceuse.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends User {

    private String familyName;
    private Date birth;
    private Date dateRegister;


    public Person(String id, String email, String password, String avatarUrl, String name, String familyName,
            Date birth, Date dateRegister) {
        super(id, email, password, avatarUrl,name);
        this.familyName = familyName;
        this.birth = birth;
        this.dateRegister = dateRegister;
    }


    public Person(String email, String password, String name, String avatarUrl) {
        super(email, password, name, avatarUrl);
    }

    



}
