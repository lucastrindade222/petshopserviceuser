package br.com.lucas.petshopserviceuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class PetshopserviceuseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetshopserviceuseApplication.class, args);
		System.out.println("Nada acontece duas vezes da mesma maneira.");
	}




}
