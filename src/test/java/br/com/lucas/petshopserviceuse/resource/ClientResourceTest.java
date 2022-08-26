package br.com.lucas.petshopserviceuse.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import br.com.lucas.petshopserviceuse.config.AplicationConfingTest;
import br.com.lucas.petshopserviceuse.dto.ClientmodelNew;
import br.com.lucas.petshopserviceuse.model.Client;
import br.com.lucas.petshopserviceuse.model.Role;
import br.com.lucas.petshopserviceuse.enums.RoleName;
import br.com.lucas.petshopserviceuse.repository.ClientRepositoy;
import br.com.lucas.petshopserviceuse.repository.RoleRepositoy;
import br.com.lucas.petshopserviceuse.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

@DisplayName("ClientResource")
public class ClientResourceTest extends AplicationConfingTest {
    @Value("${api.profiles.images.profile}")
    private String url;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ClientRepositoy clientRepositoy;
    @MockBean
    private RoleRepositoy roleRepositoy;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testSalva() throws JsonProcessingException, Exception {

        var cliente = new ClientmodelNew("Lucas","Teste","123456","lucasDev@gmail.com");


        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
    @Test
    public void testListAll() throws JsonProcessingException, Exception {



        mockMvc.perform(get("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testById() throws JsonProcessingException, Exception {

        mockMvc.perform(get("/client/6308de19abf8881f154d2025")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @BeforeEach
    public void setup() {
        var cliente = new Client(  "lucas@email.com", "password123","nome",url);
        cliente.setId("6308de19abf8881f154d2025");
        var listClient = List.of(cliente);

        Mockito.when(clientRepositoy.findById(cliente.getId())).thenReturn(Optional.of(cliente));
        Mockito.when(clientRepositoy.findAll()).thenReturn(listClient);

        var role = new Role(RoleName.ROLE_CLIENT.name());
        Mockito.when(roleRepositoy.findById(RoleName.ROLE_CLIENT.name())).thenReturn(Optional.of(role));



    }

}
