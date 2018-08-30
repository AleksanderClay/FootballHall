package com.example.footballhall.footballhall.objetos;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballhall.footballhall.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListaPersonalizadaAdapter extends BaseAdapter {
    private final List<Agenda> agendaList;
    private final Activity act;

    public ListaPersonalizadaAdapter(List<Agenda> agendaList, Activity act) {
        this.agendaList = agendaList;
        this.act = act;
    }

    @Override
    public int getCount() {
        return agendaList.size();
    }

    @Override
    public Object getItem(int position) {
        return agendaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_lista_personalizada, parent, false);

        Agenda agenda = agendaList.get(position);

        TextView data = (TextView)
                view.findViewById(R.id.txtData);
        TextView descricao = (TextView)
                view.findViewById(R.id.txtDescricao);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.icone);

        data.setText(new SimpleDateFormat("dd/MM/yy").format(agenda.data)
                + " - " + agenda.hora);
        descricao.setText(agenda.arena);

        if (agenda.arena.toLowerCase().contains("vindi")){
            imagem.setImageResource(R.drawable.imagem_vindi);
        } else if (agenda.arena.toLowerCase().contains("sabino")) {
            imagem.setImageResource(R.drawable.imagem_sabino);
        }
        else {
            imagem.setImageResource(R.drawable.imagem_senac);
        }

        return view;
    }
}
