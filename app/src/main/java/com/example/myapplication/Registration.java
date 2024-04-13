package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Registration extends AppCompatActivity {

    public static final String Tag = "Tag";
    private String pass;
    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword, conf;
    private Button signupButton;
    private TextView loginRedirectText;
    boolean isValid = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        conf = findViewById(R.id.signup_passwordconf);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                if (conf.getText().toString().equals(signupPassword.getText().toString())) {
                    isValid = true;
                }
                else {
                    isValid = false;
                    Toast.makeText(Registration.this, "Password doesnot match", Toast.LENGTH_SHORT).show();
                }

                if(user.isEmpty()){
                    signupEmail.setError("Email cannot be empty");
                }else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful() && isValid){


                                        Toast.makeText(Registration.this, "Sign Up Successfuly", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Registration.this, LogIn.class));
                                    }else {
                                Toast.makeText(Registration.this, "Sign Up Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }




                                    Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override    public void onSuccess(Void aVoid) {
                                    Toast.makeText(Registration.this, "Verification code has been  Sent", Toast.LENGTH_SHORT).show();
                                }}).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(Tag, "onFailure: email not sent" + e.getMessage());
                                }
                            });

                        }

                    });
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, LogIn.class));
            }
        });




    }
}