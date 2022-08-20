package br.com.lucas.petshopserviceuse.utils;

import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.model.User;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;


public class UTILS {

    public User encryptPassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        var encryptPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptPassword);
        return user;
    }

    public static UTILS now(){
        return  new UTILS();
    }

}
