package com.example.newapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;

public class MainActivity extends AppCompatActivity {

    private Button button_reinforce;
    private Button button_adventure;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_reinforce = findViewById(R.id.button_reinforce);
        button_adventure = findViewById(R.id.button_adventure);

        button_reinforce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), UpgradeActivity.class);
                //startActivity(intent);
            }
        });

        button_adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FightActivity.class);
                startActivity(intent);
            }
        });
    }
}
