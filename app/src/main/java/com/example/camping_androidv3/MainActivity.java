package com.example.camping_androidv3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camping_androidv3.API.ApiClient;
import com.example.camping_androidv3.API.ApiService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button connexionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.Login);
        passwordEditText = findViewById(R.id.MDP);
        connexionButton = findViewById(R.id.ConnexionButton);

        connexionButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            ApiService apiService = ApiClient.getApiService();
            apiService.connexion(email, password).enqueue(new Callback<Map<String, Object>>() {
                @Override
                public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Map<String, Object> responseBody = response.body();
                        String status = (String) responseBody.get("status");

                        if ("ok".equals(status)) {
                            // Récupérer idCompte selon ta réponse JSON
                            // Parfois Retrofit parse les nombres en Double, donc on convertit correctement
                            Object idCompteObj = responseBody.get("id_compte"); // clé conforme à ta réponse JSON
                            int idCompte = -1;
                            if (idCompteObj instanceof Double) {
                                idCompte = ((Double) idCompteObj).intValue();
                            } else if (idCompteObj instanceof Integer) {
                                idCompte = (int) idCompteObj;
                            }

                            // Sauvegarde dans SharedPreferences
                            SharedPreferences prefs = getSharedPreferences("CampingPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("idCompte", idCompte);
                            editor.apply();

                            Toast.makeText(MainActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, CampingActivitiesActivity.class));
                            finish(); // optionnel pour ne pas revenir sur la connexion
                        } else {
                            String message = (String) responseBody.get("message");
                            Toast.makeText(MainActivity.this, message != null ? message : "Erreur inconnue", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Erreur de réponse du serveur: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Erreur de connexion au serveur: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        });
    }
}
