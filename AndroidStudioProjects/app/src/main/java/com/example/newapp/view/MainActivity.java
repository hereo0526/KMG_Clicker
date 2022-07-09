package com.example.newapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity {
    MainPresenter presenter = new MainPresenter();

    private Button button_clear;
    private TextView text_point;
    private Button button_reinforce;
    private Button button_adventure;

    int clear_check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_clear     = findViewById(R.id.button_clear);
        text_point       = findViewById(R.id.text_point);
        button_reinforce = findViewById(R.id.button_reinforce);
        button_adventure = findViewById(R.id.button_adventure);

        clear_check = 0;

        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent intent = result.getData();
                            presenter.setPoint(presenter.getPoint()+intent.getIntExtra("point", presenter.getPoint()));
                            setTextPoint();
                        }
                    }
                }
        );
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_check = 1;
            }
        });
        button_reinforce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReinActivity.class);
                intent.putExtra("point", presenter.getPoint());
                setResult(RESULT_OK);
                startActivity(intent);
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
    public void setTextPoint(){
        text_point.setText(Integer.toString(presenter.getPoint()));
    }
}
