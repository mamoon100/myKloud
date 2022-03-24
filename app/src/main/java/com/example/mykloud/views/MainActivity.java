package com.example.mykloud.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mykloud.R;
import com.example.mykloud.adapters.ListAdapter;
import com.example.mykloud.fragments.CreateListFragment;
import com.example.mykloud.listners.ListListener;
import com.example.mykloud.models.ListModel;
import com.example.mykloud.viewModels.MainActivityViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListListener {
    MainActivityViewModel viewModel;
    RecyclerView listView;
    List<ListModel> lists;
    ListAdapter adapter;
    FloatingActionButton plusButton;
    FloatingActionButton minusButton;
    ProgressBar progressBar;
    CreateListFragment createListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListener();
        initObserver();

    }

    public void init() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        listView = findViewById(R.id.listView);
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        minusButton.hide();
        listView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        progressBar = findViewById(R.id.progressBar);
        createListFragment = CreateListFragment.newInstance();
    }

    public void initListener() {
        plusButton.setOnClickListener(v -> {
            showFragment(createListFragment);
        });

        minusButton.setOnClickListener(v -> {
            minusButton.hide();
            progressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            viewModel.deleteList(adapter.getSelectedID());
        });
    }


    private void initObserver() {
        viewModel.getAllLists().observe(this, listModels -> {
            lists = listModels;
            fetchList();
        });
    }


    public void showFragment(DialogFragment fragment) {
        if (!fragment.isAdded()) {
            fragment.show(getSupportFragmentManager(), fragment.getTag());
        }
    }


    public void fetchList() {
        adapter = new ListAdapter(lists, this);
        handleMinusButton();
        listView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void textClicked(int position) {
        CreateListFragment createListFragment = new CreateListFragment(lists.get(position));
        showFragment(createListFragment);
    }

    @Override
    public void handleMinusButton() {
        if (adapter != null && adapter.getSelectedID().size() > 0) {
            minusButton.show();
        } else {
            minusButton.hide();
        }
    }
}