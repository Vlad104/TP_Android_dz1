package com.example.tp_android_dz1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class NumberFragment extends Fragment {
    private int _number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onStart();

        Bundle bundle = getArguments();
        if (bundle != null) {
            _number = bundle.getInt(MainActivity.CURRENT_NUMBER);
        }

        TextView text = view.findViewById(R.id.number);
        text.setText(String.valueOf(_number));
        text.setTextColor(getColor(_number));


        Button button = Objects.requireNonNull(getView()).findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });
    }

    private int getColor(int number) {
        return (number % 2 == 0) ? Color.RED : Color.BLUE;
    }
}
