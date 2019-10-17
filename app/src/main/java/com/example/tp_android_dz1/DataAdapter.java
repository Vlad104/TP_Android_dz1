package com.example.tp_android_dz1;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Integer> _data;
    private IEventListener _clickListener;

    DataAdapter(int listSize, IEventListener clickListener) {
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
        holder.getTextView().setText(String.format("%s", _data.get(position)));
        holder.getTextView().setTextColor(getColor(position + 1));
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        _clickListener = null;
    }

    void addNumber() {
        _data.add(getItemCount() + 1);
        notifyItemInserted(getItemCount());
    }

    private int getColor(int number) {
        return (number % 2 == 0) ? Color.RED : Color.BLUE;
    }
}
