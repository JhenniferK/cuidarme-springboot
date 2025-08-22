package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.Paciente;
import br.edu.ifpb.es.cuidarme.model.Prontuario;
import br.edu.ifpb.es.cuidarme.model.Psicologo;
import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteIdDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoIdDTO;
import br.edu.ifpb.es.cuidarme.service.PacienteService;
import br.edu.ifpb.es.cuidarme.service.PsicologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProntuarioMapper {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PsicologoService psicologoService;

    public Prontuario from(ProntuarioSalvarRequestDTO from) {
        var pacienteId = from.getPaciente().getLookupId();

        Paciente paciente = pacienteService.buscarPor(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente com lookupId " + pacienteId + " não encontrado."));

        Prontuario prontuario = new Prontuario();
        prontuario.setDescricao(from.getDescricao());
        prontuario.setDataRegistro(LocalDateTime.from(from.getDataRegistro()));
        prontuario.setPaciente(paciente);
        return prontuario;
    }

    public ProntuarioResponseDTO from(Prontuario from) {
        ProntuarioResponseDTO prontuario = new ProntuarioResponseDTO();
        prontuario.setLookupId(from.getLookupId());
        prontuario.setDescricao(from.getDescricao());
        prontuario.setDataRegistro(LocalDateTime.from(from.getDataRegistro()));

        if (from.getPaciente() != null) {
            Paciente paciente = from.getPaciente();
            PacienteIdDTO pacienteIdDTO = new PacienteIdDTO();
            pacienteIdDTO.setLookupId(paciente.getLookupId());
            prontuario.setPacienteIdDTO(pacienteIdDTO);
        }

        if (from.getPsicologo() != null) {
            Psicologo psicologo = from.getPsicologo();
            PsicologoIdDTO psicologoIdDTO = new PsicologoIdDTO();
            psicologoIdDTO.setLookupId(psicologo.getLookupId());
        }

        return prontuario;
    }

}