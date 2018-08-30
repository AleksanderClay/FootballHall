package com.example.footballhall.footballhall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.footballhall.footballhall.objetos.Agenda;
import com.example.footballhall.footballhall.objetos.AgendaDbHelper;
import com.example.footballhall.footballhall.objetos.ListaPersonalizadaAdapter;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoActivity extends AppCompatActivity {

    private ListView listaAgendas;

    public List<Agenda> agendas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaAgendas = findViewById(R.id.lista_agendas);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            agendas = new AgendaDbHelper(getBaseContext()).Consultar("0");

            if (agendas != null) {
                final ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(agendas, this);
                listaAgendas.setAdapter(adapter);
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("MainActivity", "onResumo: " + e.getMessage());
        }
    }
}
