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
import com.example.beststore.Models.AllProductModel;
import com.example.beststore.R;

import java.util.List;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder> {
    private Context context;
    private List<AllProductModel> list;

    public ShowAllAdapter(Context context, List<AllProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.mItemImage);
        holder.mCost.setText("$" + String.valueOf(list.get(position).getPrice()));
        holder.mName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImage;
        private TextView mCost;
        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemImage = itemView.findViewById(R.id.show_all_image);
            mCost = itemView.findViewById(R.id.showall_all_price);
            mName = itemView.findViewById(R.id.showall_all_title);
        }
    }
}
