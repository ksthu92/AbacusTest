package com.example.abacustest;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder>{

    private final List<ColorItem> recyclerViewItems;

    public class  ColorViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.colorTextView)
        TextView colorTextView;
        @BindView(R.id.linearlayout)
        LinearLayout linearLayout;

    private ColorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        }
    }

    public ColorAdapter(List<ColorItem> recyclerViewItems) {
        this.recyclerViewItems = recyclerViewItems;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.coloritems,parent,false);
        return new ColorViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder viewHolder, int position) {
       ColorItem currentColorItem = recyclerViewItems.get(position);
       viewHolder.colorTextView.setText(currentColorItem.getColorName());
       viewHolder.colorTextView.setBackgroundColor(Color.parseColor(currentColorItem.getColorCode()));
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }
}