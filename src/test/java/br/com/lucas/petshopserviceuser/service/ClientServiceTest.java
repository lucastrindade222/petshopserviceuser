package br.com.lucas.petshopserviceuser.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.lucas.petshopserviceuser.model.Client;
import br.com.lucas.petshopserviceuser.repository.ClientRepository;

 
 
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    ClientService service;
    @MockBean
    ClientRepository repo;

    
    @BeforeAll
    public void befor() {

        Client client = new Client("lucas", "trindade", null, null);
        Mockito.when(repo.findById(1)).thenReturn(java.util.Optional.of(client));

    }

    @Test
    public void testSavel() {

        Client client = new Client();

        client = service.salve(client);

        assertNotNull(client);

    }

    @Test
    public void testFindById() {

        Client client = service.findById(1);

        assertNotNull(client);
    }

}
