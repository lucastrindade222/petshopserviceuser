package br.com.lucas.petshopserviceuse.config;

import br.com.lucas.petshopserviceuse.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

import static java.lang.System.out;

@Configuration
public class ConfigProjectconfig {

    @Autowired
    private DBService dbService;

    @Bean
    public void instantiateDatabase() throws ParseException {
        dbService.instantiateDatabase();

    }

}
