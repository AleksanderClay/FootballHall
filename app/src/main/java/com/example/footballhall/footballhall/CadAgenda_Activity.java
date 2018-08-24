package com.example.footballhall.footballhall;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.example.footballhall.footballhall.objetos.Agenda;
import com.example.footballhall.footballhall.objetos.AgendaDbHelper;
import com.example.footballhall.footballhall.objetos.Cliente;
import com.example.footballhall.footballhall.objetos.ClienteDbHelper;
import com.google.android.gms.common.api.Api;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadAgenda_Activity extends AppCompatActivity {
    private EditText editNome;
    private EditText editTel;
    private Spinner spinner_Arena;
    private EditText editData;
    private Spinner editHora;
    final Context context = this;
    Agenda objAgenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            final Cliente cliente = new ClienteDbHelper(this).ConsultarCliente();
            if (cliente.id == 0) {
                AlertDialog.Builder alertDialogBuilder = new
                        AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Usuário não cadastrado!");
                alertDialogBuilder
                        .setMessage("É preciso efetuar cadastro. \n Deseja se cadastrar agora?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getBaseContext(), CadCliente_Activity.class));

                                Cliente cli = new ClienteDbHelper(getBaseContext()).ConsultarCliente();
                                if (cliente.id == 0) {
                                    finish();
                                }
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return;

            } else {
                editNome = (EditText) findViewById(R.id.editNome);
                editTel = (EditText) findViewById(R.id.editTel);
                editData = (EditText) findViewById(R.id.editData);
                editHora = (Spinner) findViewById(R.id.editHora);
                spinner_Arena = (Spinner) findViewById(R.id.spinner_Arena);

                editNome.setText(cliente.nome);
                editTel.setText(cliente.telefone);
                spinner_Arena.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                editData.setText(new SimpleDateFormat("dd-MM-yyyy").format(objAgenda.getData()));
                editHora.setOnItemSelectedListener(new CustomOnItemSelectedListener());

            }

            editNome = (EditText) findViewById(R.id.editNome);
            editTel = (EditText) findViewById(R.id.editTel);
            editData = (EditText) findViewById(R.id.editData);
            editHora = (Spinner) findViewById(R.id.editHora);
            spinner_Arena = (Spinner) findViewById(R.id.spinner_Arena);


            editNome.setText(cliente.nome);
            editTel.setText(cliente.telefone);
            spinner_Arena.setOnItemSelectedListener(new CustomOnItemSelectedListener());
            editData.setText(new SimpleDateFormat("dd-MM-yyyy").format(objAgenda.getData()));
            editHora.setOnItemSelectedListener(new CustomOnItemSelectedListener());;
    }

    public void calendario(View view) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            editData.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
    }

    public void Agendar(View view)  {
        try {

            if (editData.getText().toString().isEmpty()) {
                editData.setError("Entre com uma data!");

                return;
            }


            Agenda agenda = new Agenda(1,
                    1,
                    spinner_Arena.getOnItemSelectedListener().toString(),
                    new SimpleDateFormat("dd-MM-yyyy").parse(editData.getText().toString()),
                    editHora.getOnItemSelectedListener().toString()
            );

            AgendaDbHelper agendaDbHelper = new AgendaDbHelper(this);
            agendaDbHelper.Agendar(agenda);

            finish();
            Toast.makeText(this, "Agendamento Salvo com sucesso!", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Agenda", "Agendar: " + e.getMessage());
        }
    }

    public void Cancelar(View view) {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }
}



