package com.example.mykloud.viewModels;

import androidx.lifecycle.LiveData;

import com.example.mykloud.models.ListModel;
import com.example.mykloud.repo.ListRepo;

import java.util.List;

public class MainActivityViewModel extends androidx.lifecycle.ViewModel {
    private final ListRepo listRepo;

    public MainActivityViewModel() {
        listRepo = ListRepo.getInstance();
    }


    public LiveData<List<ListModel>> getAllLists () {
        return listRepo.getLists();
    }

    public void deleteList(List<String> selectedID) {
        listRepo.deleteLists(selectedID);
    }
}
