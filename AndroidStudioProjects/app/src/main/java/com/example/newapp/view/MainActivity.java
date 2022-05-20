package com.example.newapp.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.model.MainModel;
import com.example.newapp.presenter.MainPresenter;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    DecimalFormat formatter = new DecimalFormat("###,###");

    private int anim_check = 0;

    private ImageView image_click;
    private TextView score_text;
    private TextView critical_text;
    private TextView double_need;
    private TextView click_add;
    private Button button_upgrade;

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
        button_upgrade = findViewById(R.id.button_upgrade);

        animation_pop = AnimationUtils.loadAnimation(this, R.anim.pop);
        animation_critical = AnimationUtils.loadAnimation(this, R.anim.critical);

        critical_text.setVisibility(View.INVISIBLE);

        SharedPreferences pref_get = getSharedPreferences("preferences", MODE_PRIVATE);
        if( (pref_get != null) && (pref_get.contains("score")) ) {
            int saved_score = pref_get.getInt("score", 0);
            presenter.setScore(saved_score);
            setTextScore(formatter.format(saved_score));
        }
        if( (pref_get != null) && (pref_get.contains("inc")) ) {
            int saved_inc = pref_get.getInt("inc", 1);
            presenter.setInc(saved_inc);
            String str_saved_inc = Integer.toString(saved_inc);
            setTextinc("("+'+'+str_saved_inc+')');
        }
        if( (pref_get != null) && (pref_get.contains("inc_need")) ) {
            int saved_double_need = pref_get.getInt("inc_need", 10);
            presenter.setDoubleNeed(saved_double_need);
            setTextDoubleNeed(Integer.toString(saved_double_need));
        }
        if( (pref_get != null) && (pref_get.contains("crit_ratio")) ) {
            int saved_crit_ratio = pref_get.getInt("crit_ratio", 1);
            presenter.setCritRatio(saved_crit_ratio);
        }
        if( (pref_get != null) && (pref_get.contains("crit_inc")) ) {
            int saved_crit_inc = pref_get.getInt("crit_inc", 1);
            presenter.setCritRatio(saved_crit_inc);
        }
        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent intent_up = result.getData();
                            presenter.setScore(intent_up.getIntExtra("score_up", presenter.getScore()));
                            presenter.setInc(intent_up.getIntExtra("inc_up", presenter.getInc()));
                            presenter.setDoubleNeed(intent_up.getIntExtra("inc_need_up", presenter.getDoubleNeed()));
                            presenter.setCritRatio(intent_up.getIntExtra("crit_ratio_up", presenter.getCritRatio()));
                            presenter.setCritInc(intent_up.getIntExtra("crit_inc_up", presenter.getCritInc()));
                            setTextAll();
                        }
                    }
                }
        );
        ////////////////////////////////////////////////////////////////////////////////////////////
        image_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addScore();
                presenter.addScoreCrit();
                if(presenter.flagCrit() == 1){
                    critical_text.clearAnimation();
                    setCritText(formatter.format(presenter.getCritInc()));
                    critical_text.setVisibility(View.VISIBLE);
                    critical_text.startAnimation(animation_critical);
                    critical_text.setVisibility(View.INVISIBLE);
                }
                setTextScore(formatter.format(presenter.getScore()));
                image_click.clearAnimation();
                image_click.startAnimation(animation_pop);
            }
        });
        button_upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UpgradeActivity.class);
                intent.putExtra("score", presenter.getScore());
                intent.putExtra("inc", presenter.getInc());
                intent.putExtra("inc_need", presenter.getDoubleNeed());
                intent.putExtra("crit_ratio", presenter.getCritRatio());
                intent.putExtra("crit_inc", presenter.getCritInc());
                startActivityResult.launch(intent);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences pref = getSharedPreferences("preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("score", presenter.getScore() );
        editor.putInt("inc_need", presenter.getDoubleNeed() );
        editor.putInt("inc", presenter.getInc() );
        editor.putInt("crit_ratio", presenter.getCritRatio());
        editor.putInt("crit_inc", presenter.getCritInc());
        editor.apply();
        editor.commit();
    }

    public void setTextScore(String s){
        score_text.setText(s);
    }
    public void setTextinc(String s){
        click_add.setText(s);
    }
    public void setTextDoubleNeed(String s){
        double_need.setText(s);
    }
    public void setTextAll(){
        setTextScore(formatter.format(presenter.getScore()));
        setTextinc("("+'+'+formatter.format(presenter.getInc())+')');
        setTextDoubleNeed(formatter.format(presenter.getDoubleNeed()));
    }
    public void setCritText(String s){
        critical_text.setText("x2");
    }
}