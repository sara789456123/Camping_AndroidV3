package com.example.camping_androidv3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InscriptionActivity extends AppCompatActivity {

    private EditText prenomInput, nomInput, emailInput, mdpInput, mdpInput2;
    private Button submitButton;

    private final OkHttpClient client = new OkHttpClient();
    private static final String URL = "https://ton-api.com/api/register"; // Remplace par l'URL réelle de ton API

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_activity); // Vérifie que le fichier XML s'appelle bien "inscription.xml" et non "Inscription.xml"

        prenomInput = findViewById(R.id.prenomInput);
        nomInput = findViewById(R.id.nomInput);
        emailInput = findViewById(R.id.emailInput);
        mdpInput = findViewById(R.id.mdpInput);
        mdpInput2 = findViewById(R.id.mdpInput2);
        submitButton = findViewById(R.id.submitInscriptionButton);

        submitButton.setOnClickListener(v -> {
            String prenom = prenomInput.getText().toString().trim();
            String nom = nomInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String mdp = mdpInput.getText().toString();
            String mdp2 = mdpInput2.getText().toString();

            if (!mdp.equals(mdp2)) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject json = new JSONObject();
                json.put("prenom", prenom);
                json.put("nom", nom);
                json.put("email", email);
                json.put("password", mdp);

                // RequestBody body = RequestBody.create(json.toString(), JSON);

                Request request = new Request.Builder()
                        .url(URL)
                        //.post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Capture la trace complète de l'erreur
                        Log.e("InscriptionActivity", "Erreur de connexion : ", e);
                        runOnUiThread(() ->
                                Toast.makeText(InscriptionActivity.this, "Erreur de connexion : " + e.getMessage(), Toast.LENGTH_LONG).show()
                        );
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        runOnUiThread(() -> {
                            if (response.isSuccessful()) {
                                try {
                                    // Récupérer la réponse JSON
                                    String responseBody = response.body().string();
                                    JSONObject jsonResponse = new JSONObject(responseBody);

                                    // Extraire le token
                                    String token = jsonResponse.getString("token");

                                    // Stocker le token dans SharedPreferences
                                    SharedPreferences preferences = getSharedPreferences("MyApp", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("token", token);
                                    editor.apply(); // Enregistrer

                                    Toast.makeText(InscriptionActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                                    finish(); // Ferme l'activité après inscription
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(InscriptionActivity.this, "Erreur de traitement", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(InscriptionActivity.this, "Erreur d'inscription", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erreur lors de la création de la requête", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
