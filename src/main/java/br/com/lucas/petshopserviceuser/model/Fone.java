package br.com.lucas.petshopserviceuser.model;

 import br.com.lucas.petshopserviceuser.model.enums.TypeFone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fone {

    private String ddd;
    private String numero;
    private TypeFone tipoTelefone;

}
