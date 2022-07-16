package com.example.newapp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.presenter.MainPresenter;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    MainPresenter presenter = new MainPresenter();

    private Button button_back;
    private Button button_clear;
    private TextView text_point;
    private Button button_reinforce;
    private Button button_adventure;

    int clear_check = 0;
    public void clear(){
        presenter.setInc(1);
        presenter.setPoint(0);
        setTextPoint();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_back      = findViewById(R.id.button_back);
        button_clear     = findViewById(R.id.button_clear);
        text_point       = findViewById(R.id.text_point);
        button_reinforce = findViewById(R.id.button_reinforce);
        button_adventure = findViewById(R.id.button_adventure);

        clear_check = 0;
        SharedPreferences pref_get = getSharedPreferences("pref", MODE_PRIVATE);
        if( (pref_get != null) && (pref_get.contains("point")) ) {
            int saved_score = pref_get.getInt("point", 0);
            presenter.setPoint(saved_score);
            setTextPoint();
        }
        if( (pref_get != null) && (pref_get.contains("weapon_level")) ) {
            int saved_level = pref_get.getInt("weapon_level", 1);
            presenter.setInc(saved_level);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("point", presenter.getPoint());
                editor.commit();
                finish();
            }
        });
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
        button_reinforce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReinActivity.class);
                intent.putExtra("point", presenter.getPoint());
                intent.putExtra("weapon_level", presenter.getInc());
                startActivityForResult(intent, 0);
            }
        });

        button_adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FloorActivity.class);
                intent.putExtra("weapon_level", presenter.getInc());
                startActivityForResult(intent, 1);
            }
        });
    }
    public void setTextPoint(){
        text_point.setText("Point : "+Integer.toString(presenter.getPoint()));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                presenter.setPoint(intent.getIntExtra("point", presenter.getPoint()));
                presenter.setInc(intent.getIntExtra("weapon_level", presenter.getInc()));
                setTextPoint();
            }
        }
        else if(requestCode == 1){
            if(resultCode == RESULT_OK){
                presenter.setPoint(presenter.getPoint()+intent.getIntExtra("point", presenter.getPoint()));
                setTextPoint();
            }
        }
    }
}
