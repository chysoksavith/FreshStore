package com.example.beststore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.beststore.Models.ViewAllModel;
import com.example.beststore.R;

public class DetailedActivity extends AppCompatActivity {


    ImageView detailedImaage;
    TextView price,rating,description;
    Button addtoCart;
    ImageView addItem,removeItem;

    ViewAllModel viewAllModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        final  Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        detailedImaage = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_items);
        removeItem = findViewById(R.id.remove_item);

        price = findViewById(R.id.detail_price);
        rating = findViewById(R.id.detail_rating);
        description = findViewById(R.id.detail_decription);

        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImaage);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price: $" + String.valueOf(viewAllModel.getPrice()));

        }

        addtoCart = findViewById(R.id.addTocart);
    }



}