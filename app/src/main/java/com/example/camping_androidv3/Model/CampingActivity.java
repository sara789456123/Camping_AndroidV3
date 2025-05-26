package com.example.camping_androidv3.Model;

import com.google.gson.annotations.SerializedName;

public class CampingActivity {
    @SerializedName("nom_animation")
    private String nomAnimation;

    @SerializedName("nom_lieu")
    private String nomLieu;

    @SerializedName("date_heure")
    private String dateHeure;

    @SerializedName("descriptif_animation")
    private String descriptifAnimation;

    @SerializedName("img")
    private String img;

    @SerializedName("id_creneau")  // S'assure que la clé JSON "id_creneau" est bien mappée
    private int idCreneau;

    // Getters
    public String getNomAnimation() { return nomAnimation; }
    public String getNomLieu() { return nomLieu; }
    public String getDateHeure() { return dateHeure; }
    public String getDescriptifAnimation() { return descriptifAnimation; }
    public String getImg() { return img; }
    public int getIdCreneau() { return idCreneau; }  // Ajout du getter pour l'ID
}
