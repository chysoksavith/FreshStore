package com.example.beststore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beststore.Models.AllProductModel;
import com.example.beststore.R;
import com.example.beststore.activities.DetailedActivity;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {
    private Context context;
    private List<AllProductModel> allProductModelsList;

    public AllProductAdapter(Context context, List<AllProductModel> allProductModelsList) {
        this.context = context;
        this.allProductModelsList = allProductModelsList;
    }


    @NonNull
    @Override
    public AllProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(allProductModelsList.get(position).getImg_url()).into(holder.AllProImage);
        holder.name.setText(allProductModelsList.get(position).getName());

        holder.price.setText("$" + String.valueOf(allProductModelsList.get(position).getPrice()));


    }

    @Override
    public int getItemCount() {
        return allProductModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView AllProImage;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            AllProImage = itemView.findViewById(R.id.product_all_image);
            name = itemView.findViewById(R.id.product_all_title);
            price = itemView.findViewById(R.id.product_all_price);
        }
    }
}
