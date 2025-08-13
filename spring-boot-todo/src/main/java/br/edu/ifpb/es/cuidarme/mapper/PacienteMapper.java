package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.Paciente;
import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    public Paciente from(PacienteSalvarRequestDTO from) {
        Paciente paciente = new Paciente();
        paciente.setNome(from.getNome());
        paciente.setCpf(from.getCpf());
        paciente.setRg(from.getRg());
        paciente.setDataNascimento(from.getDataNascimento());
        paciente.setSexo(from.getSexo());
        paciente.setEstadoCivil(from.getEstadoCivil());
        paciente.setGrauInstrucao(from.getGrauInstrucao());
        paciente.setProfissao(from.getProfissao());
        paciente.setTelefonePessoal(from.getTelefonePessoal());
        paciente.setInfoAdicionais(from.getInfoAdicionais());
        return paciente;
    }

    public PacienteResponseDTO from(Paciente from) {
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();
        pacienteResponseDTO.setNome(from.getNome());
        pacienteResponseDTO.setCpf(from.getCpf());
        pacienteResponseDTO.setRg(from.getRg());
        pacienteResponseDTO.setDataNascimento(from.getDataNascimento());
        pacienteResponseDTO.setSexo(from.getSexo());
        pacienteResponseDTO.setEstadoCivil(from.getEstadoCivil());
        pacienteResponseDTO.setGrauInstrucao(from.getGrauInstrucao());
        pacienteResponseDTO.setProfissao(from.getProfissao());
        pacienteResponseDTO.setTelefonePessoal(from.getTelefonePessoal());
        pacienteResponseDTO.setInfoAdicionais(from.getInfoAdicionais());
        return pacienteResponseDTO;
    }

}