package com.example.pdfreader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {
    public TextView txtname;
    public CardView cardView;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);

        txtname = itemView.findViewById(R.id.pdf_name);
        cardView =itemView.findViewById(R.id.pdf_cardview);
    }
}
