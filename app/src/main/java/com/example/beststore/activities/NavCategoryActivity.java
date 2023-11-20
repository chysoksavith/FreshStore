package com.example.beststore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.beststore.Models.HomeCategory;
import com.example.beststore.Models.NavCategoryDetaliedModel;
import com.example.beststore.Models.ViewAllModel;
import com.example.beststore.R;
import com.example.beststore.adapter.NavCategoryDetailedAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<NavCategoryDetaliedModel> list;
    NavCategoryDetailedAdapter adapter;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);
        String type = getIntent().getStringExtra("type");

        db = FirebaseFirestore.getInstance();


        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);


        if(type !=  null && type.equalsIgnoreCase("drink")){
            db.collection("NavCategoryDetailed").whereEqualTo("type", "drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetaliedModel navCategoryDetaliedModel = documentSnapshot.toObject(NavCategoryDetaliedModel.class);
                        list.add(navCategoryDetaliedModel);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
        // snack
        if(type !=  null && type.equalsIgnoreCase("snack")){
            db.collection("NavCategoryDetailed").whereEqualTo("type", "snack").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        NavCategoryDetaliedModel navCategoryDetaliedModel = documentSnapshot.toObject(NavCategoryDetaliedModel.class);
                        list.add(navCategoryDetaliedModel);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
//        db.collection("NavCategoryDetailed")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            // Clear the list to prevent duplicates
//
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                NavCategoryDetaliedModel navCategoryDetaliedModel = document.toObject(NavCategoryDetaliedModel.class);
//                                list.add(navCategoryDetaliedModel);
//                                adapter.notifyDataSetChanged();
//                            }
//                        } else {
//                            Toast.makeText(NavCategoryActivity.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

    }

}