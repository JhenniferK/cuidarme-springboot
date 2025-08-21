package br.edu.ifpb.es.cuidarme.rest.dto.Endereco;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class EnderecoDTO {

    private UUID lookupId;
    private String cep;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;

    public UUID getLookupId() {
        return lookupId;
    }

    public void setLookupId(UUID lookupId) {
        this.lookupId = lookupId;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
