package br.edu.ifpb.es.cuidarme.rest.dto.Prontuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProntuarioSalvarRequestDTO {

    private Long id;
    @NotBlank
    private String descricao;
    @NotBlank
    private LocalDateTime dataRegistro;

}