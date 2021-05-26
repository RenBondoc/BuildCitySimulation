package com.mad.cityassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private int height, width;
    private MapData md;
    private Context ct;

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyAdapter(Context ct, int height, int width, MapData md){
        this.ct = ct;
        this.height = height;
        this.width = width;
        this.md = md;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ct);
        View view = layoutInflater.inflate(R.layout.grid_cell, parent, false);

        int size = parent.getMeasuredHeight() / this.height + 1;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = size;
        lp.height = size;

        return new MyAdapter.MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        final int row = position % this.height;
        final int col = position / this.height;

        holder.northEast.setImageResource(md.getElement(row, col).getNorthEast());
        holder.northWest.setImageResource(md.getElement(row, col).getNorthWest());
        holder.southEast.setImageResource(md.getElement(row, col).getSouthEast());
        holder.southWest.setImageResource(md.getElement(row, col).getSouthWest());
        if (md.getElement(row, col).getStructure() != null) {
            holder.structure.setImageResource(md.getElement(row, col).getStructure().getDrawableId());
        }

    }


    @Override
    public int getItemCount() {
        return this.width * this.height;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView northEast, northWest, southEast, southWest, structure;
        private ConstraintLayout layout;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            northEast = (ImageView) itemView.findViewById(R.id.nortEast);
            northWest = (ImageView) itemView.findViewById(R.id.nortWest);
            southEast = (ImageView) itemView.findViewById(R.id.southEast);
            southWest = (ImageView) itemView.findViewById(R.id.southWest);
            structure = (ImageView) itemView.findViewById(R.id.structure);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }

    }
}

