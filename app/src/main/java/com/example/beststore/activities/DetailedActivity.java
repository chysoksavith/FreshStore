package com.example.beststore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.beststore.Models.AllProductModel;
import com.example.beststore.Models.RecommendModel;
import com.example.beststore.Models.ViewAllModel;
import com.example.beststore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {


    private ImageView detailedImage;
    private TextView price, rating, description, name, quantity;
    private Button addToCart;
    private ImageView addItem, removeItem;

    private ViewAllModel viewAllModel;
    private RecommendModel recommendModel;
    private AllProductModel allProductModel;

    private int totalQuantity = 1;
    private int totalPrice = 0;

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        FirebaseApp.initializeApp(this);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        initializeViews();
        initializeData();

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoCart();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    updateQuantity();
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    updateQuantity();
                }
            }
        });
    }

    private void initializeViews() {
        detailedImage = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_items);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detail_price);
        rating = findViewById(R.id.detail_rating);
        description = findViewById(R.id.detail_decription);
        name = findViewById(R.id.detail_name);
        addToCart = findViewById(R.id.addTocart);
        quantity = findViewById(R.id.quantity);
    }

    private void initializeData() {
        Object object = getIntent().getSerializableExtra("detail");

        if (object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;
            displayViewAllDetails();
        } else if (object instanceof RecommendModel) {
            recommendModel = (RecommendModel) object;
            displayRecommendDetails();
        } else if (object instanceof  AllProductModel) {
            allProductModel = (AllProductModel) object;
            displayAllProductDetails();
        } else {
            handleNullIntentExtra();
        }
    }

    private void displayViewAllDetails() {
        if (viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImage);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            name.setText(viewAllModel.getName());
            price.setText("Price: $" + String.valueOf(viewAllModel.getPrice()));
        }
    }

    private void displayRecommendDetails() {
        if (recommendModel != null) {
            Glide.with(getApplicationContext()).load(recommendModel.getImg_url()).into(detailedImage);
            rating.setText(recommendModel.getRating());
            description.setText(recommendModel.getDescription());
            name.setText(recommendModel.getName());
            price.setText("Price: $" + String.valueOf(recommendModel.getPrice()));
        }
    }

    private void displayAllProductDetails(){
        if(allProductModel != null){
            Glide.with(getApplicationContext()).load(allProductModel.getImg_url()).into(detailedImage);
            name.setText(allProductModel.getName());
            price.setText("Price:  $" + String.valueOf(allProductModel.getPrice()));

        }
    }

    private void handleNullIntentExtra() {
        Log.e("DetailedActivity", "Error: Intent extra is null");
        Toast.makeText(this, "Error loading details", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updateQuantity() {
        quantity.setText(String.valueOf(totalQuantity));
    }

    private void addtoCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:SS A");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", name.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);

        String userId = (auth.getCurrentUser() != null) ? auth.getCurrentUser().getUid() : "default_user";

        firestore.collection("AddToCart")
                .document(userId)
                .collection("CurrentUser")
                .add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

}