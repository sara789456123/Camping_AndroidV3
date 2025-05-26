package com.example.camping_androidv3.Model;

public class Activite {
    public String titre;
    public String lieu;
    public String date;
    public String description;
    public String imageUrl;
    public int id_creneaux;


    public Activite(String titre, String lieu, String date, String description, String imageUrl,int idCreneaux) {
        this.titre = titre;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.imageUrl = imageUrl;
        this.id_creneaux = idCreneaux;
    }
}
