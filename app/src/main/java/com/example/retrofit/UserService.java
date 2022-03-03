package com.example.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @Headers({"Content-Type:application/json"})
    @POST("mobile/auth/login")
    Call<JsonObject> login(@Header("Client") String cliente, @Body JsonObject body);
}
