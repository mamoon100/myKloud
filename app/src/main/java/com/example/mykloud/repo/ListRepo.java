package com.example.mykloud.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mykloud.models.ListModel;
import com.example.mykloud.services.DataService;

import java.util.ArrayList;
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
        lists = new MutableLiveData<>(new ArrayList<>());
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
                lists.setValue(new ArrayList<>());
            }
        });
    }

    public void createList (String name, String priority) {
        service.createList(name, priority).enqueue(new Callback<ListModel>() {
            @Override
            public void onResponse(Call<ListModel> call, Response<ListModel> response) {
                fetchLists();
            }

            @Override
            public void onFailure(Call<ListModel> call, Throwable t) {

            }
        });
    }


    public void updateList(String id, String name, String priority) {
        service.updateList(id, name, priority).enqueue(new Callback<ListModel>() {
            @Override
            public void onResponse(Call<ListModel> call, Response<ListModel> response) {
                fetchLists();
            }

            @Override
            public void onFailure(Call<ListModel> call, Throwable t) {

            }
        });
    }



    public void deleteLists (List<String> selectedID) {
        if (selectedID.size() > 0) {
            service.deleteLists(selectedID.get(0)).enqueue(new Callback<ListModel>() {
                @Override
                public void onResponse(Call<ListModel> call, Response<ListModel> response) {
                    selectedID.remove(0);
                    deleteLists(selectedID);
                }

                @Override
                public void onFailure(Call<ListModel> call, Throwable t) {
                    fetchLists();
                }
            });
        } else {
            fetchLists();
        }
    }


    public LiveData<List<ListModel>> getLists() {
        return lists;
    }
}
