package com.example.tp_android_dz1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

class ViewHolder extends RecyclerView.ViewHolder {
    private TextView _textView;
    private IEventListener _clickListener;

    ViewHolder(@NonNull View itemView, IEventListener clickListener) {
        super(itemView);
        _clickListener = clickListener;
        _textView = itemView.findViewById(R.id.item_list);

        _textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = getCurrentNumber(_textView);
                _clickListener.onNumberClick(number);
            }
        });
    }

    TextView getTextView() {
        return _textView;
    }

    private int getCurrentNumber(TextView textView) {
        try {
            return Integer.parseInt(textView.getText().toString());
        } catch (Exception e){
            return 0;
        }
    }
}
