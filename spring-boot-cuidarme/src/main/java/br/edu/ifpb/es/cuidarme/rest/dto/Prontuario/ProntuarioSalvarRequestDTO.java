package br.edu.ifpb.es.cuidarme.rest.dto.Prontuario;

import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteIdDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoIdDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProntuarioSalvarRequestDTO {

    @NotBlank
    private String descricao;
    @NotNull
    private LocalDateTime dataRegistro;
    @NotNull
    @Valid
    private PsicologoIdDTO psicologo;
    @NotNull
    @Valid
    private PacienteIdDTO paciente;
}