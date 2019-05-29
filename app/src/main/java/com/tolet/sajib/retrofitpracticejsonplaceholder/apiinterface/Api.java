package com.tolet.sajib.retrofitpracticejsonplaceholder.apiinterface;

import com.tolet.sajib.retrofitpracticejsonplaceholder.pojoclass.Modelclass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("posts")
    Call<List<Modelclass>> getclass();
}
