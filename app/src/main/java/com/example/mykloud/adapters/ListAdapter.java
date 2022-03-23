package com.example.mykloud.adapters;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykloud.R;
import com.example.mykloud.listners.ListListener;
import com.example.mykloud.models.ListModel;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<ListModel> lists;
    private final List<String> selectedID = new ArrayList<>();
    private final ListListener listener;


    public ListAdapter(List<ListModel> lists, ListListener listener) {
        this.lists = lists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fragment, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListModel list = lists.get(position);
        holder.listText.setText(holder.itemView.getContext().getResources().getString(R.string.list_name, list.getName()));
        setBorderAndCheck(holder, list.getId());


        holder.checkBox.setOnClickListener(v -> {
            if (holder.checkBox.isChecked()) {
                selectedID.add(list.getId());
            } else {
                selectedID.remove(list.getId());
            }
            setBorderAndCheck(holder, list.getId());
        });


        holder.listText.setOnClickListener(v -> {
            this.listener.textClicked(position);
        });

    }


    public void setBorderAndCheck(ListViewHolder holder, String id) {
        if (id.equals("0")) {
            holder.checkBox.setButtonTintList(AppCompatResources.getColorStateList(holder.checkBox.getContext(), R.color.red));
        } else if (id.equals("1")) {
            holder.checkBox.setButtonTintList(AppCompatResources.getColorStateList(holder.checkBox.getContext(), R.color.blue));
        } else {
            holder.checkBox.setButtonTintList(AppCompatResources.getColorStateList(holder.checkBox.getContext(), R.color.green));
        }
        holder.checkBox.setChecked(selectedID.contains(id));
        if (holder.checkBox.isChecked()) {
            holder.listText.setPaintFlags(holder.listText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.listText.setPaintFlags(0);
        }
    }


    @Override
    public int getItemCount() {
        return this.lists.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        MaterialCheckBox checkBox;
        TextView listText;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            listText = itemView.findViewById(R.id.listText);
        }
    }
}
