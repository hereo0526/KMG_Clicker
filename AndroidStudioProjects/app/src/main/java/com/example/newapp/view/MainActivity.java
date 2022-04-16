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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    DecimalFormat formatter = new DecimalFormat("###,###");

    private ImageView image_click;
    private TextView score_text;
    private TextView critical_text;
    private TextView double_need;
    private TextView click_add;

    MainPresenter presenter = new MainPresenter();
    Animation animation_pop;
    Animation animation_critical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_click = findViewById(R.id.image_click);
        score_text = findViewById(R.id.score_text);
        critical_text = findViewById(R.id.critical_text);
        double_need = findViewById(R.id.double_need);
        click_add = findViewById(R.id.click_add);

        animation_pop = AnimationUtils.loadAnimation(this, R.anim.pop);
        animation_critical = AnimationUtils.loadAnimation(this, R.anim.critical);

        SharedPreferences pref_get = getSharedPreferences("preferences", MODE_PRIVATE);
        if( (pref_get != null) && (pref_get.contains("score")) ) {
            int saved_score = pref_get.getInt("score", 0);
            presenter.setScore(saved_score);
            setTextScore(formatter.format(saved_score));
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
        ////////////////////////////////////////////////////////////////////////////////////////////
        image_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addScore();
                presenter.addScoreCrit();
                if(presenter.flagCrit() == 1){
                    critical_text.setVisibility(View.VISIBLE);
                    critical_text.startAnimation(animation_critical);
                    critical_text.setVisibility(View.INVISIBLE);
                }
                setTextScore(formatter.format(presenter.getScore()));
                image_click.startAnimation(animation_pop);
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
        setTextScore(formatter.format(presenter.getScore()));
        setTextIncrease("("+'+'+formatter.format(presenter.getIncrease())+')');
        setTextDoubleNeed(formatter.format(presenter.getDoubleNeed()));
    }
}