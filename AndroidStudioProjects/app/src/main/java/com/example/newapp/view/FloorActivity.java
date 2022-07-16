package com.example.newapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.presenter.FloorPresenter;

public class FloorActivity extends AppCompatActivity {
    FloorPresenter presenter = new FloorPresenter();

    private Button button_back;
    private Button button_stage1;
    private Button button_stage2;
    private Button button_stage3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);
        
        button_back = findViewById(R.id.button_back);
        button_stage1 = findViewById(R.id.button_stage1);
        button_stage2 = findViewById(R.id.button_stage2);
        button_stage3 = findViewById(R.id.button_stage3);

        Intent intent = getIntent();
        presenter.setInc(intent.getIntExtra("weapon_level", presenter.getInc()));

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
