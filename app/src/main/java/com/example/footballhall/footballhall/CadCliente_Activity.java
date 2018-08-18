package com.example.footballhall.footballhall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.footballhall.footballhall.objetos.Cliente;
import com.example.footballhall.footballhall.objetos.ClienteDbHelper;

public class CadCliente_Activity extends AppCompatActivity {

    private EditText id;
    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtEmail;
    private EditText edtTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome     = (EditText)findViewById(R.id.edtNome);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtEmail    = (EditText)findViewById(R.id.edtEmail);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);

    }


    public void Salvar(View view) {

        Cliente cliente = new Cliente(
                1,
                edtNome.getText().toString(),
                edtEndereco.getText().toString(),
                edtEmail.getText().toString(),
                edtTelefone.getText().toString()
        );

        ClienteDbHelper clienteDbHelper = new ClienteDbHelper(this);
        clienteDbHelper.Salvar(cliente);

        finish();
    }
}
