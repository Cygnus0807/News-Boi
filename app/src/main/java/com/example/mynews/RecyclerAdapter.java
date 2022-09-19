package com.example.mynews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder > {

    Context context;
    ArrayList<MyData> data;
    RecyclerAdapter(Context context, ArrayList<MyData> data){
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_item,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int i) {
        MyData CurData=data.get(i);
        Picasso.get().load(CurData.getImgUrl()).into(viewHolder.imgUrl);
        viewHolder.title.setText(CurData.getTitle());
        viewHolder.date.setText(CurData.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUrl;
        TextView title;
        TextView date;
        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            this.imgUrl=view.findViewById(R.id.newImg);
            this.title= view.findViewById(R.id.title);
            this.date=view.findViewById(R.id.date);
        }
    }

}
