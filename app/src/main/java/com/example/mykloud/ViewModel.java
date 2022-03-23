package com.example.mykloud;

import androidx.lifecycle.LiveData;

import com.example.mykloud.models.ListModel;
import com.example.mykloud.repo.ListRepo;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private final ListRepo listRepo;

    public ViewModel() {
        listRepo = ListRepo.getInstance();
    }


    public LiveData<List<ListModel>> getAllLists () {
        return listRepo.getLists();
    }
}
