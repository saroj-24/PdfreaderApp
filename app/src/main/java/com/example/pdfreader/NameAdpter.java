package com.example.pdfreader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class NameAdpter extends RecyclerView.Adapter<MainViewHolder> {

    private Context context;
    private List<File> pdffiles;
    private pdfselection listner;

    public NameAdpter(Context context, List<File> pdffiles, pdfselection listner) {
        this.context = context;
        this.pdffiles = pdffiles;
        this.listner = listner;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.pdfdesign,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtname.setText(pdffiles.get(position).getName());
        holder.txtname.setSelected(true);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              listner.onPdfSeleceted(pdffiles.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return pdffiles.size();
    }
}
