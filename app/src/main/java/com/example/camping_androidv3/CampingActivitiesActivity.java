package com.example.camping_androidv3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camping_androidv3.API.ApiClient;
import com.example.camping_androidv3.API.ApiService;
import com.example.camping_androidv3.Model.CampingActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampingActivitiesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CampingActivityAdapter adapter;
    private List<CampingActivity> listActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_activities); // Assurez-vous que c'est le bon layout

        // Gestion des boutons
        Button btnDeconnexion = findViewById(R.id.btnDeconnexion);
        Button btnVoirMesActivites = findViewById(R.id.btnVoirMesActivites);

        btnDeconnexion.setOnClickListener(v -> {
            // Logique pour la déconnexion
            Intent intent = new Intent(CampingActivitiesActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btnVoirMesActivites.setOnClickListener(v -> {
            // Logique pour voir mes activités
            Intent intent = new Intent(CampingActivitiesActivity.this, DejaInscritActivity.class);
            startActivity(intent);
        });

        // Configuration du RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listActivities = new ArrayList<>();
        adapter = new CampingActivityAdapter(this, listActivities);
        recyclerView.setAdapter(adapter);

        loadCampingActivities(); // Charge les données depuis l’API
    }

    private void loadCampingActivities() {
        ApiService apiService = ApiClient.getApiService();
        Call<List<CampingActivity>> call = apiService.getCampingActivities();

        call.enqueue(new Callback<List<CampingActivity>>() {
            @Override
            public void onResponse(Call<List<CampingActivity>> call, Response<List<CampingActivity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listActivities.clear();
                    listActivities.addAll(response.body());
                    adapter.notifyDataSetChanged(); // Rafraîchit l’adaptateur
                } else {
                    Toast.makeText(CampingActivitiesActivity.this, "Aucune activité trouvée", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CampingActivity>> call, Throwable t) {
                Toast.makeText(CampingActivitiesActivity.this, "Erreur de chargement", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
