package com.example.tp_android_dz1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements IEventListener {
    public static final String LIST_SIZE = "LIST_SIZE";
    public static final String CURRENT_NUMBER = "CURRENT_NUMBER";
    private static final String RecyclerFragmentTag = "RecyclerFragment";
    private static final String NumberFragmentTag = "NumberFragment";

    private int _listSize = 100;
    private RecyclerFragment _recyclerFragment;
    private NumberFragment _numberFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreState(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LIST_SIZE, _listSize);
    }

    @Override
    public void onNumberClick(int number) {
        renderNumberFragment(number);
    }

    public void renderRecyclerFragment() {
        setRecyclerFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, _recyclerFragment, RecyclerFragmentTag)
                .commit();
    }

    public void renderNumberFragment(int number) {
        setNumberFragment(number);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, _numberFragment, NumberFragmentTag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onChangeContent() {
        _listSize += 1;
        setRecyclerFragment();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            _listSize = savedInstanceState.getInt(LIST_SIZE);
        }

        _recyclerFragment = (RecyclerFragment) getSupportFragmentManager().findFragmentByTag(RecyclerFragmentTag);
        if (_recyclerFragment == null) {
            _recyclerFragment = new RecyclerFragment();
        }

        _numberFragment = (NumberFragment) getSupportFragmentManager().findFragmentByTag(NumberFragmentTag);
        if (_numberFragment == null) {
            _numberFragment = new NumberFragment();

            renderRecyclerFragment();
        }
    }

    private void setRecyclerFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(LIST_SIZE, _listSize);
        _recyclerFragment.setArguments(bundle);
    }

    private void setNumberFragment(int number) {
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_NUMBER, number);
        _numberFragment.setArguments(bundle);
    }
}

