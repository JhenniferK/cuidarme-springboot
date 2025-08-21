package br.edu.ifpb.es.cuidarme.rest.dto.ContatoEmergencia;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ContatoEmergenciaDTO {
    private UUID lookupId;
    private String nome;
    private String telefone;
    private String parentesco;

    public UUID getLookupId() {
        return lookupId;
    }

    public void setLookupId(UUID lookupId) {
        this.lookupId = lookupId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
}
