package br.com.lucas.petshopserviceuse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientmodelNew {
    
    private String name;
    private String familyName;
    private String password;
    private String email;



}
