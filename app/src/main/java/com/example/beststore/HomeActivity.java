package com.example.beststore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);

        if (auth.getCurrentUser() != null) {
            // If the user is already logged in, you might want to redirect them to another activity
            Toast.makeText(this, "You are already logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }
    }




    public void Register(View view) {
        startActivity(new Intent(HomeActivity.this,RegistrationActivity2.class));
    }

    public void Login(View view) {
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
    }
}