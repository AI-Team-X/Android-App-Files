package com.teamx.bottomnav;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private String[] text;
    private LayoutInflater layoutInflater;
  //  private AdapterView.OnItemClickListener onItemClickListener;


    public RVAdapter(String[] text, Context context) {
        this.text = text;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.findlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
        String mtext = text[position];
        holder.cardView.setText(mtext);

    }

    @Override
    public int getItemCount() {
        return text.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.farmer_chat);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClick(v, getAdapterPosition());
        }
    }

    public String getItem(int id) {
        return text[id];
    }
    public void onItemClick(View v, int position) {
        Log.i("TAG", "You clicked number " + getItem(position).toString() + ", which is at cell position " + position);
    }
}
