package br.com.lucas.petshopserviceuse.utils;

import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.model.User;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;


public class UTILS {

    public Client encryptPassword(Client client) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!client.getPassword().equals(null)){
            var encryptPassword = encoder.encode(client.getPassword());
            client.setPassword(encryptPassword);
        }

        return client;
    }

    public static UTILS now(){
        return  new UTILS();
    }

}
