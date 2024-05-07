package com.example.day09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.day09.data.ApiClient;
import com.example.day09.data.ApiInterface;
import com.example.day09.data.model.Register;
import com.example.day09.databinding.ActivityLoginBinding;
import com.example.day09.databinding.ActivityRegisterBinding;

import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvreg1.setOnClickListener(v -> {
            if (v.getId() == R.id.tvreg1) {
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
            }
        });
        binding.btnreg1.setOnClickListener(v -> {
            String username = binding.etreg1.getText().toString();
            String nama = binding.etreg2.getText().toString();
            String password = binding.etreg3.getText().toString();
             register(username, password, nama);
        });



    }
    private void register (String username, String password, String nama){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.registerresponse(username, password, nama);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful() && response.body() != null){
                    Intent intent = new Intent(register.this, login.class);
                    Toast.makeText(register.this, "akun berhasil dibuat", Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }else {
                    Toast.makeText(register.this,response.body().toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(register.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}