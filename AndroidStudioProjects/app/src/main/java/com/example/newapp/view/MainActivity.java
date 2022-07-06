package com.example.newapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity {
    MainPresenter presenter = new MainPresenter();

    private Button button_clear;
    private Button button_reinforce;
    private Button button_adventure;

    int clear_check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_clear     = findViewById(R.id.button_clear);
        button_reinforce = findViewById(R.id.button_reinforce);
        button_adventure = findViewById(R.id.button_adventure);

        clear_check = 0;

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_check = 1;
            }
        });
        button_reinforce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpgradeActivity.class);
                startActivity(intent);
            }
        });

        button_adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FightActivity.class);
                intent.putExtra("clear_check", clear_check);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });
    }
}
