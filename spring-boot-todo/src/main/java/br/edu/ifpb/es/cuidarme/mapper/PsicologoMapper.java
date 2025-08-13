package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.Psicologo;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PsicologoMapper {

    public Psicologo from(PsicologoSalvarRequestDTO from) {
        Psicologo psicologo = new Psicologo();
        psicologo.setNome(from.getNome());
        psicologo.setEmail(from.getEmail());
        psicologo.setSenha(from.getSenha());
        return psicologo;
    }

    public PsicologoResponseDTO from(Psicologo from) {
        PsicologoResponseDTO responseDTO = new PsicologoResponseDTO();
        responseDTO.setNome(from.getNome());
        responseDTO.setEmail(from.getEmail());
        responseDTO.setSenha(from.getSenha());
        return responseDTO;
    }

}