package br.edu.ifpb.es.cuidarme.rest.dto.Atendimento;

import br.edu.ifpb.es.cuidarme.model.StatusAtendimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtendimentoSalvarRequestDTO {

    @NotNull
    private LocalDateTime data;
    @NotBlank
    private String localidade;
    @NotNull
    private StatusAtendimento status;

}