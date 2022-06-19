package com.example.javaquizbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    TextView textView,createAccount,already;
    EditText fullName,email,pass,phone;
    Button register;
     FirebaseAuth fauth;
   //  ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView=findViewById(R.id.textView);
        createAccount=findViewById(R.id.createAccount);
        already=findViewById(R.id.already);
        fullName=findViewById(R.id.fullName);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        phone=findViewById(R.id.phone);
        register=findViewById(R.id.login);
        fauth=FirebaseAuth.getInstance();
       // progressBar=findViewById(R.id.progressBar);
        if(fauth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String memail=email.getText().toString().trim();
              String mPassword=pass.getText().toString().trim();
              if(TextUtils.isEmpty(memail))
              {
                  email.setError("Email is required");
                  return;
              }
                if(TextUtils.isEmpty(mPassword))
                {
                    pass.setError("Password is required");
                    return;
                }
                if(mPassword.length()<6)
                {
                    pass.setError("Password must be >= to 6 characters");
                    return;
                }
      //          progressBar.setVisibility(View.VISIBLE);
                fauth.createUserWithEmailAndPassword(memail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "Account created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));//after register we will send user to main activity
                        }
                        else
                        {
                            Toast.makeText(Register.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Signin.class));
            }
        });
    }

}