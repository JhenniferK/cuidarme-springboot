package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.Atendimento;
import br.edu.ifpb.es.cuidarme.model.Paciente;
import br.edu.ifpb.es.cuidarme.model.Psicologo;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.service.PacienteService;
import br.edu.ifpb.es.cuidarme.service.PsicologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtendimentoMapper {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PsicologoService psicologoService;

    public Atendimento from(AtendimentoSalvarRequestDTO from) {
        Paciente paciente = pacienteService.buscarPor(from.getPaciente().getLookupId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente com lookupId " + from.getPaciente().getLookupId() + " não foi encontrado!"));

        Psicologo psicologo = psicologoService.buscarPor(from.getPsicologo().getLookupId())
                .orElseThrow(() -> new IllegalArgumentException("Psicólogo com lookupId " + from.getPsicologo().getLookupId() + " não foi encontrado!"));

        Atendimento atendimento = new Atendimento();
        atendimento.setId(from.getId());
        atendimento.setData(from.getData());
        atendimento.setTipo(from.getTipo());
        atendimento.setLocalidade(from.getLocalidade());
        atendimento.setStatus(from.getStatus());
        return atendimento;
    }

    public AtendimentoResponseDTO from(Atendimento from) {
        AtendimentoResponseDTO atendimentoResponseDTO = new AtendimentoResponseDTO();
        atendimentoResponseDTO.setLookupId(from.getLookupId());
        atendimentoResponseDTO.setData(from.getData());
        atendimentoResponseDTO.setTipo(from.getTipo());
        atendimentoResponseDTO.setLocalidade(from.getLocalidade());
        atendimentoResponseDTO.setStatus(from.getStatus());
        return atendimentoResponseDTO;
    }

}