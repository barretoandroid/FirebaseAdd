package br.com.rbarreto.firebaseadddata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.rbarreto.firebaseadddata.R;


public class MeuAdapater extends ArrayAdapter {

    public Context context;
    private List<String> lista = new ArrayList<>();

    public MeuAdapater(Context context, ArrayList<String> lista){
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = convertView;

        if (listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.layout_lista, parent, false);
        }

        String dadoAtual = lista.get(position);

        TextView dado = listItem.findViewById(R.id.tvDado);
        dado.setText(dadoAtual);

        return listItem;
    }
}
