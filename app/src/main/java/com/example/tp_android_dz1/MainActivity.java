package com.example.tp_android_dz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements IEventListener {
    public static final String CURRENT_NUMBER = "CURRENT_NUMBER";
    private static final String RecyclerFragmentTag = "RecyclerFragment";
    private static final String NumberFragmentTag = "NumberFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().findFragmentByTag(NumberFragmentTag) == null) {
            renderRecyclerFragment();
        }
    }

    @Override
    public void onNumberClick(int number) {
        renderNumberFragment(number);
    }

    public void renderRecyclerFragment() {
        RecyclerFragment recyclerFragment = (RecyclerFragment) getSupportFragmentManager().findFragmentByTag(RecyclerFragmentTag);
        if (recyclerFragment == null) {
            recyclerFragment = new RecyclerFragment();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, recyclerFragment, RecyclerFragmentTag)
                .commit();
    }

    public void renderNumberFragment(int number) {
        NumberFragment numberFragment = new NumberFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_NUMBER, number);
        numberFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, numberFragment, NumberFragmentTag)
                .addToBackStack(null)
                .commit();
    }
}

