package com.example.alexp.controleleds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andersonl on 6/18/15.
 */
public class GridLettersAdapter extends ScrollingActivity.Adapter<Letter> {

    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private List<String> letters;

    public GridLettersAdapter() {
        int count = alphabet.length;
        letters = new ArrayList<String>(count);
        for (int i = 0; i < count; ++i) {
            letters.add(String.valueOf(alphabet[i]));
        }
    }

    @Override
    public Letter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new Letter(view);
    }

    @Override
    public void onBindViewHolder(final Letter holder, final int position) {
        final String label = letters.get(position);
        holder.textView.setText(label);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        holder.textView.getContext(), label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return letters.size();
    }
}