package br.com.lucas.petshopserviceuse.resource;

import java.util.List;

import br.com.lucas.petshopserviceuse.service.impl.ClientServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.dto.ClientmodelNew;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "client")
public class ClientResource {

    @Autowired
    private ClientServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {

        List<Client> all = service.findAll();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable String id) {

        Client client = service.findId(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    public ResponseEntity<Client> save(@Validated @RequestBody ClientmodelNew clientModelNew) {
        System.out.println("teste");
        Client client = modelMapper.map(clientModelNew, Client.class);
        client = service.save(client);
        return ResponseEntity.status(CREATED).body(client);
    }

}
