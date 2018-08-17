package com.example.footballhall.footballhall.objetos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ClienteDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "footballhall.db";

    public ClienteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContratoCliente.SQL_CREAT_CLIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContratoCliente.SQL_DELETE_CLIENTE);
        onCreate(db);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean Salvar(Cliente cliente){
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ContratoCliente.TabelaCliente.COLUMN_NAME_IDCLIENTE, cliente.getId());
            values.put(ContratoCliente.TabelaCliente.COLUMN_NAME_NOME, cliente.getNome());
            values.put(ContratoCliente.TabelaCliente.COLUMN_NAME_ENDERECO, cliente.getEndereco());
            values.put(ContratoCliente.TabelaCliente.COLUMN_NAME_EMAIL, cliente.getEmail());
            values.put(ContratoCliente.TabelaCliente.COLUMN_NAME_TELEFONE, cliente.getTelefone());

            String[] args = {(cliente.getNome())};

            if (cliente.getNome() == null) {
                return db.update(ContratoCliente.TabelaCliente.TABLE_NAME, values, "_nome = ?", args) > 0;
            } else {
                return db.insert(ContratoCliente.TabelaCliente.TABLE_NAME, null, values) > 0;
            }
        } catch (Exception e) {
            Log.e("ClienteDbHelper", "Erro no Salvar, " + e.getMessage());
            throw e;
        }
    }

    public boolean Remover (Cliente cliente) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String args = ((cliente.getNome()));

            return db.delete(ContratoCliente.TabelaCliente.TABLE_NAME, "_nome = ?", new String[]{args}) > 0;
        } catch (Exception e) {
            Log.e("ClienteDbHelper", "Erro no Remover, " + e.getMessage());
            throw e;
        }
    }

    public List<Cliente> Consultar(String ordem) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            switch (ordem) {
                case "0":
                    ordem = "_nome";
                    break;
            }

            Cursor cursor = db.query(ContratoCliente.TabelaCliente.TABLE_NAME, null, null, null, null, null, ordem);

            List<Cliente> clientes = new ArrayList<>();
            while (cursor.moveToNext()) {
                clientes.add(new Cliente(
                        cursor.getInt(cursor.getColumnIndex(ContratoCliente.TabelaCliente._ID)),
                        cursor.getString(cursor.getColumnIndex(ContratoCliente.TabelaCliente.COLUMN_NAME_NOME)),
                        cursor.getString(cursor.getColumnIndex(ContratoCliente.TabelaCliente.COLUMN_NAME_ENDERECO)),
                        cursor.getString(cursor.getColumnIndex(ContratoCliente.TabelaCliente.COLUMN_NAME_EMAIL)),
                        cursor.getInt(cursor.getColumnIndex(ContratoCliente.TabelaCliente.COLUMN_NAME_TELEFONE))
                ));
            }
            cursor.close();

            return clientes;
        } catch (Exception e){
            Log.e("ContratoCliente", e.getMessage());
            return null;
        }
    }
}
