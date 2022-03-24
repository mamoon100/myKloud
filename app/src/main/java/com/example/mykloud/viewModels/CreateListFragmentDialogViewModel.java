package com.example.mykloud.viewModels;

import androidx.lifecycle.ViewModel;

import com.example.mykloud.repo.ListRepo;

public class CreateListFragmentDialogViewModel extends ViewModel {
    private final ListRepo listRepo;


    public CreateListFragmentDialogViewModel() {
        this.listRepo = ListRepo.getInstance();
    }


    public void createList(String name, String priority) {
        listRepo.createList(name, priority);
    }

    public void updateList(String id, String name, String priority) {
        listRepo.updateList(id, name, priority);

    }
}
