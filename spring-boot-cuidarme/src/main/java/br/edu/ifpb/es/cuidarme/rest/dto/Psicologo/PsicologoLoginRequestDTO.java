package br.edu.ifpb.es.cuidarme.rest.dto.Psicologo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PsicologoLoginRequestDTO {

    private String email;
    private String senha;

}
