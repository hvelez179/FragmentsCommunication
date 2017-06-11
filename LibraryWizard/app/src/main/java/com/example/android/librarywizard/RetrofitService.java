package com.example.android.librarywizard;

import com.example.android.librarywizard.Entities.RandomAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetrofitService {

    @GET("api")
    Call<RandomAPI> getRandomUser();
}


