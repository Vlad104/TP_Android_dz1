package com.example.tp_android_dz1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerFragment extends Fragment {
    private int _listSize;
    private IEventListener clickListener;

    @Override
    public void onAttach(Context context) {
        clickListener = (IEventListener) context;

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            _listSize = bundle.getInt(MainActivity.LIST_SIZE);
        }

        final DataAdapter adapter = new DataAdapter(_listSize, clickListener);

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getView().getContext(), spanCount()));
        recyclerView.setAdapter(adapter);

        Button button = getView().findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addNumber();
                clickListener.onChangeContent();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MainActivity.LIST_SIZE, _listSize);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }

    private int spanCount() {
        int orientation = getResources().getConfiguration().orientation;

        return  (orientation == Configuration.ORIENTATION_PORTRAIT) ? 3 : 4;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView _textView;
        private IEventListener _clickListener;

        public ViewHolder(@NonNull View itemView, IEventListener clickListener) {
            super(itemView);
            _clickListener = clickListener;
            _textView = itemView.findViewById(R.id.item_list);

            _textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = Integer.parseInt(_textView.getText().toString());
                    _clickListener.onNumberClick(number);
                }
            });
        }

        public TextView getTextView() {
            return _textView;
        }
    }
}
