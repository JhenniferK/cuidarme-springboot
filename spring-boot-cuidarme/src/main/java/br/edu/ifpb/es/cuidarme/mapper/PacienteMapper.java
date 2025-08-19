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
        paciente.setTelefone(from.getTelefone());
        paciente.setEnderecoPessoal(from.getEnderecoPessoal());
        paciente.setEnderecoTrabalho(from.getEnderecoTrabalho());
        paciente.setInfoAdicionais(from.getInfoAdicionais());
        paciente.setContatoEmergencia(from.getContatoEmergencia());
        paciente.setPsicologo(from.getPsicologo());
        return paciente;
    }

    public PacienteResponseDTO from(Paciente from) {
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();
        pacienteResponseDTO.setLookupId(from.getLookupId());
        pacienteResponseDTO.setNome(from.getNome());
        pacienteResponseDTO.setCpf(from.getCpf());
        pacienteResponseDTO.setRg(from.getRg());
        pacienteResponseDTO.setDataNascimento(from.getDataNascimento());
        pacienteResponseDTO.setSexo(from.getSexo());
        pacienteResponseDTO.setEstadoCivil(from.getEstadoCivil());
        pacienteResponseDTO.setGrauInstrucao(from.getGrauInstrucao());
        pacienteResponseDTO.setProfissao(from.getProfissao());
        pacienteResponseDTO.setTelefone(from.getTelefone());
        pacienteResponseDTO.setEnderecoPessoal(from.getEnderecoPessoal());
        pacienteResponseDTO.setEnderecoTrabalho(from.getEnderecoTrabalho());
        pacienteResponseDTO.setInfoAdicionais(from.getInfoAdicionais());
        pacienteResponseDTO.setContatoEmergencia(from.getContatoEmergencia());
        return pacienteResponseDTO;
    }
}