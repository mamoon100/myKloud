package com.example.mykloud.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykloud.R;
import com.example.mykloud.listners.ListListener;
import com.example.mykloud.models.ListModel;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private final List<ListModel> lists;
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
        setBorderAndCheck(holder, list.getId(), list.getPriority());


        holder.checkBox.setOnClickListener(v -> {
            if (holder.checkBox.isChecked()) {
                selectedID.add(list.getId());
            } else {
                selectedID.remove(list.getId());
            }
            setBorderAndCheck(holder, list.getId(), list.getPriority());
            listener.handleMinusButton();
        });


        holder.itemView.setOnClickListener(v -> {
            this.listener.textClicked(position);
        });

    }


    public void setBorderAndCheck(ListViewHolder holder, String id, String priority) {
        if (priority.equals("0")) {
            holder.checkBox.setButtonTintList(AppCompatResources.getColorStateList(holder.checkBox.getContext(), R.color.red));
        } else if (priority.equals("1")) {
            holder.checkBox.setButtonTintList(AppCompatResources.getColorStateList(holder.checkBox.getContext(), R.color.blue));
        } else {
            holder.checkBox.setButtonTintList(AppCompatResources.getColorStateList(holder.checkBox.getContext(), R.color.green));
        }
        holder.checkBox.setChecked(selectedID.contains(id));
        if (holder.checkBox.isChecked()) {
            holder.listText.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.text_gray));
            holder.listText.setPaintFlags(holder.listText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.listText.setPaintFlags(0);
            holder.listText.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.black));
        }
    }


    public List<String> getSelectedID() {
        return this.selectedID;
    }

    public void emptySelectedID() {
        this.selectedID.clear();
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
