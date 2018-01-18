package com.example.android.classexamappa.data;

import com.example.android.examproject.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("api")
    Call<User> getUsers(@Query("results") int count);

}