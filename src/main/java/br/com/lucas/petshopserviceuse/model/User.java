package br.com.lucas.petshopserviceuse.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "user")
public abstract class User implements Serializable {

    @Id
    private UUID id;
    @Indexed(unique = true)
    @Email(message = "O campo email não pode estar vazio.")
    private String email;
    @JsonIgnore
    private String password;
    private String name;
    private String avatarUrl;

    private Set<Role> roles = new HashSet<>();


    public User(String email,String password, String name,String avatarUrl){

        this.email = email;
        this.password = password;
        this.name = name;
        this.avatarUrl = avatarUrl;

    }

    public User(UUID id,String email,String password, String name,String avatarUrl){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.avatarUrl = avatarUrl;

    }
    public void addRole(Role role) {
        this.roles.add(role);
    }


    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    @JsonIgnore
    public void setRoles(Set<Role> role) {
        this.roles = role;
    }

}
