package br.edu.ifpb.es.cuidarme.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ContatoEmergencia {
    private String nome;
    private String telefone;
    private String parentesco;

    public ContatoEmergencia(){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContatoEmergencia that = (ContatoEmergencia) o;
        return Objects.equals(nome, that.nome) && Objects.equals(telefone, that.telefone) && Objects.equals(parentesco, that.parentesco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, telefone, parentesco);
    }

    @Override
    public String toString() {
        return "ContatoEmergencia{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", parentesco='" + parentesco + '\'' +
                '}';
    }
}
