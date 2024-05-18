package com.example.residencesafia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    Button signInButton;
    EditText firstName, lastName, phoneNumber, email, password;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.register);
        signInButton = findViewById(R.id.button);
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameContent = firstName.getText().toString();
                String lastNameContent = lastName.getText().toString();
                String phoneNumberContent = phoneNumber.getText().toString();
                String emailContent = email.getText().toString();
                String passwordContent = password.getText().toString();
                Log.w(TAG, firstNameContent);
                Log.w(TAG, lastNameContent);
                Log.w(TAG, phoneNumberContent);
                Log.w(TAG, emailContent);
                Log.w(TAG, passwordContent);
                mAuth.createUserWithEmailAndPassword(emailContent, passwordContent)
                        .addOnCompleteListener(Register.this ,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign up success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    // TODO: Add user data to Firebase Firestore or Realtime Database
                                    updateUI(user);
                                } else {
                                    // If sign up fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) { // Création de compte avec succes
            Toast.makeText(getApplicationContext(), "Création de compte avec succes.",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Login.class));
            finish();
        } else { // échec de création de compte
            Toast.makeText(getApplicationContext(), "échec de création de compte.",
                    Toast.LENGTH_SHORT).show();
            signInButton.setVisibility(View.VISIBLE);
            firstName.setVisibility(View.VISIBLE);
            lastName.setVisibility(View.VISIBLE);
            phoneNumber.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
        }
    }
}
