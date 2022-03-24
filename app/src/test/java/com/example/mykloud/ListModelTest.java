package com.example.mykloud;

import static org.junit.Assert.*;


import com.example.mykloud.models.ListModel;

import org.junit.Test;

public class ListModelTest {

    ListModel listModel = new ListModel();


    @Test
    public void listModel() {
        assertNotNull(listModel);
    }

    @Test
    public void getCreatedAt() {
        assertNotNull(listModel.getCreatedAt());
    }

    @Test
    public void getName() {
        assertNotNull(listModel.getName());

    }

    @Test
    public void getPriority() {
        assertNotNull(listModel.getPriority());

    }

    @Test
    public void getId() {
        assertNotNull(listModel.getId());

    }
}