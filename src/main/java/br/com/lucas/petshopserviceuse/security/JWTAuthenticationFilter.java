package br.com.lucas.petshopserviceuse.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.lucas.petshopserviceuse.dto.CredentialDTO;
import br.com.lucas.petshopserviceuse.dto.TokenDTO;
import br.com.lucas.petshopserviceuse.security.exception.JWTAuthenticationFailure;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static br.com.lucas.petshopserviceuse.security.SecurityConstants.HEADER_STRING;
import static br.com.lucas.petshopserviceuse.security.SecurityConstants.TOKEN_PREXI;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;



    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {

            CredentialDTO cre = new ObjectMapper().readValue(request.getInputStream(), CredentialDTO.class);

            return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    cre.getEmail(), cre.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String email = ((UserSS) authResult.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> profile = ((UserSS) authResult.getPrincipal()).getAuthorities();
        String id = ((UserSS) authResult.getPrincipal()).getId();
        String token = generateToken(email, id, profile);
        response.setStatus(200);
        response.setHeader(HEADER_STRING,token);

    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {

            var gson =new  Gson();
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(gson.toJson(new JWTAuthenticationFailure()));
        }

    }

    public String generateToken(String email, String id, Collection<? extends GrantedAuthority> profile) {

        String token = Jwts.builder().setSubject(email)
                .claim("id", id)
                .claim("email", email)
                .claim("role", profile)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();



        return TOKEN_PREXI+token;

    }

}
