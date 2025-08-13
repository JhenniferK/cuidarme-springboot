package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.Atendimento;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AtendimentoMapper {

    public Atendimento from(AtendimentoSalvarRequestDTO from) {
        Atendimento atendimento = new Atendimento();
        atendimento.setData(from.getData());
        atendimento.setLocalidade(from.getLocalidade());
        atendimento.setStatusAtendimento(from.getStatus());
        return atendimento;
    }

    public AtendimentoResponseDTO from(Atendimento from) {
        AtendimentoResponseDTO atendimentoResponseDTO = new AtendimentoResponseDTO();
        atendimentoResponseDTO.setData(atendimentoResponseDTO.getData());
        atendimentoResponseDTO.setLocalidade(atendimentoResponseDTO.getLocalidade());
        atendimentoResponseDTO.setStatus(atendimentoResponseDTO.getStatus());
        return atendimentoResponseDTO;
    }

}