package com.example.camping_androidv3.API;

import com.example.camping_androidv3.CampingActivity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import java.util.Map;

import java.util.List;

public interface ApiService {

    @FormUrlEncoded
    @POST("/auth/login")
    Call<Boolean> login(@Field("email") String email, @Field("password") String password);

    @GET("/creneaux/allCreneaux")
    Call<List<CampingActivity>> getCampingActivities();

    @FormUrlEncoded
    @POST("/auth/connexion")
    Call<Map<String, Object>> connexion(@Field("email") String email, @Field("password") String password);

}
