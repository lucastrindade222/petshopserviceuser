package br.com.lucas.petshopserviceuse.resource;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminResource {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
   public ResponseEntity<Void> findAll() {
        System.out.println("teste:");
        return ResponseEntity.ok().build();
    }
}
