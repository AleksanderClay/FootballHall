package com.example.footballhall.footballhall;

import android.app.DatePickerDialog;
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
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.example.footballhall.footballhall.objetos.Agenda;
import com.example.footballhall.footballhall.objetos.AgendaDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadAgenda_Activity extends AppCompatActivity  {

    private EditText editNome;
    private EditText editTel;
    private Spinner spinner_Arena;
    private EditText editData;
    private EditText editHora;
    private Button button_Agendar;
    private Button button_Cancelar;
    Agenda objAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editNome = (EditText) findViewById(R.id.editNome);
        editTel = (EditText) findViewById(R.id.editTel);
        editData = (EditText) findViewById(R.id.editData);
        editHora = (EditText) findViewById(R.id.editHora);
        spinner_Arena = (Spinner) findViewById(R.id.spinner_Arena);

        try {
        objAgenda = (Agenda) getIntent().getExtras().getSerializable("objAgenda");
        editNome.setText(objAgenda.getNome());
        editTel.setText(objAgenda.getTel());
        spinner_Arena.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        editData.setText(new SimpleDateFormat("dd-MM-yyyy").format(objAgenda.getData()));
        editHora.setText(new SimpleDateFormat("hh:mm").format(objAgenda.getHora()));

        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Agenda", "Erro OnCreate, " + e.getMessage());
        }

        }


    public void calendario(View view) {
        try {
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
        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Agenda", e.getMessage());
        }
    }

    public void Agendar(View view) {
        try {
            if (editData.getText().toString().isEmpty()){
                editData.setError("Entre com uma data!");

                return;
            }
            if (editHora.getText().toString().isEmpty()){
                editHora.setError("Informe a hora desejada!");

                return;
            }

            int id = 0;

            if (objAgenda != null){
                id = objAgenda.getId();
            }

            Agenda agenda = new Agenda(
                    id,
                    editNome.getText().toString(),
                    - Integer.parseInt(editTel.getText().toString()),
                    spinner_Arena.toString(),
                    new SimpleDateFormat("dd-MM-yyyy").parse(editData.getText().toString()),
                    new SimpleDateFormat("hh:mm").parse(editHora.getText().toString())
            );

            AgendaDbHelper agendaDbHelper = new AgendaDbHelper(this);
            agendaDbHelper.Agendar(agenda);


            finish();

        } catch (Exception e){
            Toast.makeText(this, "Ocorreu um erro...", Toast.LENGTH_LONG).show();
            Log.e("Agenda", "Agendar: " + e.getMessage());
        }
    }

   public void addListenerOnButton() {

        spinner_Arena = (Spinner) findViewById(R.id.spinner_Arena);

        button_Agendar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(CadAgenda_Activity.this,
                        "OnClickListener : " +
                                "\nSpinner : " + String.valueOf(spinner_Arena.getSelectedItem()) ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}

