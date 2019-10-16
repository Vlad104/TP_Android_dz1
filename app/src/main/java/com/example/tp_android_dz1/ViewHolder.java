package com.example.tp_android_dz1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
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
