package com.example.camping_androidv3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camping_androidv3.API.ApiClient;
import com.example.camping_androidv3.API.ApiService;
import com.example.camping_androidv3.ActivityLayout.MenuMainActivity;

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
                            Toast.makeText(MainActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, MenuMainActivity.class));
                        } else {
                            String message = (String) responseBody.get("message");
                            Toast.makeText(MainActivity.this, message != null ? message : "Erreur inconnue", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Erreur de réponse du serveur", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Erreur de connexion au serveur", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
