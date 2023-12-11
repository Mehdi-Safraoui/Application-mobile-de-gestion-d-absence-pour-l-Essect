package com.example.appgestionabsence;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
/*
        // Récupérer les statuts des étudiants depuis les préférences partagées et les mettre à jour
        updateStudentStatus("dev mobile");
        updateStudentStatus("integration web");
        updateStudentStatus("data-mining");
        updateStudentStatus("ia");
        updateStudentStatus("gestion de projet");
*/
        logout= findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(StudentActivity.this , "Disconnected !" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StudentActivity.this,StartActivity.class));
            }
        });
    }

    // Méthode pour mettre à jour le statut des étudiants dans les TextViews
    // Méthode pour mettre à jour le statut des étudiants dans les TextViews
 /*   private void updateStudentStatus(String subject) {
        SharedPreferences sharedPref = getSharedPreferences("StudentStatus", Context.MODE_PRIVATE);

        // Récupération des statuts pour chaque matière
        String statusDevMobile = sharedPref.getString("dev mobile", "pas encore");
        String statusIntegrationWeb = sharedPref.getString("integration web", "pas encore");
        String statusDataMining = sharedPref.getString("data-mining", "pas encore");
        String statusIA = sharedPref.getString("ia", "pas encore");
        String statusGestionProjet = sharedPref.getString("gestion de projet", "pas encore");
        // Ajoutez d'autres lignes pour les autres matières si nécessaire

        // Mettez à jour le TextView correspondant à la matière
        switch (subject) {
            case "dev mobile":
                TextView textViewDevMobile = findViewById(R.id.textViewDevMobile);
                textViewDevMobile.setText(statusDevMobile);
                break;
            case "integration web":
                TextView textViewIntegrationWeb = findViewById(R.id.textViewIntegrationWeb);
                textViewIntegrationWeb.setText(statusIntegrationWeb);
                break;
            case "data-mining":
                TextView textViewDataMining = findViewById(R.id.textViewDataMining);
                textViewDataMining.setText(statusDataMining);
                break;
            case "ia":
                TextView textViewIA = findViewById(R.id.textViewIA);
                textViewIA.setText(statusIA);
                break;
            case "gestion de projet":
                TextView textViewGestionProjet = findViewById(R.id.textViewGestionProjet);
                textViewGestionProjet.setText(statusGestionProjet);
                break;
            // Ajoutez des cas pour chaque matière avec le bon TextView correspondant
        }
    }
*/
}
