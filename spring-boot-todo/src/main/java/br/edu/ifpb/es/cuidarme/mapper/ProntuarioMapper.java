package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.Prontuario;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioSalvarRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProntuarioMapper {

    public Prontuario from(ProntuarioSalvarRequestDTO from) {
        Prontuario prontuario = new Prontuario();
        prontuario.setDescricao(from.getDescricao());
        prontuario.setDataRegistro(LocalDateTime.from(from.getDataRegistro()));
        return prontuario;
    }

    public ProntuarioResponseDTO from(Prontuario from) {
        ProntuarioResponseDTO prontuario = new ProntuarioResponseDTO();
        prontuario.setDescricao(from.getDescricao());
        prontuario.setDataRegistro(LocalDateTime.from(from.getDataRegistro()));
        return prontuario;
    }

}