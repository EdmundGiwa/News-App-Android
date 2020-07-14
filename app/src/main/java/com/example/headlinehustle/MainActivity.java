package com.example.headlinehustle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.headlinehustle.Model.Articles;
import com.example.headlinehustle.Model.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    final String API_KEY = "4138f39d774444a3bb3b647b29dc0fe1";

    Adapter adapter;
    List<Articles> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(255,255,255));
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.displayNews_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        String country = getCountry();
retreive_json(country,API_KEY);
    }

    public void retreive_json(String myCountry, String apiKey){
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(myCountry,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry(){
    Locale locale = Locale.getDefault();
    String country = locale.getCountry();
    return country.toLowerCase();
    }
}
