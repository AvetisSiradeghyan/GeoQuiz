package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

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
                    isValid = false;
                }
                else {
                    isValid = true;
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