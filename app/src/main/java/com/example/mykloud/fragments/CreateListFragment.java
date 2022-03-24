package com.example.mykloud.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mykloud.R;
import com.example.mykloud.models.ListModel;
import com.example.mykloud.viewModels.CreateListFragmentDialogViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;


public class CreateListFragment extends DialogFragment {
    CreateListFragmentDialogViewModel viewModel;

    TextView header;
    RadioGroup radioGroup;
    TextInputEditText titleEditText;
    MaterialButton addButton;
    MaterialButton cancelButton;
    int selectedPriority = 2;
    ListModel list = null;


    public CreateListFragment() {
    }

    public CreateListFragment(ListModel list) {
        this.list = list;
    }


    public static CreateListFragment newInstance() {
        return new CreateListFragment();
    }


    public static CreateListFragment newInstance(ListModel list) {
        return new CreateListFragment(list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CreateListFragmentDialogViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_list, container, false);
        radioGroup = view.findViewById(R.id.radioGroup);
        titleEditText = view.findViewById(R.id.titleEditText);
        addButton = view.findViewById(R.id.addButton);
        cancelButton = view.findViewById(R.id.cancelButton);
        header = view.findViewById(R.id.header);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    MaterialRadioButton radioButton = (MaterialRadioButton) radioGroup.getChildAt(j);
                    if (radioButton.isChecked()) {
                        selectedPriority = j;
                    }
                }
            }
        });
        if (list != null) {
            addButton.setText(getResources().getString(R.string.update));
            header.setText(getResources().getString(R.string.update_task));
            titleEditText.setText(list.getName());
            MaterialRadioButton radioButton;
            switch (list.getPriority()) {
                case "0":
                    radioButton = (MaterialRadioButton) radioGroup.getChildAt(0);
                    radioButton.setChecked(true);
                    break;
                case "1":
                    radioButton = (MaterialRadioButton) radioGroup.getChildAt(1);
                    radioButton.setChecked(true);
                    break;
                default:
                    radioButton = (MaterialRadioButton) radioGroup.getChildAt(2);
                    radioButton.setChecked(true);

            }
            addButton.setOnClickListener(v -> {
                createAndUpdate();
            });
        } else {
            addButton.setOnClickListener(v -> {
                createAndUpdate();
            });
        }

        cancelButton.setOnClickListener(v -> dismiss());
    }

    public String getTitleText() {
        String title = "";
        if (titleEditText != null && titleEditText.getText() != null) {
            title = titleEditText.getText().toString();
        }
        return title;
    }


    public void resetParam() {
        titleEditText.setText("");
        list = null;
    }

    public void createAndUpdate() {
        if (list != null) {
            viewModel.updateList(list.getId(), getTitleText(), String.valueOf(selectedPriority));
        } else {
            viewModel.createList(getTitleText(), String.valueOf(selectedPriority));
        }
        dismiss();
        resetParam();
    }
}