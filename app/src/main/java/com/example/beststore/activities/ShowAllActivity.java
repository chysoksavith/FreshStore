package com.example.beststore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.beststore.Models.AllProductModel;
import com.example.beststore.R;
import com.example.beststore.adapter.ShowAllAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<AllProductModel> allProductModelList;

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        allProductModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this, allProductModelList);
        recyclerView.setAdapter(showAllAdapter);


        firestore.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot doc :task.getResult().getDocuments()){
                                AllProductModel allProductModel = doc.toObject(AllProductModel.class);
                                allProductModelList.add(allProductModel);
                                showAllAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                });
    }
}