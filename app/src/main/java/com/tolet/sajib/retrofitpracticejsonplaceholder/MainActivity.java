package com.tolet.sajib.retrofitpracticejsonplaceholder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tolet.sajib.retrofitpracticejsonplaceholder.apiinterface.Api;
import com.tolet.sajib.retrofitpracticejsonplaceholder.pojoclass.Modelclass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private TextView textView;
private String baseurl="https://jsonplaceholder.typicode.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.texid);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
// object create of interface

        Api httpapi=retrofit.create(Api.class);

        Call<List<Modelclass>> call=httpapi.getclass();
        call.enqueue(new Callback<List<Modelclass>>() {
            @Override
            public void onResponse(Call<List<Modelclass>> call, Response<List<Modelclass>> response) {
              if(!response.isSuccessful()){
                 textView.setText("code"+response.code());
                 return;
              }
              List<Modelclass> posts=response.body();
              for(Modelclass post: posts){
               String content="";
               content+="UserID:"+post.getUserId()+"\n";
               content+="postId:"+post.getId()+"\n";
               content+="title:"+post.getTitle()+"\n";
               content+="body:"+post.getBody()+"\n";
               textView.append(content);
              }





            }

            @Override
            public void onFailure(Call<List<Modelclass>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
