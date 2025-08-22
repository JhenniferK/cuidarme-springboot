package br.edu.ifpb.es.cuidarme.rest.dto.Prontuario;

import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteIdDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProntuarioResponseDTO {

    private UUID lookupId;
    private String descricao;
    private LocalDateTime dataRegistro;
    @Getter
    @Setter
    private PacienteIdDTO pacienteIdDTO;

}