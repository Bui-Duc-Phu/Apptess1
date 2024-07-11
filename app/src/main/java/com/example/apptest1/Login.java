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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.loginButton.setOnClickListener(view -> {
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Email and password must not be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            loginUser(email, password);
        });

        // Set up the sign-up text click listener






    }


    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                            // Get the current user
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                // Set uid and token in MyApp
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
                                                    Intent intent = new Intent(Login.this, Home.class);
                                                    startActivity(intent);
                                                    finish(); // Close the current activity
                                                } else {
                                                    Toast.makeText(Login.this, "Failed to get token: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(Login.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}