package com.example.mykloud.repo;

import com.example.mykloud.BuildConfig;
import com.example.mykloud.services.DataService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitBuilderRepo {
    public static DataService getClient () {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(DataService.class);
    }
}
