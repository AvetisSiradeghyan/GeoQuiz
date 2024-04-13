package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class LogIn extends AppCompatActivity {

    public static final String Tag = "Tag";

    public static boolean user = false;
    private FirebaseAuth auth;
    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private TextView signupRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        
      auth = FirebaseAuth.getInstance();
      loginPassword = findViewById(R.id.login_password);
      loginEmail = findViewById(R.id.login_email);
      loginButton = findViewById(R.id.login_button);
      signupRedirectText = findViewById(R.id.signupRedirectText);

      loginButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String email = loginEmail.getText().toString();
              String pass = loginPassword.getText().toString();

              if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                  if (!pass.isEmpty()){
                      user = true;
                      auth.signInWithEmailAndPassword(email, pass)
                              .addOnSuccessListener(new OnSuccessListener<AuthResult>()

                              {
                                  @Override
                                  public void onSuccess(AuthResult authResult) {


                                      if (Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified()) {
                                          Toast.makeText(LogIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                          startActivity(new Intent(LogIn.this, MainActivity.class));
                                          finish();
                                      }else {
                                          Toast.makeText(LogIn.this, "Please Verify", Toast.LENGTH_SHORT).show();
                                          auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                              @Override    public void onSuccess(Void aVoid) {
                                                  Toast.makeText(LogIn.this, "Verification code has been  Sent", Toast.LENGTH_SHORT).show();
                                              }}).addOnFailureListener(new OnFailureListener() {
                                              @Override
                                              public void onFailure(@NonNull Exception e) {
                                                  Log.d(Tag, "onFailure: email not sent" + e.getMessage());
                                              }
                                          });
                                      }


                                  }
                              }).addOnFailureListener(new OnFailureListener() {
                                  @Override
                                  public void onFailure(@NonNull Exception e) {
                                      Toast.makeText(LogIn.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                  }
                              });
                  }else {
                      loginPassword.setError("Password cannot be empty");
                  }
              } else if (email.isEmpty()) {
                  loginEmail.setError("Email cannot be empty");
              }else {
                  loginEmail.setError("Please enter valid email");
              }
          }
      });


        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, Registration.class));
            }
        });



    }
}