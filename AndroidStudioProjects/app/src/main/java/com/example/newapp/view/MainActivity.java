package com.example.newapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
//import android.widget.Button;
import android.widget.Button;
//import android.widget.ImageView;
import android.widget.ImageView;
//import android.widget.TextView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity {
    private TextView score_text;
    private Button button_add;
    MainPresenter presenter = new MainPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score_text = findViewById(R.id.score_text);
        button_add = findViewById(R.id.button_add);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newString = presenter.changeText();
                showText(newString);
            }
        });
    }
    public void showText(String s){
        score_text.setText(s);
    }
}