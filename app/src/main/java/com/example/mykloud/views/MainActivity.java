package com.example.mykloud.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mykloud.R;
import com.example.mykloud.listners.ListListener;
import com.example.mykloud.repo.ListRepo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListRepo listRepo = ListRepo.getInstance();
        FloatingActionButton plusButton = findViewById(R.id.plusButton);

    }

    @Override
    public void textClicked(int position) {

    }
}