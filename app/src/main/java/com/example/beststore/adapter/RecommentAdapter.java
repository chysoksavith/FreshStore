package com.example.beststore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beststore.Models.RecommendModel;
import com.example.beststore.R;

import java.util.List;

public class RecommentAdapter extends RecyclerView.Adapter<RecommentAdapter.ViewHolder> {

    Context context;
    List<RecommendModel> list;

    public RecommentAdapter(Context context, List<RecommendModel> recommendModelList) {
        this.context = context;
        this.list = recommendModelList;
    }

    @NonNull
    @Override
    public RecommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_home, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecommentAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.rating.setText(list.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rec_img);
            name = itemView.findViewById(R.id.rec_name);
            description = itemView.findViewById(R.id.rec_dec);
            rating = itemView.findViewById(R.id.rec_ratign);
        }
    }
}
