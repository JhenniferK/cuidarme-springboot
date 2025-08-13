package br.edu.ifpb.es.cuidarme.rest.dto.Atendimento;

import br.edu.ifpb.es.cuidarme.model.StatusAtendimento;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtendimentoSalvarRequestDTO {

    @NotBlank
    private LocalDateTime data;
    @NotBlank
    private String localidade;
    @NotBlank
    private StatusAtendimento status;

}