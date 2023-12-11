package com.example.appgestionabsence;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVReader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.opencsv.exceptions.CsvValidationException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import androidx.annotation.NonNull;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    List<EditText> editTextList = new ArrayList<>();
    private Button logout;
    private Button terminer;
    private Button dashboardAbsences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        tableLayout = findViewById(R.id.tableLayout);
        try {
            InputStream inputStream = getAssets().open("data.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                addRowToTable(nextLine);
            }
            csvReader.close();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
    private void addRowToTable(String[] rowData) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);
        for (int i = 0; i < rowData.length; i++) {
            if (i == 1) {
                EditText editText = new EditText(this);
                editText.setText(rowData[i]);
                editText.setTextColor(Color.BLACK);
                editText.setPadding(20, 20, 20, 20);
                editText.setGravity(Gravity.CENTER); // Centrer le texte
                editTextList.add(editText);
                row.addView(editText);
            } else {
                TextView textView = new TextView(this);
                textView.setText(rowData[i]);
                textView.setTextColor(Color.BLACK);
                textView.setPadding(20, 20, 20, 20);
                textView.setGravity(Gravity.CENTER);
                row.addView(textView);
            }
        }
        tableLayout.addView(row);
        dashboardAbsences = findViewById(R.id.dashboardAbsences);
        dashboardAbsences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] presenceData = new String[editTextList.size()];
                for (int i = 0; i < editTextList.size(); i++) {
                    EditText editText = editTextList.get(i);
                    String presence = editText.getText().toString();
                    presenceData[i] = presence.isEmpty() ? "N/A" : presence; // Si vide, mettre "N/A"
                }
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TeacherActivity.this, DashboardAbsencesActivity.class);
                intent.putExtra("presenceData", presenceData);
                startActivity(intent);
            }
        });
        terminer= findViewById(R.id.terminer);
        terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(TeacherActivity.this , "appel terminé !" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TeacherActivity.this,StartActivity.class));
            }
        });
        logout= findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(TeacherActivity.this , "Disconnected !" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TeacherActivity.this,StartActivity.class));
            }
        });
    }

}
