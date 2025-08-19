package br.edu.ifpb.es.cuidarme.rest.dto.Atendimento;

import br.edu.ifpb.es.cuidarme.model.Paciente;
import br.edu.ifpb.es.cuidarme.model.Psicologo;
import br.edu.ifpb.es.cuidarme.model.StatusAtendimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtendimentoSalvarRequestDTO {

    private Long id;
    @NotNull
    private LocalDateTime data;
    @NotBlank
    private String tipo;
    @NotBlank
    private String localidade;
    @NotNull
    private StatusAtendimento status;
    @NotNull
    private Psicologo psicologo;
    @NotNull
    private Paciente paciente;

}