package com.example.camping_androidv3;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.camping_androidv3.API.ApiClient;
import com.example.camping_androidv3.API.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampingActivitiesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CampingActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_activities);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Appel à l'API pour récupérer les activités
        loadCampingActivities();
    }


    private void loadCampingActivities() {
        ApiService apiService = ApiClient.getApiService();
        Call<List<CampingActivity>> call = apiService.getCampingActivities();

        call.enqueue(new Callback<List<CampingActivity>>() {
            @Override
            public void onResponse(Call<List<CampingActivity>> call, Response<List<CampingActivity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CampingActivity> activities = response.body();
                    adapter = new CampingActivityAdapter(activities);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(CampingActivitiesActivity.this, "Aucune activité trouvée", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CampingActivity>> call, Throwable t) {
                Toast.makeText(CampingActivitiesActivity.this, "Erreur de chargement des activités", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
