package br.com.lucas.petshopserviceuse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "role")
@Data
public class Role implements GrantedAuthority {


    @Id
    private String nameRole;

    @JsonIgnore
    private List<User> user = new ArrayList<>();

    public Role(String nameRole) {
        this.nameRole = nameRole;
    }

    @Override
    public String getAuthority() {

        return this.nameRole;
    }

    public void addUser(User user) {
        this.user.add(user);
    }

}

