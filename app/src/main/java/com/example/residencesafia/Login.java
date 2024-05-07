package com.example.residencesafia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;

public class Login extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    Button signInButton,signUpButton;
    EditText email;
    EditText password;
    String emailContent , passwordContent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.login);
        signInButton = findViewById(R.id.button);
        signUpButton = findViewById(R.id.SignUpButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailContent = email.getText().toString();
                passwordContent = password.getText().toString();

                Log.w(TAG, emailContent);
                Log.w(TAG, passwordContent);
                mAuth.signInWithEmailAndPassword(emailContent, passwordContent)
                        .addOnCompleteListener(Login.this ,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    Toast.makeText(getApplicationContext(), "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
