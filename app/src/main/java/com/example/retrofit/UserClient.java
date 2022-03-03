package com.example.retrofit;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserClient {

    static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            return retrofit = new Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();
        }

        return retrofit;
    }

    public static UserService userService() {
        return getInstance().create(UserService.class);
    }
}
