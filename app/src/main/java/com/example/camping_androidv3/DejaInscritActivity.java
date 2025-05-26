package com.example.camping_androidv3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DejaInscritActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DejaInscritAdapter adapter;
    private List<String> listActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deja_inscrit_activity);

        recyclerView = findViewById(R.id.recyclerViewDejaInscrit);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listActivities = new ArrayList<>();
        // Ajoutez des activités à la liste (exemple)
        listActivities.add("Activité 1");
        listActivities.add("Activité 2");
        listActivities.add("Activité 3");

        adapter = new DejaInscritAdapter(this, listActivities);
        recyclerView.setAdapter(adapter);

        Button btnRetour = findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(v -> {
            Intent intent = new Intent(DejaInscritActivity.this, CampingActivitiesActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
