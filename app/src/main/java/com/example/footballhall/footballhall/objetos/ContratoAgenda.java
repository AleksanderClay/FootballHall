package com.example.footballhall.footballhall.objetos;

import android.provider.BaseColumns;

public class ContratoAgenda {

    public static final String SQL_CREATE_AGENDA =
            "CREATE TABLE " + TabelaAgenda.TABLE_NAME + " (" +
                    TabelaAgenda._ID + " INTEGER PRIMARY KEY," +
                    TabelaAgenda.COLUMN_NAME_IDCLIENTE + " INTEGER," +
                    TabelaAgenda.COLUMN_NAME_QUANTIDADEQUADRAS + " INTEGER," +
                    TabelaAgenda.COLUMN_NAME_DIAHORARIO + " TEXT)";

    public static final String SQL_DELETE_AGENDA =
            "DROP TABLE IF EXISTS " + TabelaAgenda.TABLE_NAME;

    private ContratoAgenda() {}

    public static class TabelaAgenda implements BaseColumns {
        public static final String TABLE_NAME = "agenda";
        public static final String COLUMN_NAME_IDCLIENTE = "id_cliente";
        public static final String COLUMN_NAME_QUANTIDADEQUADRAS = "quantidade_quadras";
        public static final String COLUMN_NAME_DIAHORARIO = "dia_horario";
    }

}
