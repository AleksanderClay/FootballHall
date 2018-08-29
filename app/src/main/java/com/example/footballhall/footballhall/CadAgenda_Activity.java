package com.example.footballhall.footballhall;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.footballhall.footballhall.objetos.Agenda;
import com.example.footballhall.footballhall.objetos.AgendaDbHelper;
import com.example.footballhall.footballhall.objetos.Cliente;
import com.example.footballhall.footballhall.objetos.ClienteDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadAgenda_Activity extends AppCompatActivity {
    private EditText editNome;
    private EditText editTel;
    private Spinner spinner_Arena;
    private EditText editData;
    private Spinner editHora;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try{
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

            } else {
                editNome = findViewById(R.id.editNome);
                editTel = findViewById(R.id.editTel);
                editData = findViewById(R.id.editData);
                editHora = findViewById(R.id.editHora);
                spinner_Arena = findViewById(R.id.spinner_Arena);


                editNome.setText(cliente.nome);
                editTel.setText(cliente.telefone);
                editTel.addTextChangedListener(MaskEditUtil.mask(editTel, MaskEditUtil.FORMAT_FONE));
                spinner_Arena.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                editHora.setOnItemSelectedListener(new CustomOnItemSelectedListener());

            }

            editNome = findViewById(R.id.editNome);
            editTel = findViewById(R.id.editTel);
            editData = findViewById(R.id.editData);
            editHora = findViewById(R.id.editHora);
            spinner_Arena = findViewById(R.id.spinner_Arena);


            editNome.setText(cliente.nome);
            editTel.setText(cliente.telefone);
            editTel.addTextChangedListener(MaskEditUtil.mask(editTel, MaskEditUtil.FORMAT_FONE));
            spinner_Arena.setOnItemSelectedListener(new CustomOnItemSelectedListener());
            editHora.setOnItemSelectedListener(new CustomOnItemSelectedListener());;
        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Agenda", "Erro OnCreate, " + e.getMessage());
        }
    }

    public void calendario(View view) {
        try{
            final Calendar c = Calendar.getInstance();
            final int mYear = c.get(Calendar.YEAR);
            final int mMonth = c.get(Calendar.MONTH);
            final int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            editData.setText(new StringBuilder().append(mDay).append("-").append(mMonth + 1).append("-").append(mYear));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Agenda", e.getMessage());
        }
    }

    public void Agendar(View view)  {
        try {

            if (editData.getText().toString().isEmpty()) {
                editData.setError("Entre com uma data!");

                return;
            }

            int id = 0;

            Agenda agenda = new Agenda(id,
                    1,
                    String.valueOf(spinner_Arena.getSelectedItem()),
                    new SimpleDateFormat("dd-MM-yyyy").parse(editData.getText().toString()),
                    String.valueOf(editHora.getSelectedItem())
            );

        AgendaDbHelper agendaDbHelper = new AgendaDbHelper(this);
            agendaDbHelper.Agendar(agenda);


            Toast.makeText(this, "Agendamento Salvo com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Agenda", "Agendar: " + e.getMessage());
        }
    }

    public void Cancelar(View view) {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }
}



