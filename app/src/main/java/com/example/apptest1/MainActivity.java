package com.example.apptest1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptest1.databinding.ActivityLoginBinding;
import com.example.apptest1.databinding.ActivityMainBinding;
import com.example.apptest1.databinding.ActivitySignUpBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        });

        binding.Signup.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, signUp.class);
            startActivity(intent);
        });





    }
}