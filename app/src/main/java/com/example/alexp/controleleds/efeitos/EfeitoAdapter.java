package com.example.alexp.controleleds.efeitos;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alexp.controleleds.Cor;
import com.example.alexp.controleleds.R;

import java.util.List;

/**
 * Created by alexp on 29/08/2017.
 */

public class EfeitoAdapter extends BaseAdapter {

    private final List<Efeito> efeitos;
    private final Activity act;

    public EfeitoAdapter(List<Efeito> efeitos, Activity act) {
        this.efeitos = efeitos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return efeitos.size();
    }

    @Override
    public Object getItem(int position) {
        return efeitos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.layout_adapter, parent, false);

        Efeito efeito = efeitos.get(position);

        TextView textCor = (TextView)
                view.findViewById(R.id.Color);
        TextView nome = (TextView)
                view.findViewById(R.id.ColorName);

        //define os dados de cada box

        textCor.setText("Otaru");
        nome.setText(efeito.getNome());


        return view;
    }
}
