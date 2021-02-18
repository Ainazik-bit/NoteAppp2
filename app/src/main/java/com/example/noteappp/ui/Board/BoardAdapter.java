package com.example.noteappp.ui.Board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.noteappp.Models.BoardRabbit;
import com.example.noteappp.OnItemClickListener;
import com.example.noteappp.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder>{

    private static final String TAG = "olneoo";
    private  ClickListener onItemClickListener;
    private ArrayList<BoardRabbit> list = new ArrayList<>();
    private OnItemClickListener onItemClickListenerr;

    public BoardAdapter(ClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).
               inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setOnItemClickListener(ClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addRabbitToBoard(ArrayList<BoardRabbit> list) {
        this.list = list;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{
        private LottieAnimationView lt;
        private TextView textTitle;
        private TextView textDesc;
        private Button btnStart;
        private ClickListener listener;


        public ViewHolder(@NonNull View itemView,ClickListener listener){
            super(itemView);
            this.listener = listener;

            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
            lt = itemView.findViewById(R.id.imageView);

            btnStart = itemView.findViewById(R.id.btnStart);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
        }


        public void bind(int position) {
            btnStart.setVisibility(View.GONE);
            show(position);
    }

    private void show(int position) {
        if (position == 2){
            btnStart.setVisibility(View.VISIBLE);
        }
        BoardRabbit br = list.get(position);
        textTitle.setText(br.getName());
        textDesc.setText(br.getDesc());
        lt.setAnimation(br.getImageResourceId());
    }
}

    interface ClickListener{
        void onClick(int position);
    }
}

