package com.example.newapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.presenter.FloorPresenter;

import android.os.Handler;

public class FloorActivity extends AppCompatActivity {
    FloorPresenter presenter = new FloorPresenter();

    private ScrollView scroll_view;
    private Button button_back;
    private TextView text_point;
    private Button button_stage1;
    private Button button_stage2;
    private Button button_stage3;
    private Button button_stage4;
    private Button button_stage5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);

        scroll_view = findViewById(R.id.scroll_view);
        button_back = findViewById(R.id.button_back);
        text_point  = findViewById(R.id.text_point);
        button_stage1 = findViewById(R.id.button_stage1);
        button_stage2 = findViewById(R.id.button_stage2);
        button_stage3 = findViewById(R.id.button_stage3);
        button_stage4 = findViewById(R.id.button_stage4);
        button_stage5 = findViewById(R.id.button_stage5);

        Intent intent = getIntent();
        presenter.setPoint(intent.getIntExtra("point", presenter.getPoint()));
        presenter.setInc(intent.getIntExtra("weapon_level", presenter.getInc()));
        presenter.setMyHealth(intent.getIntExtra("my_health", presenter.getMyHealth()));
        setTextPoint();

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("point", presenter.getPoint());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        button_stage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FightActivity.class);
                intent.putExtra("stage_level", 1);
                intent.putExtra("weapon_level", presenter.getInc());
                intent.putExtra("my_health", presenter.getMyHealth());
                startActivityForResult(intent, 2);
            }
        });
        button_stage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FightActivity.class);
                intent.putExtra("stage_level", 2);
                intent.putExtra("weapon_level", presenter.getInc());
                intent.putExtra("my_health", presenter.getMyHealth());
                startActivityForResult(intent, 2);
            }
        });
        button_stage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FightActivity.class);
                intent.putExtra("stage_level", 3);
                intent.putExtra("weapon_level", presenter.getInc());
                intent.putExtra("my_health", presenter.getMyHealth());
                startActivityForResult(intent, 2);
            }
        });
        button_stage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FightActivity.class);
                intent.putExtra("stage_level", 4);
                intent.putExtra("weapon_level", presenter.getInc());
                intent.putExtra("my_health", presenter.getMyHealth());
                startActivityForResult(intent, 2);
            }
        });
        button_stage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FightActivity.class);
                intent.putExtra("stage_level", 5);
                intent.putExtra("weapon_level", presenter.getInc());
                intent.putExtra("my_health", presenter.getMyHealth());
                startActivityForResult(intent, 2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                presenter.setPoint(presenter.getPoint()+intent.getIntExtra("point", 0));
                presenter.setMyHealth(intent.getIntExtra("my_health", 100));
                setTextPoint();
            }
        }
    }
    public void setTextPoint(){
        text_point.setText("Point : "+Integer.toString(presenter.getPoint()));
    }
}