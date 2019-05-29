package com.tolet.sajib.retrofitpracticejsonplaceholder.apiinterface;

import com.tolet.sajib.retrofitpracticejsonplaceholder.pojoclass.Getquery;
import com.tolet.sajib.retrofitpracticejsonplaceholder.pojoclass.Modelclass;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {
//    @GET("posts")
//    Call<List<Getquery>> getclass(
//            @Query("userId") int userId,
//            @Query("_sort") String sort,
//            @Query("_order") String order
//    );
    //here we Implement array of Integer
//@GET("posts")
//Call<List<Getquery>> getclass(
//        @Query("userId") Integer[] userId,
//        @Query("_sort") String sort,
//        @Query("_order") String order
//);


    @GET("posts")
    Call<List<Getquery>> getclass(
            @QueryMap Map<String,String> parameters
    );

    //This is for path parameter
//    @GET("posts/{id}/comments")
//    Call<List<Modelclass>> getmodelclass(
//            @Path("id") int postId);
    @GET()
    Call<List<Modelclass>> getmodelclass(@Url String url);

}
