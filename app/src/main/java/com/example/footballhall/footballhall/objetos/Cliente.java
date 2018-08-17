package com.example.footballhall.footballhall.objetos;

public class Cliente {

    public int id;
    public String nome;
    public String endereco;
    public String email;
    public int telefone;

    public Cliente(String string, String cursorString, String s, int anInt){
    }

    public Cliente(int id, String nome, String endereco, String email, int telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
