package com.example.mready.Http;

import com.example.mready.Clase.DataUser;
import com.example.mready.Clase.UserAndToken;
import com.example.mready.Clase.MyData;
import com.example.mready.Clase.PostData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface JsonPlaceHolder {

    @GET("posts")
    Call<MyData> getMessages();

    @POST("posts")
    @FormUrlEncoded
    Call<PostData> createMessage(@Field("message") String message, @Header("Authorization") String token);

    @POST("auth/register")
    @FormUrlEncoded
    Call<DataUser> registerUser(@Field("Username") String username, @Field("Password") String password, @Field("Display_name") String displayName);

    @POST("auth/login")
    @FormUrlEncoded
    Call<DataUser> loginUser (@Field("Username") String username, @Field("Password") String password);
}
