package br.com.lucas.petshopserviceuse.model;

import br.com.lucas.petshopserviceuse.model.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Document
@ToString
public class Admin extends User {

    public Admin( String email, String password, String name, String avatarUrl) {
        super( email, password, name, avatarUrl);
    }
}
