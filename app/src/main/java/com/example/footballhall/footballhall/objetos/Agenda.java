package com.example.footballhall.footballhall.objetos;

import java.text.DateFormat;
import java.util.Date;

public class Agenda {

    public int id;
    public String nome;
    public int tel;
    public String arena;
    public Date data;
    public Date hora;

    public Agenda(int id, String nome, int tel, String arena, Date data, Date hora) {
        this.id = id;
        this.nome = nome;
        this.tel = tel;
        this.arena = arena;
        this.data = data;
        this.hora = hora;
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

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
}