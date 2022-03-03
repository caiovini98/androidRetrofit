package com.example.retrofit;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login {

    public static void loginAuth(Handler handler, JsonObject jsonObject) {
        Call<JsonObject> login = UserClient.userService().login("dev", jsonObject);

        login.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject body = response.body();
                    Message message = new Message();
                    message.obj = body;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Message message = new Message();
                message.arg1 = 2;
                handler.sendMessage(message);
            }
        });
    }

}
