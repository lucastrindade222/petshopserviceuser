package br.com.lucas.petshopserviceuse.security;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import br.com.lucas.petshopserviceuse.dto.TokenDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JWTUtil {



    public String generateToken(String email, UUID id, Collection<? extends GrantedAuthority> profile) {

        String token = Jwts.builder().setSubject(email)
                .claim("id", id.toString())
                .claim("email", email)
                .claim("profile", profile)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();

        Gson gson = new Gson();

        var tokemFinal = new TokenDTO(token);

        return gson.toJson(tokemFinal);

    }

    public String getEmailDoUsuario(String token) {
        if (token != null && token.startsWith("Bearer ")) {

            Claims claims = getClaims(token.substring(7));
            if (claims != null) {

                return claims.get("email").toString();
            }
        }
        return null;
    }

    public String getIdDoUsuario(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {

            return claims.get("id").toString();
        }
        return null;
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }



    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("Authorization").replace("Bearer ", "");
    }

}
