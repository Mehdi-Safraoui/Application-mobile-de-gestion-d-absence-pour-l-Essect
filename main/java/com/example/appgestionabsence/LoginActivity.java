package com.example.appgestionabsence;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.checkerframework.checker.nullness.qual.NonNull;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth ;
    private EditText email;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> loginUser());
        firebaseAuth = FirebaseAuth.getInstance();

    }
    private void loginUser() {
        String txt_email = email.getText().toString();
        String txt_password = password.getText().toString();
        System.out.println("test1");
        if (txt_email.isEmpty() || txt_password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("USERS");

        usersRef.orderByChild("email").equalTo(txt_email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //tthabet ken les données mawjoudin bl bd wale
                if (dataSnapshot.exists()) {
                    //boucle for tparcouri el children pta3 noeuds fl bd
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String storedPassword = userSnapshot.child("password").getValue(String.class);
                            if (storedPassword != null && storedPassword.equals(txt_password)) {
                                String role = userSnapshot.child("role").getValue(String.class);
                                if (role != null) {
                                if (role.equals("student")) {
                                    Toast.makeText(LoginActivity.this, "Student login successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                                    startActivity(intent);
                                } else if (role.equals("teacher")) {
                                    Toast.makeText(LoginActivity.this, "Teacher login successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, TeacherActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Other role, handle as needed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Your password is incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Please verif your informations !", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MyTag", "Database error: " + databaseError.getMessage());
            }
        });
    }
}
