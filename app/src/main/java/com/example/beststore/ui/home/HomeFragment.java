package com.example.beststore.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beststore.Models.AllProductModel;
import com.example.beststore.Models.HomeCategory;
import com.example.beststore.Models.PopularModel;
import com.example.beststore.Models.RecommendModel;
import com.example.beststore.Models.ViewAllModel;
import com.example.beststore.R;
import com.example.beststore.adapter.AllProductAdapter;
import com.example.beststore.adapter.HomeAdapter;
import com.example.beststore.adapter.PopularAdapter;
import com.example.beststore.adapter.RecommentAdapter;
import com.example.beststore.adapter.ViewAllAdapter;
import com.example.beststore.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView popularRec, homeCatRec, recommendRec, allProductRec;
    FirebaseFirestore db;
    //popularitem
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    //Homecategory
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;

    // recommend
    List<RecommendModel> recommendModelList;
    RecommentAdapter recommentAdapter;
    // all product
    List<AllProductModel> allProductModelList;
    AllProductAdapter allProductAdapter;
    // Search View
    EditText search_box;
    private List<ViewAllModel> viewAllModelList;
    private RecyclerView recyclerViewSearch;
    private ViewAllAdapter viewAllAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();


        popularRec = root.findViewById(R.id.pop_rec);
        homeCatRec = root.findViewById(R.id.explaore_rec);
        recommendRec = root.findViewById(R.id.recommend_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbar);
        allProductRec = root.findViewById(R.id.all_product);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);


        //pop items

        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(), popularModelList);
        popularRec.setAdapter(popularAdapter);

        db.collection("PopularProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Clear the list to prevent duplicates

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//        category
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), categoryList);
        homeCatRec.setAdapter(homeAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Clear the list to prevent duplicates

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // recommend
        recommendRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendModelList = new ArrayList<>();
        recommentAdapter = new RecommentAdapter(getActivity(), recommendModelList);
        recommendRec.setAdapter(recommentAdapter);

        db.collection("Recommend")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Clear the list to prevent duplicates

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendModel recommendModel = document.toObject(RecommendModel.class);
                                recommendModelList.add(recommendModel);
                                recommentAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        // recommend
        recommendRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendModelList = new ArrayList<>();
        recommentAdapter = new RecommentAdapter(getActivity(), recommendModelList);
        recommendRec.setAdapter(recommentAdapter);

        db.collection("Recommend")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Clear the list to prevent duplicates

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendModel recommendModel = document.toObject(RecommendModel.class);
                                recommendModelList.add(recommendModel);
                                recommentAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // all product
        int numberOfItemsInOneLine = 2;
        allProductRec.setLayoutManager(new GridLayoutManager(getActivity(), numberOfItemsInOneLine, RecyclerView.VERTICAL, false));
        allProductModelList = new ArrayList<>();
        allProductAdapter = new AllProductAdapter(getActivity(), allProductModelList);
        allProductRec.setAdapter(allProductAdapter);


        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Clear the list to prevent duplicates

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AllProductModel allProductModel = document.toObject(AllProductModel.class);
                                allProductModelList.add(allProductModel);
                                allProductAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Errors: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // Search View
        recyclerViewSearch = root.findViewById(R.id.search_rec);
        search_box = root.findViewById(R.id.search_box);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getContext(), viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);

        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for your implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed for your implementation
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                } else {
                    searchProduct(s.toString());
                }
            }
        });
        return root;
    }

    private void searchProduct(String productName) {
        if (!productName.isEmpty()) {
            db.collection("AllProducts").whereEqualTo("name", productName).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                List<ViewAllModel> updatedList = new ArrayList<>();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                    updatedList.add(viewAllModel);
                                }
                                viewAllModelList.clear();
                                viewAllModelList.addAll(updatedList);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
    }

}