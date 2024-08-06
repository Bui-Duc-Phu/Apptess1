package com.example.apptest1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptest1.appSetup.MyApp;
import com.example.apptest1.databinding.ActivityHomeBinding;
import com.example.apptest1.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyApp myApp = (MyApp) getApplicationContext();

        binding.token.setText(myApp.getToken());

        binding.logOut.setOnClickListener(v -> {
            mAuth.signOut();
        });






    }
}