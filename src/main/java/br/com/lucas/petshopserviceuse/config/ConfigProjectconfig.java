package br.com.lucas.petshopserviceuse.config;

import br.com.lucas.petshopserviceuse.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;



@Configuration
@Profile("dev")
public class ConfigProjectconfig {

    @Autowired
    private DBService dbService;
    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public void instantiateDatabase() throws ParseException {

        dbService.instantiateDatabase();

    }

}
