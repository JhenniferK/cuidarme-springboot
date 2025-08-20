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

    @Autowired
    private PacienteMapper pacienteMapper;

    public Atendimento from(AtendimentoSalvarRequestDTO from) {
        var pacienteId = from.getPaciente().getLookupId();
        var psicologoId = from.getPsicologo().getLookupId();

        Paciente paciente = pacienteService.buscarPor(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente com lookupId " + pacienteId + " não encontrado."));

        Psicologo psicologo = psicologoService.buscarPor(psicologoId)
                .orElseThrow(() -> new IllegalArgumentException("Psicólogo com lookupId " + psicologoId + " não encontrado."));

        Atendimento atendimento = new Atendimento();
        atendimento.setData(from.getData());
        atendimento.setLocalidade(from.getLocalidade());
        atendimento.setStatus(from.getStatus());
        atendimento.setPaciente(paciente);
        atendimento.setPsicologo(psicologo);

        return atendimento;
    }

    public AtendimentoResponseDTO from(Atendimento from) {
        AtendimentoResponseDTO atendimentoResponseDTO = new AtendimentoResponseDTO();
        atendimentoResponseDTO.setLookupId(from.getLookupId());
        atendimentoResponseDTO.setData(from.getData());
        atendimentoResponseDTO.setLocalidade(from.getLocalidade());
        atendimentoResponseDTO.setStatus(from.getStatus());

        if (from.getPaciente() != null) {
            atendimentoResponseDTO.setPaciente(pacienteMapper.from(from.getPaciente()));
        }

        return atendimentoResponseDTO;
    }

}