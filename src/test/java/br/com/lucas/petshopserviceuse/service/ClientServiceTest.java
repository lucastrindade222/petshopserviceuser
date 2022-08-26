package br.com.lucas.petshopserviceuse.service;

import br.com.lucas.petshopserviceuse.config.AplicationConfingTest;
import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.model.Role;
import br.com.lucas.petshopserviceuse.enums.RoleName;
import br.com.lucas.petshopserviceuse.repository.ClientRepositoy;
import br.com.lucas.petshopserviceuse.repository.RoleRepositoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;


@DisplayName("ClientServiceImpl")
public class ClientServiceTest extends AplicationConfingTest {

    @Value("${api.profiles.images.profile}")
    private String url;

    @MockBean
    private ClientRepositoy clientRepositoy;
    @MockBean
    private  RoleRepositoy roleRepositoy;
    @Autowired
    private ClientService clientService;

    @Test
    public void checkEmailExistsTest(){


        var cliente = new Client(  "lucas@email.com", "password123","nome",url);

        try {
            clientService.checkEmailExists(cliente.getEmail());
            Assertions.fail();
        }catch (Exception e){
            Assertions.assertEquals(e.getMessage(),"O e-mail já existe no sistema.");
        }


    }

    @Test
    public void save(){
        var cliente = new Client(  "lucasDevJava@email.com", "password123","nome",url);
       cliente = clientService.save(cliente);

       Assertions.assertNotNull(cliente.getId(),"Id nulo");

    }


    @BeforeEach
    public void setup() {
        var cliente = new Client(  "lucas@email.com", "password123","nome",url);
        Mockito.when(clientRepositoy.findByEmail(cliente.getEmail())).thenReturn(cliente);

        var role = new Role(RoleName.ROLE_CLIENT.name());
        Mockito.when(roleRepositoy.findById(RoleName.ROLE_CLIENT.name())).thenReturn(Optional.of(role));

    }


}
