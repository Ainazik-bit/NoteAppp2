package com.example.noteappp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappp.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    //============================================VIEWHODLER=================================
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textDesk;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle=itemView.findViewById(R.id.textTitle);
            textDesk=itemView.findViewById(R.id.textDesk);

        }
    }
}