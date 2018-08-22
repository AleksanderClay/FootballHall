package com.example.footballhall.footballhall.objetos;

import java.text.DateFormat;
import java.util.Date;

public class Agenda {

    public int id;
    public int idCliente;
    public String arena;
    public Date data;
    public Date hora;

    public Agenda(int id, int idCliente, String arena, Date data, Date hora) {
        this.id = id;
        this.idCliente = idCliente;
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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