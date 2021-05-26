package com.mad.cityassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class SelectorAdapter extends RecyclerView.Adapter<SelectorAdapter.SelectorHolder>{

    private Context context;
    private StructureData structures;

    public SelectorAdapter(Context ct, StructureData structures) {
        this.context = ct;
        this.structures = structures;

    }

    private OnClickListener listener;

    public interface OnClickListener {
        void onClickItem(int position);
    }

    public void setOnClickListener(OnClickListener listener){ this.listener = listener; }

    @NonNull
    @Override
    public SelectorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.selector_list, parent, false);

        return new SelectorHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectorHolder holder, int position) {
        holder.imageView.setImageResource(structures.get(position).getDrawableId());
        holder.imageText.setText(structures.get(position).getLabel());
        holder.costText.setText("$" + Integer.toString(structures.get(position).getCost()));
    }

    @Override
    public int getItemCount() {
        return structures.size();
    }

    public class SelectorHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView imageText, costText;

        public SelectorHolder(@NonNull View itemView, final OnClickListener listener) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.itemImage);
            imageText = (TextView) itemView.findViewById(R.id.itemText);
            costText = (TextView) itemView.findViewById(R.id.costText);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClickItem(position);
                        }
                    }
                }
            });

        }

    }
}
