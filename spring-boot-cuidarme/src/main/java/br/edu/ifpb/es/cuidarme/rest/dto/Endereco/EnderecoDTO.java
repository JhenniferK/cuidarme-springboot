package br.edu.ifpb.es.cuidarme.rest.dto.Endereco;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;

}
