package com.example.footballhall.footballhall.objetos;

import android.provider.BaseColumns;

public class ContratoCliente {

    public static final String SQL_CREAT_CLIENTE =
            "CREATE TABLE " + TabelaCliente.TABLE_NAME + " (" +
                    TabelaCliente._ID + " INTEGER PRIMARY KEY," +
                    TabelaCliente.COLUMN_NAME_NOME + " TEXT," +
                    TabelaCliente.COLUMN_NAME_ENDERECO + " TEXT," +
                    TabelaCliente.COLUMN_NAME_EMAIL + " TEXT," +
                    TabelaCliente.COLUMN_NAME_TELEFONE +" INTEGER)";

    public static final String SQL_DELETE_CLIENTE =
            "DROP TABLE IF EXISTS " + TabelaCliente.TABLE_NAME;

    private ContratoCliente() {}

    public static class TabelaCliente implements BaseColumns {
        public static final String TABLE_NAME = "cliente";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_ENDERECO = "endereco";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_TELEFONE = "telefone";
    }
}
