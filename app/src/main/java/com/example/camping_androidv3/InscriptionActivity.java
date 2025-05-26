package com.example.camping_androidv3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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

    private Button btnInscrire;

    private final OkHttpClient client = new OkHttpClient();
    private static final String URL_INSCRIPTION = "http://10.0.2.2:8080/inscription/insertOrUpdateInscription";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private int idCreneau;  // Récupérer de l'Intent pour identifier la créneau d'activité

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // Affichage des infos
        TextView titreTextView = findViewById(R.id.tvNomAnimation);
        TextView lieuTextView = findViewById(R.id.tvLieu);
        TextView dateTextView = findViewById(R.id.tvDateHeure);
        TextView descriptionTextView = findViewById(R.id.tvDescriptif);
        ImageView imageView = findViewById(R.id.imageView);
        btnInscrire = findViewById(R.id.btnInscrire);

        Intent intent = getIntent();
        String titre = intent.getStringExtra("titre");
        String lieu = intent.getStringExtra("lieu");
        String date = intent.getStringExtra("date");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        idCreneau = intent.getIntExtra("idCreneau", -1);

        titreTextView.setText(titre);
        lieuTextView.setText(lieu);
        dateTextView.setText(date);
        descriptionTextView.setText(description);

        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.default_image)
                .into(imageView);

        btnInscrire.setOnClickListener(v -> inscrireUtilisateur());
    }

    private void inscrireUtilisateur() {
        SharedPreferences prefs = getSharedPreferences("CampingPrefs", MODE_PRIVATE);
        int idCompte = prefs.getInt("idCompte", -1);


        if (idCompte == -1) {
            Toast.makeText(this, "Utilisateur non connecté", Toast.LENGTH_SHORT).show();
            return;
        }

        if (idCreneau == -1) {
            Toast.makeText(this, "Créneau d'activité invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        // Récupérer la date d'inscription : ici on met la date actuelle par exemple
        String dateInscription = java.time.LocalDate.now().toString(); // "2025-05-25"

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idCompte", idCompte);

            JSONObject creneauxObject = new JSONObject();
            creneauxObject.put("id_creneaux", idCreneau);

            JSONObject inscriptionObject = new JSONObject();
            inscriptionObject.put("date_inscription", dateInscription);

            jsonObject.put("creneaux", creneauxObject);
            jsonObject.put("inscription", inscriptionObject);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors de la préparation de la requête", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(URL_INSCRIPTION)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(InscriptionActivity.this, "Erreur réseau", Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(() ->
                            Toast.makeText(InscriptionActivity.this, "Inscription réussie !", Toast.LENGTH_LONG).show()
                    );
                } else {
                    runOnUiThread(() ->
                            Toast.makeText(InscriptionActivity.this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show()
                    );
                }
            }
        });
    }

}
