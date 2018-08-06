package com.example.footballhall.footballhall.objetos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AgendaDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "footballhall.db";

    public AgendaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContratoAgenda.SQL_CREATE_AGENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContratoAgenda.SQL_DELETE_AGENDA);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean Salvar(Agenda agenda) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ContratoAgenda.TabelaAgenda.COLUMN_NAME_IDCLIENTE, agenda.getIdCliente());
            values.put(ContratoAgenda.TabelaAgenda.COLUMN_NAME_QUANTIDADEQUADRAS, agenda.getQuantidadeQuadras());
            values.put(ContratoAgenda.TabelaAgenda.COLUMN_NAME_DIAHORARIO, new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS").format(agenda.getDiaHorario()));

            String[] args = {Integer.toString(agenda.getId())};

            if (agenda.getId() > 0) {
                return db.update(ContratoAgenda.TabelaAgenda.TABLE_NAME, values, "_id = ?", args) > 0;
            } else {
                return db.insert(ContratoAgenda.TabelaAgenda.TABLE_NAME, null, values) > 0;
            }
        } catch (Exception e) {
            Log.e("AgendaDbHelper", "Erro no Salvar, " + e.getMessage());
            throw e;
        }
    }

    public boolean Remover(Agenda agenda) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String[] args = {Integer.toString(agenda.getId())};

            return db.delete(ContratoAgenda.TabelaAgenda.TABLE_NAME, "_id = ?", args) > 0;
        } catch (Exception e) {
            Log.e("AgendaDbHelper", "Erro no Remover, " + e.getMessage());
            throw e;
        }
    }

    public List<Agenda> Consultar(String ordem) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            switch (ordem) {
                case "0":
                    ordem = "dia_horario";
                    break;
            }

            Cursor cursor = db.query(ContratoAgenda.TabelaAgenda.TABLE_NAME, null, null, null, null, null, ordem);

            List<Agenda> agendas = new ArrayList<>();
            while (cursor.moveToNext()) {
                agendas.add(new Agenda(
                        cursor.getInt(cursor.getColumnIndex(ContratoAgenda.TabelaAgenda._ID)),
                        cursor.getInt(cursor.getColumnIndex(ContratoAgenda.TabelaAgenda.COLUMN_NAME_IDCLIENTE)),
                        cursor.getInt(cursor.getColumnIndex(ContratoAgenda.TabelaAgenda.COLUMN_NAME_QUANTIDADEQUADRAS)),
                        new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS").parse(cursor.getString(cursor.getColumnIndex(ContratoAgenda.TabelaAgenda.COLUMN_NAME_DIAHORARIO)))
                ));
            }
            cursor.close();

            return agendas;
        } catch (Exception e) {
            Log.e("ContratoAgenda", e.getMessage());
            return null;
        }
    }

}
