package br.edu.ifpb.es.cuidarme.rest.dto.Atendimento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AtendimentoRemarcarDTO {

    @NotNull
    @Future
    private LocalDateTime data;

    @NotBlank
    private String localidade;
}