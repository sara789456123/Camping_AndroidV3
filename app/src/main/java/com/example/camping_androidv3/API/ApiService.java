package com.example.camping_androidv3.API;

import com.example.camping_androidv3.Model.CampingActivity;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("auth/connexion")
    Call<Map<String, Object>> connexion(@Field("email") String email, @Field("password") String password);

    @GET("creneaux/infosActivites")
    Call<List<CampingActivity>> getCampingActivities();

    @FormUrlEncoded
    @POST("inscription/insertOrUpdateInscription")
    Call<Map<String, Object>> inscrireUtilisateur(
            @Field("prenom") String prenom,
            @Field("nom") String nom,
            @Field("email") String email,
            @Field("mot_de_passe") String motDePasse
    );


}
