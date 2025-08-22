package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.ContatoEmergencia;
import br.edu.ifpb.es.cuidarme.rest.dto.ContatoEmergencia.ContatoEmergenciaDTO;
import org.springframework.stereotype.Component;

@Component
public class ContatoEmergenciaMapper {
    public ContatoEmergencia from (ContatoEmergenciaDTO from){
        ContatoEmergencia contatoEmergencia = new ContatoEmergencia();
        contatoEmergencia.setNome(from.getNome());
        contatoEmergencia.setParentesco(from.getParentesco());
        contatoEmergencia.setTelefone(from.getTelefone());
        return contatoEmergencia;
    }
    public ContatoEmergenciaDTO from (ContatoEmergencia from) {
        ContatoEmergenciaDTO contatoEmergenciaDTO = new ContatoEmergenciaDTO();
        contatoEmergenciaDTO.setNome(from.getNome());
        contatoEmergenciaDTO.setParentesco(from.getParentesco());
        contatoEmergenciaDTO.setTelefone(from.getTelefone());
        return contatoEmergenciaDTO;
    }
}
