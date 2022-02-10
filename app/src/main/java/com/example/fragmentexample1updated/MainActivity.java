package com.example.fragmentexample1updated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mOpenButton;
    private Boolean isFragmentDisplay = false;
    private static final String FRAGMENT_STATE = "fragment_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenButton = findViewById(R.id.open_button);

        if (savedInstanceState != null){
            isFragmentDisplay = savedInstanceState.getBoolean(FRAGMENT_STATE);

            if (isFragmentDisplay){
                showFragment();
                //agar tetap display walaupun move aplikasi
            }
        }
        mOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplay){
                    showFragment();
                }
                else {
                    closeFragment();
                }
            }
        });
    }

    private void showFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();

        mOpenButton.setText(R.string.close);
        isFragmentDisplay = true;
    }

    private void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction.remove(simpleFragment).commit();

        mOpenButton.setText(R.string.open);
        isFragmentDisplay = false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(FRAGMENT_STATE, isFragmentDisplay);
        super.onSaveInstanceState(outState);
    }
}