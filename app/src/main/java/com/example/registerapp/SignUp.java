package com.example.registerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText email=findViewById(R.id.usernameET);
        final EditText mobile=findViewById(R.id.phoneET);
        final EditText passwordET=findViewById(R.id.passwordET);
        final EditText nameET=findViewById(R.id.nameET);
        final EditText confirmpasswordET=findViewById(R.id.confirmpasswordET);
        final TextView signinBtn=findViewById(R.id.loginBtn);
        final Button signupBtn=findViewById(R.id.signupBtn);



    signupBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       final String getmobiletext=mobile.getText().toString();
        final String getemailtext=email.getText().toString();
        Intent intent=new Intent(SignUp.this,OTPVerification.class);
        intent.putExtra("email",getemailtext);
        startActivity(intent);
    }
});
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }  });
    }
}