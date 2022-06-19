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

public class Signin extends AppCompatActivity {
    TextView textView1,already1;
    EditText email1,pass2;
    Button login;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        textView1=findViewById(R.id.textView1);
        already1=findViewById(R.id.already1);
        email1=findViewById(R.id.email1);
        pass2=findViewById(R.id.pass2);
        login=findViewById(R.id.login);
       fauth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String memaill=email1.getText().toString().trim();
                String mPasswordd=pass2.getText().toString().trim();
                if(TextUtils.isEmpty(memaill))
                {
                    email1.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(mPasswordd))
                {
                    pass2.setError("Password is required");
                    return;
                }
                if(mPasswordd.length()<6)
                {
                    pass2.setError("Password must be >= to 6 characters");
                    return;
                }
                //authenticate the user
                fauth.signInWithEmailAndPassword(memaill,mPasswordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Signin.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));//after register we will send user to main activity
                        }
                        else
                        {
                            Toast.makeText(Signin.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        already1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}