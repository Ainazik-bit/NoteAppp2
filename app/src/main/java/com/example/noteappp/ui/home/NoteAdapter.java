package com.example.noteappp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappp.Models.Note;
import com.example.noteappp.OnItemClickListener;
import com.example.noteappp.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public NoteAdapter(){
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }
        @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Note note) {
        list.add(0, note);
        notifyDataSetChanged();
    }

//    public void addList(ArrayList<Note> list) {
//        this.list.clear();
//        this.list.addAll(list);
//        notifyDataSetChanged();
//    }

    public void remove( int position) {
        this.list.remove(position);
        notifyDataSetChanged();
    }

    public void setList(List<Note> noteList) {
        this.list.clear();
        this.list.addAll(noteList);
        notifyDataSetChanged();
    }

    public Note getItem(int position) {
        return list.get(position);
     }

    public void setItem(Note note, int temp) {
        this.list.set(temp, note);
        notifyItemInserted(temp);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.longClick(getAdapterPosition());
                    return true;
                }
            });

        }

        public void bind(Note note) {
            textTitle.setText(note.getTitles()+"\n" + note.getDate());

        }

    }

}
