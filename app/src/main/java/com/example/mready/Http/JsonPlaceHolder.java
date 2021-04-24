package com.example.mready.Http;

import com.example.mready.Clase.GetMessage;
import com.example.mready.Clase.MyData;
import com.example.mready.Clase.PostData;
import com.example.mready.Clase.PostMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolder {

    @GET("posts")
    Call<MyData> getMessages();

    @POST("posts")
    @FormUrlEncoded
    Call<PostData> createMessage(@Field("message") String message);
}
