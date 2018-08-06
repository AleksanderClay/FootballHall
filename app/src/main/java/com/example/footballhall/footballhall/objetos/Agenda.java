package com.example.footballhall.footballhall.objetos;

import java.text.DateFormat;
import java.util.Date;

public class Agenda {

    public int id;
    public int idCliente;
    public int quantidadeQuadras;
    public Date diaHorario;

    public Agenda() {
    }

    public Agenda(int id, int idCliente, int quantidadeQuadras, Date diaHorario) {
        this.id = id;
        this.idCliente = idCliente;
        this.quantidadeQuadras = quantidadeQuadras;
        this.diaHorario = diaHorario;
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

    public int getQuantidadeQuadras() {
        return quantidadeQuadras;
    }

    public void setQuantidadeQuadras(int quantidadeQuadras) {
        this.quantidadeQuadras = quantidadeQuadras;
    }

    public Date getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(Date diaHorario) {
        this.diaHorario = diaHorario;
    }
}
