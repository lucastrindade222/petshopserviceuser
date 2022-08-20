package br.com.lucas.petshopserviceuse.resource;

import java.util.List;

import br.com.lucas.petshopserviceuse.service.impl.ClientServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.dto.ClientmodelNew;
import br.com.lucas.petshopserviceuse.service.ClientService;

@RestController
@RequestMapping(value = "client")
public class ClientResource {

    @Autowired
    private ClientServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("ROLE_CLIENTE")
    public ResponseEntity<List<Client>> findAll() {

        List<Client> all = service.findAll();
        return ResponseEntity.ok().body(all);
    }

    @PostMapping
    public ResponseEntity<Client> save(@Validated @RequestBody ClientmodelNew clientModelNew) {

        Client client = modelMapper.map(clientModelNew, Client.class);
        client = service.save(client);
        return ResponseEntity.ok().body(client);
    }

}
