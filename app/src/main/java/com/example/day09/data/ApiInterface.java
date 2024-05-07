package com.example.day09.data;

import com.example.day09.data.model.Login;
import com.example.day09.data.model.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
     Call<Login> loginresponse(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerresponse(
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name
    );
}
