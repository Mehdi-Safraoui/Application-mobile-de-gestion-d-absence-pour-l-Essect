package com.example.appgestionabsence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    String nom,prenom;
    FirebaseDatabase db;
    DatabaseReference reference;
    private EditText nomprenom;
    private EditText email;
    private EditText password;
    private Button register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nomprenom=findViewById(R.id.nomprenom);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_nomprenom = nomprenom.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                if(TextUtils.isEmpty(txt_nomprenom) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(RegisterActivity.this,"Des champs sont vides",Toast.LENGTH_SHORT).show();
                } else if(txt_password.length()<6){
                    Toast.makeText(RegisterActivity.this,"Le mot de passe est trop court",Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(txt_email,txt_password,txt_nomprenom);
                }
            }
        });
    }
    private void registerUser(String email,String password,String nomprenom){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"registering user successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,StartActivity.class));
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this,"registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}