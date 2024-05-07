package com.example.day09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day09.data.ApiClient;
import com.example.day09.data.ApiInterface;
import com.example.day09.data.model.Login;
import com.example.day09.data.model.LoginData;
import com.example.day09.data.model.SessionManager;
import com.example.day09.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    String Username, Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnlog1.setOnClickListener(v -> {
            Username = binding.etlog1.getText().toString();
            Password = binding.etlog2.getText().toString();
            Login1(Username, Password);

        });


        binding.tvlog1.setOnClickListener(v -> {
            if (v.getId() == R.id.tvlog1) {
                Intent intent = new Intent(this, register.class);
                startActivity(intent);
            }
        });

    }

    private void Login1(String username, String password) {
        ApiInterface apiinterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiinterface.loginresponse(username, password);

        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {

                    SessionManager sessionManager = new SessionManager(login.this);
                    LoginData loginData = response.body().getData();
                    sessionManager.createLoginSession(loginData);

                    Toast.makeText(login.this, response.body().getData().getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}