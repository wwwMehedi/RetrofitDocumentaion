package com.tolet.sajib.retrofitpracticejsonplaceholder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tolet.sajib.retrofitpracticejsonplaceholder.apiinterface.Api;
import com.tolet.sajib.retrofitpracticejsonplaceholder.pojoclass.Getquery;
import com.tolet.sajib.retrofitpracticejsonplaceholder.pojoclass.Modelclass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private TextView textView;
private String baseurl="https://jsonplaceholder.typicode.com/";
    Api apiinstanceclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.texid);
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
     apiinstanceclass=retrofit.create(Api.class);
    // getpost();
      getcomment();


    }

    private void getpost() {
        Map<String,String> parameters=new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");
        Call<List<Getquery>> call=apiinstanceclass.getclass(parameters);
        call.enqueue(new Callback<List<Getquery>>() {
            @Override
            public void onResponse(Call<List<Getquery>> call, Response<List<Getquery>> response) {
                if(!response.isSuccessful()){
                  //  textView.setText();
                    Toast.makeText(MainActivity.this, "response problem", Toast.LENGTH_SHORT).show();
                }

                List<Getquery> getqueries=response.body();
                for(Getquery g:getqueries){
                    String content="";
                    content+="Userid:"+g.getUserId()+"\n";
                    content+="postid:"+g.getId()+"\n";
                    content+="title:"+g.getTitle()+"\n";
                    content+="body:"+g.getBody()+"\n";
                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Getquery>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "faille connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getcomment() {
        Call<List<Modelclass>> call=apiinstanceclass.getmodelclass("posts/3/comments");
        call.enqueue(new Callback<List<Modelclass>>() {
            @Override
            public void onResponse(Call<List<Modelclass>> call, Response<List<Modelclass>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "not successfull", Toast.LENGTH_SHORT).show();
                }
                List<Modelclass> model=response.body();
                for(Modelclass m:model){
                    String content="";
                    content+="id"+m.getId()+"\n";
                    content+="Postid:"+m.getPostId()+"\n";
                    content+="name:"+m.getName()+"\n";
                    content+="email:"+m.getEmail()+"\n";
                    content+="body: "+m.getBody()+"\n\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Modelclass>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });


    }
}
