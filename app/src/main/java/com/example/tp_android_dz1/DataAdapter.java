package com.example.tp_android_dz1;

import java.util.ArrayList;
import java.util.List;

import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Integer> _data;
    private IEventListener _clickListener;

    public DataAdapter(int listSize, IEventListener clickListener) {
        _clickListener = clickListener;
        _data = new ArrayList<>(listSize);

        for(Integer i = 1; i < listSize; i++) {
            _data.add(i);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);

        return new ViewHolder(view, _clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(_data.get(position).toString());
        holder.getTextView().setTextColor(getColor(position + 1));
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public void addNumber() {
        _data.add(getItemCount() + 1);
        notifyItemInserted(getItemCount());
    }

    public static int getColor(int number) {
        return (number % 2 == 0) ? Color.RED : Color.BLUE;
    }
}
