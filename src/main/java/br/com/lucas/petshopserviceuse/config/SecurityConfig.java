package br.com.lucas.petshopserviceuse.config;

import java.util.Arrays;
import java.util.List;

import br.com.lucas.petshopserviceuse.security.JWTAuthenticationFilter;
import br.com.lucas.petshopserviceuse.security.JWTAuthorizationFilter;
import br.com.lucas.petshopserviceuse.security.JWTUtil;
import br.com.lucas.petshopserviceuse.service.impl.UserDetailsServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



import org.springframework.security.config.http.SessionCreationPolicy;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity {

    private final   UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jWTUtil;
    @Autowired
    public WebSecurity(@Qualifier("userService") UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, JWTUtil jWTUtil){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
        this.jWTUtil = jWTUtil;
    }


    private static final String[] PUBLIC_MATCHERS = { "/public/**"};
    private static final String[] PUBLIC_MATCHERS_GET = { "/usuario/**","/api/produto/cardapio/**"};
    private static final String[] PUBLIC_MATCHERS_POST = { "/usuario/**","api/empresa","/auth/forgot/**",
            "https://sandbox.pagseguro.uol.com.br" };









    @Bean
    public SecurityFilterChain filter( HttpSecurity http) throws Exception {



        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        // Get AuthenticationManager
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();


//        http
//                .cors().and()
//                .csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST)
//                .permitAll()
//                .anyRequest().authenticated().and()
//
//                .addFilter(new JWTAuthenticationFilter(authenticationManager))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager, jWTUtil,
//                        userDetailsService))
//                .authenticationManager(authenticationManager)
//
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.headers().frameOptions().disable();



        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).hasAnyRole("CLIENTE", "FUNCIONARIO", "PROPRIETARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN").and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager))
                .addFilter(new JWTAuthorizationFilter(authenticationManager, jWTUtil,
                        userDetailsService))
                .authenticationManager(authenticationManager);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }







}
