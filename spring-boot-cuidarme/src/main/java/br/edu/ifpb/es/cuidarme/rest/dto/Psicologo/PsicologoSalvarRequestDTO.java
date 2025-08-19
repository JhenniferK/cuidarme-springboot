package br.edu.ifpb.es.cuidarme.rest.dto.Psicologo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PsicologoSalvarRequestDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

}