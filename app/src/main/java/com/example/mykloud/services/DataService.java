package com.example.mykloud.services;

import com.example.mykloud.models.ListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {


    @GET("/lists")
    Call<List<ListModel>> fetchAllLists();


    @GET("/lists/{id}")
    Call<ListModel> fetchSpecifiedList(@Path("id") String id);


    @FormUrlEncoded
    @POST("/lists")
    Call<ListModel> createList(@Field(encoded = true, value = "name") String name, @Field(encoded = true, value = "priority") String priority);


    @FormUrlEncoded
    @PUT("/lists/{id}")
    Call<ListModel> updateList(@Path("id") String id, @Field(encoded = true, value = "name") String name, @Field(encoded = true, value = "priority") String priority);


    @DELETE("/lists/{id}")
    Call<ListModel> deleteLists(@Path("id") String id);


}
