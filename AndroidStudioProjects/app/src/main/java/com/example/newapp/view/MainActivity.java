package com.example.newapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity {

    private ImageView image_click;
    private TextView score_text;
    private TextView double_need;
    private TextView click_add;
    private Button button_add;
    private Button button_clear;

    MainPresenter presenter = new MainPresenter();
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_click = findViewById(R.id.image_click);
        score_text = findViewById(R.id.score_text);
        double_need = findViewById(R.id.double_need);
        click_add = findViewById(R.id.click_add);
        button_add = findViewById(R.id.button_add);
        button_clear = findViewById(R.id.button_clear);

        animation = AnimationUtils.loadAnimation(this, R.anim.pop);

        SharedPreferences pref_get = getSharedPreferences("preferences", MODE_PRIVATE);
        if( (pref_get != null) && (pref_get.contains("score")) ) {
            int saved_score = pref_get.getInt("score", 0);
            presenter.setScore(saved_score);
            setTextScore(Integer.toString(saved_score));
        }
        if( (pref_get != null) && (pref_get.contains("increase")) ) {
            int saved_increase = pref_get.getInt("increase", 1);
            presenter.setIncrease(saved_increase);
            String str_saved_increase = Integer.toString(saved_increase);
            setTextIncrease("("+'+'+str_saved_increase+')');
        }
        if( (pref_get != null) && (pref_get.contains("double_need")) ) {
            int saved_double_need = pref_get.getInt("double_need", 10);
            presenter.setDoubleNeed(saved_double_need);
            setTextDoubleNeed(Integer.toString(saved_double_need));
        }

        image_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addScore();
                String str_change = Integer.toString(presenter.getScore());
                setTextScore(str_change);
                image_click.startAnimation(animation);
            }
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getScore() >= presenter.getDoubleNeed()) {
                    presenter.setScore(presenter.getScore() - presenter.getDoubleNeed());
                    presenter.addIncrease();
                    presenter.addDoubleNeed();
                    setTextAll();
                    image_click.getLayoutParams().width += 20;
                    if(image_click.getLayoutParams().width >= 400)
                    {
                        image_click.setImageResource(R.drawable.buhwa);
                        image_click.getLayoutParams().width += 100;
                    }
                }
            }
        });
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setScore(0);
                presenter.setIncrease(1);
                presenter.setDoubleNeed(10);

                image_click.getLayoutParams().width = 264;
                image_click.setImageResource(R.drawable.egg);

                setTextAll();
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences pref = getSharedPreferences("preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("score", presenter.getScore() );
        editor.putInt("double_need", presenter.getDoubleNeed() );
        editor.putInt("increase", presenter.getIncrease() );
        editor.apply();
        editor.commit();
    }

    public void setTextScore(String s){
        score_text.setText(s);
    }
    public void setTextIncrease(String s){
        click_add.setText(s);
    }
    public void setTextDoubleNeed(String s){
        double_need.setText(s);
    }
    public void setTextAll(){
        String str_score = Integer.toString(presenter.getScore());
        setTextScore(str_score);
        String str_increase = Integer.toString(presenter.getIncrease());
        setTextIncrease("("+'+'+str_increase+')');
        String str_doubleNeed = Integer.toString(presenter.getDoubleNeed());
        setTextDoubleNeed(str_doubleNeed);
    }
}