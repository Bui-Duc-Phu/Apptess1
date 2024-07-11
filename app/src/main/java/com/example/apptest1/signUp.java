package com.example.apptest1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptest1.appSetup.MyApp;
import com.example.apptest1.databinding.ActivityLoginBinding;
import com.example.apptest1.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

public class signUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();


        init_();




    }

    private void init_() {
        binding.signUpButton.setOnClickListener(view -> {
            String email = binding.emailInput.getText().toString();
            String password = binding.passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(signUp.this, "Email and password must not be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            signUpUser(email, password);
        });

    }

    private void signUpUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(signUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {

                                MyApp myApp = (MyApp) getApplicationContext();
                                myApp.setUid(user.getUid());
                                user.getIdToken(true)
                                        .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<GetTokenResult> task) {
                                                if (task.isSuccessful()) {
                                                    String token = task.getResult().getToken();
                                                    myApp.setToken(token);

                                                    // Start HomeActivity
                                                    Intent intent = new Intent(signUp.this, Home.class);
                                                    startActivity(intent);
                                                    finish(); // Close the current activity
                                                } else {
                                                    System.out.println("sdsd : "+ task.getException().getMessage());
                                                    Toast.makeText(signUp.this, "Failed to get token: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            }

                            // Start HomeActivity
                            Intent intent = new Intent(signUp.this, Home.class);
                            startActivity(intent);
                            finish(); // Close the
                        } else {
                            // If sign up fails, display a message to the user
                            Toast.makeText(signUp.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}