package com.example.mykloud.services;

import com.example.mykloud.models.ListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {


    @GET("/lists")
    Call<List<ListModel>> fetchAllLists();
}
