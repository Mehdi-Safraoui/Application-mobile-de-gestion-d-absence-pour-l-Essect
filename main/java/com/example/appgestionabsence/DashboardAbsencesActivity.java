package com.example.appgestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
public class DashboardAbsencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_absences);

        // Récupérer les données transmises via l'intent
        String[] presenceData = getIntent().getStringArrayExtra("presenceData");

        // Récupérer la liste des noms des étudiants depuis le CSV
        String[] studentNames = {
                "Ahmed Amine Drira", "Mehdi Safraoui", "Feres Belekhdher",
                "Koussay Bejaoui", "Oumaima Selmi", "Khaoula Khayati",
                "Mohamed Amine Sehiri", "Nermine Riabi", "Ghofrane Lamiri",
                "Sabrine Gooli", "Firas Balti"
        };

        // Trouver le layout dans lequel vous souhaitez ajouter les TextViews
        LinearLayout layout = findViewById(R.id.layoutToAddTextViews);

        // Ajouter dynamiquement des TextViews pour chaque nom et statut
        for (int i = 0; i < studentNames.length; i++) {
            // Vérifier si le nom est différent de "présence/absence"
            if (!studentNames[i].equals("présence/absence")) {
                String studentName = studentNames[i];
                String status = presenceData[i];

                TextView textView = new TextView(this);
                textView.setText(studentName + " : " + status);
                layout.addView(textView);
            }
        }
    }
}
