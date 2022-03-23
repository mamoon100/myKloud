package com.example.mykloud.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mykloud.models.ListModel;
import com.example.mykloud.services.DataService;

import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRepo {
    private static volatile ListRepo INSTANCE;
    private final MutableLiveData<List<ListModel>> lists;
    private final DataService service;

    private ListRepo() {
        lists = new MutableLiveData<>();
        service = RetroFitBuilderRepo.getClient();
        fetchLists();
    }


    public static ListRepo getInstance() {
        if (INSTANCE == null) {
            synchronized (ListRepo.class) {
                if (INSTANCE == null) INSTANCE = new ListRepo();
            }
        }
        return INSTANCE;
    }


    private void fetchLists() {
        service.fetchAllLists().enqueue(new Callback<List<ListModel>>() {
            @Override
            public void onResponse(Call<List<ListModel>> call, Response<List<ListModel>> response) {
                if (response.isSuccessful())
                    lists.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ListModel>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<ListModel>> getLists() {
        return lists;
    }
}
