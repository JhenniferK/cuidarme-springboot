package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.Endereco;
import br.edu.ifpb.es.cuidarme.rest.dto.Endereco.EnderecoDTO;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {
    public Endereco from (EnderecoDTO from){
        Endereco endereco = new Endereco();
        endereco.setLookupId(from.getLookupId());
        endereco.setCep(from.getCep());
        endereco.setCidade(from.getCidade());
        endereco.setEstado(from.getEstado());
        endereco.setLogradouro(from.getLogradouro());
        endereco.setNumero(from.getNumero());
        return endereco;
    }

    public EnderecoDTO from(Endereco from){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLookupId(from.getLookupId());
        enderecoDTO.setCep(from.getCep());
        enderecoDTO.setCidade(from.getCidade());
        enderecoDTO.setEstado(from.getEstado());
        enderecoDTO.setLogradouro(from.getLogradouro());
        enderecoDTO.setNumero(from.getNumero());
        return enderecoDTO;
    }
}
