package br.edu.ifpb.es.cuidarme.rest.dto.Atendimento;

import br.edu.ifpb.es.cuidarme.model.StatusAtendimento;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AtendimentoResponseDTO {

    private Long id;
    private LocalDateTime data;
    private String localidade;
    private StatusAtendimento status;

}