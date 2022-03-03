package com.example.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

public class MainActivity extends AppCompatActivity {
    Button btnEntrar;
    EditText edtSenha, edtLogin;
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEntrar = findViewById(R.id.btnEntrar);
        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        showText = findViewById(R.id.showText);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("key_geral", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("key_login", edtLogin.getText().toString());
                editor.putString("key_pas sword", edtSenha.getText().toString());

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("usuario", edtLogin.getText().toString());
                jsonObject.addProperty("senha", edtSenha.getText().toString());
                auth(jsonObject);
            }
        });
    }

    public void auth(JsonObject jsonObject) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                showMessage(msg);
            }
        };
        Login.loginAuth(handler, jsonObject);
    }

    public void showMessage(Message msg) {
        Toast.makeText(MainActivity.this, "Sucesso " + msg.obj, Toast.LENGTH_SHORT);
        JsonObject token = (JsonObject) msg.obj;
        showText.setText(token.get("token").getAsString());
    }



}