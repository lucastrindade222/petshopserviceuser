package br.com.lucas.petshopserviceuser.model;

import br.com.lucas.petshopserviceuser.model.enums.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String zipCode;
    private String city;
    private StateEnum State;
    private String district;
    private String street;
    private String number;
    private String complement;
}
