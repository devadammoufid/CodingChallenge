package com.unitedremote.codingchallenge.interfaces;

import com.unitedremote.codingchallenge.model.Repositories;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RetrofitClient {

    @GET("/search/repositories")
    Call<Repositories> getRepositoryList(@QueryMap(encoded = false) Map<String,String> filter );

}
