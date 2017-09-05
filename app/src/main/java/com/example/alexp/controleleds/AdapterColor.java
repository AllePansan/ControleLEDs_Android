package com.example.alexp.controleleds;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alexp on 26/08/2017.
 */

public class AdapterColor extends BaseAdapter {

    private final List<Cor> cores;
    private final Activity act;

    public AdapterColor(List<Cor> cores, Activity act) {
        this.cores = cores;
        this.act = act;
    }

    @Override
    public int getCount() {
        return cores.size();
    }

    @Override
    public Object getItem(int position) {
        return cores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.layout_adapter, parent, false);

        Cor cor = cores.get(position);

        TextView textCor = (TextView)
                view.findViewById(R.id.Color);
        TextView nome = (TextView)
                view.findViewById(R.id.ColorName);

        //define os dados de cada box

        textCor.setBackgroundColor(Color.rgb(cor.getRed(), cor.getGreen(), cor.getBlue()));
        nome.setText(cor.getNome());


        return view;
    }
}
