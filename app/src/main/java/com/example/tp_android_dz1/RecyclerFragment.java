package com.example.tp_android_dz1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class RecyclerFragment extends Fragment {
    private static final String LIST_SIZE = "LIST_SIZE";
    private int _listSize = 100;
    private IEventListener clickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickListener = (IEventListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            _listSize = savedInstanceState.getInt(LIST_SIZE);
        }

        final DataAdapter adapter = new DataAdapter(_listSize, clickListener);

        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getView().getContext(), spanCount()));
        recyclerView.setAdapter(adapter);

        Button button;
        button = getView().findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addNumber();
                _listSize += 1;
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LIST_SIZE, _listSize);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }

    private int spanCount() {
        return getResources().getInteger((R.integer.columns));
    }
}
