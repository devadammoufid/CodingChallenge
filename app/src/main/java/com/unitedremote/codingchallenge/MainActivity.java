package com.unitedremote.codingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.unitedremote.codingchallenge.interfaces.RetrofitClient;
import com.unitedremote.codingchallenge.model.Repositories;
import com.unitedremote.codingchallenge.model.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "RepResponse";
    private Repositories repositoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map<String, String> data = new HashMap<>();
        data.put("q", "created:>2017-10-22");
        data.put("sort", "stars");
        data.put("order", "desc");

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);

        Call<Repositories> repositoryListCall= retrofitClient.getRepositoryList(data);
        repositoryListCall.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,
                            " Sucessful",
                            Toast.LENGTH_SHORT).show();
                    repositoriesList = response.body();
                    List<Repository> repositories = repositoriesList.getItems();
//                    prepareData(repositoriesList);
                    Log.i(TAG, repositories.get(0).getmName());
                } else {


                    Log.d(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Request failed. Check your internet connection",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
