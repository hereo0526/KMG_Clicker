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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class MainActivity<clickAllow> extends AppCompatActivity {

    DecimalFormat formatter = new DecimalFormat("###,###");

    private int anim_check = 0;

    private Button button_clear;
    private ImageView image_enemy;
    private ImageView image_sword_cut;
    private ImageView image_click;
    private TextView time_text;
    private TextView score_text;
    private TextView critical_text;
    private Button button_start;
    private Button button_attack;
    private Button button_upgrade;
    private TextView my_health;
    private TextView enemy_health;

    MainPresenter presenter = new MainPresenter();
    Animation animation_pop;
    Animation animation_critical;
    Animation animation_enemy_hit;
    Animation animation_sword_cut;
    Animation animation_enemy_down;
    Animation animation_enemy_emerge;

    int time_count = 10;
    private Timer timer;
    public void tempTask(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        setTextTimeText("10");
                        time_count--;
                        setTextTimeText(Integer.toString(time_count));
                        if(time_count == 0) {
                            presenter.setClickAllow(0);
                            timer.cancel();
                            timer = null;
                            presenter.setUpFlag(1);
                            image_click.setVisibility(View.INVISIBLE);
                            button_attack.setVisibility(View.VISIBLE);
                            button_upgrade.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_clear = findViewById(R.id.button_clear);
        image_enemy = findViewById(R.id.image_enemy);
        image_sword_cut = findViewById(R.id.image_sword_cut);
        image_click = findViewById(R.id.image_click);
        time_text = findViewById(R.id.time_text);
        score_text = findViewById(R.id.score_text);
        critical_text = findViewById(R.id.critical_text);
        button_start = findViewById(R.id.button_start);
        button_attack = findViewById(R.id.button_attack);
        button_upgrade = findViewById(R.id.button_upgrade);
        my_health = findViewById(R.id.my_health);
        enemy_health = findViewById(R.id.enemy_health);

        image_sword_cut.setVisibility(View.INVISIBLE);
        image_click.setVisibility(View.INVISIBLE);
        critical_text.setVisibility(View.INVISIBLE);
        button_attack.setVisibility(View.INVISIBLE);
        button_upgrade.setVisibility(View.INVISIBLE);

        SharedPreferences pref_get = getSharedPreferences("preferences", MODE_PRIVATE);
        if( (pref_get != null) && (pref_get.contains("score")) ) {
            int saved_score = pref_get.getInt("score", 0);
            presenter.setScore(saved_score);
            setTextScore();
        }
        if( (pref_get != null) && (pref_get.contains("my_hp")) ) {
            int saved_my_hp = pref_get.getInt("my_hp", 100);
            presenter.setScore(saved_my_hp);
            setTextMyHealth();
        }
        if( (pref_get != null) && (pref_get.contains("enemy_hp")) ) {
            int saved_enemy_hp = pref_get.getInt("enemy_hp", 1000);
            presenter.setScore(saved_enemy_hp);
            setTextEnemyHealth();
        }
        if( (pref_get != null) && (pref_get.contains("inc")) ) {
            int saved_inc = pref_get.getInt("inc", 1);
            presenter.setInc(saved_inc);
        }
        if( (pref_get != null) && (pref_get.contains("crit_ratio")) ) {
            int saved_crit_ratio = pref_get.getInt("crit_ratio", 1);
            presenter.setCritRatio(saved_crit_ratio);
        }
        if( (pref_get != null) && (pref_get.contains("crit_ratio_need")) ) {
            int saved_crit_ratio_need = pref_get.getInt("crit_ratio_need", 10);
            presenter.setCritRatioNeed(saved_crit_ratio_need);
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
                            presenter.setIncNeed(intent_up.getIntExtra("inc_need_up", presenter.getIncNeed()));
                            presenter.setCritRatio(intent_up.getIntExtra("crit_ratio_up", presenter.getCritRatio()));
                            presenter.setCritRatioNeed(intent_up.getIntExtra("crit_ratio_need_up", presenter.getCritRatioNeed()));
                            presenter.setCritInc(intent_up.getIntExtra("crit_inc_up", presenter.getCritInc()));

                            setTextAll();
                        }
                    }
                }
        );
        ////////////////////////////////////////////////////////////////////////////////////////////
        animation_critical = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.critical);
        animation_pop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop);

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_enemy.setVisibility(View.VISIBLE);
                button_start.setVisibility(View.VISIBLE);
                button_attack.setVisibility(View.INVISIBLE);
                button_upgrade.setVisibility(View.INVISIBLE);
                presenter.setScore(0);
                presenter.setInc(1);
                presenter.setIncNeed(10);
                presenter.setCritRatio(1);
                presenter.setCritRatioNeed(10);
                presenter.setMyHealth(100);
                presenter.setEnemyHealth(1000);
                setTextAll();
            }
        });
        image_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getClickAllow() == 1){
                    presenter.addScore();
                    presenter.addScoreCrit();
                    if(presenter.flagCrit() == 1){
                        critical_text.clearAnimation();
                        setTextCrit(formatter.format(presenter.getCritInc()));
                        critical_text.setVisibility(View.VISIBLE);
                        critical_text.startAnimation(animation_critical);
                        critical_text.setVisibility(View.INVISIBLE);
                    }
                    setTextScore();

                    image_click.startAnimation(animation_pop);
                }
            }
        });
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setScore(0);
                setTextScore();
                presenter.setUpFlag(0);
                presenter.setClickAllow(1);
                time_count = 10;
                presenter.setUpChoose(1);
                tempTask();
                presenter.setUpFlag(1);
                image_click.setVisibility(View.VISIBLE);
                button_start.setVisibility(View.INVISIBLE);
                button_attack.setVisibility(View.INVISIBLE);
                button_upgrade.setVisibility(View.INVISIBLE);

            }
        });

        animation_enemy_hit = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_hit);
        animation_sword_cut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sword_cut);
        animation_enemy_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_down);
        animation_enemy_emerge = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_emerge);
        button_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getUpFlag() == 1) {
                    presenter.setMyAttack(presenter.getScore());
                    button_start.setVisibility(View.VISIBLE);
                    if(presenter.getEnemyHealth() - presenter.getMyAttack() <= 0){//적 잡음
                        presenter.setEnemyHealth(0);
                        setTextEnemyHealth();
                        image_sword_cut.startAnimation(animation_sword_cut);
                        image_enemy.startAnimation(animation_enemy_down);
                        image_enemy.setVisibility(View.INVISIBLE);

                        presenter.setEnemyIndex(presenter.getEnemyIndex()+1);
                        presenter.setEnemyHealth(presenter.getEnemyHealthArr());
                        presenter.setEnemyAttack(presenter.getEnemyAttackArr());
                        image_enemy.setImageResource(R.drawable.dratemp);
                        image_enemy.setVisibility(View.VISIBLE);
                        image_enemy.startAnimation(animation_enemy_emerge);
                        presenter.setEnemyHealth(presenter.getEnemyHealthArr());
                        presenter.setEnemyAttack(presenter.getEnemyAttackArr());
                        presenter.setEnemyIndex(presenter.getEnemyIndex());
                    }
                    else{
                        presenter.setEnemyHealth(presenter.getEnemyHealth() - presenter.getMyAttack());
                        setTextEnemyHealth();
                        /////////////////
                        image_enemy.startAnimation(animation_enemy_hit);
                        image_sword_cut.startAnimation(animation_sword_cut);
                        /////////////////적이 피해를 입는 모션
                        presenter.setMyHealth(presenter.getMyHealth() - presenter.getEnemyAttack());

                        /////////////////setTextMyHealth();
                        /////////////////적이 날 공격하는 모션
                        button_attack.setVisibility(View.INVISIBLE);
                        button_upgrade.setVisibility(View.INVISIBLE);
                        presenter.setScore(0);
                        setTextScore();
                    }
                }
            }
        });
        button_upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getUpFlag() == 1) {
                    if (presenter.getUpChoose() == 1){
                        presenter.setMyHealth(presenter.getMyHealth() - presenter.getEnemyAttack());
                        setTextMyHealth();
                        button_attack.setVisibility(View.INVISIBLE);
                        presenter.setUpChoose(0);
                        button_start.setVisibility(View.VISIBLE);
                    }
                    Intent intent = new Intent(MainActivity.this, UpgradeActivity.class);
                    intent.putExtra("score", presenter.getScore());
                    intent.putExtra("inc", presenter.getInc());
                    intent.putExtra("inc_need", presenter.getIncNeed());
                    intent.putExtra("crit_ratio", presenter.getCritRatio());
                    intent.putExtra("crit_ratio_need", presenter.getCritRatioNeed());
                    intent.putExtra("crit_inc", presenter.getCritInc());
                    startActivityResult.launch(intent);
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences pref = getSharedPreferences("preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        presenter.setScore(0);
        editor.putInt("score", presenter.getScore() );
        editor.putInt("inc_need", presenter.getIncNeed() );
        editor.putInt("inc", presenter.getInc() );
        editor.putInt("crit_ratio", presenter.getCritRatio());
        editor.putInt("crit_ratio_need", presenter.getCritRatioNeed());
        editor.putInt("crit_inc", presenter.getCritInc());
        editor.putInt("my_hp", presenter.getMyHealth());
        editor.putInt("enemy_hp", presenter.getEnemyHealth());

        editor.apply();
        editor.commit();
    }

    public void setTextScore(){
        score_text.setText(Integer.toString(presenter.getScore()));
    }
    public void setTextMyHealth(){
        my_health.setText("HP : "+Integer.toString(presenter.getMyHealth()));
    }
    public void setTextEnemyHealth(){
        enemy_health.setText("Enemy HP : "+Integer.toString(presenter.getEnemyHealth()));
    }
    public void setTextCrit(String s){
        critical_text.setText("x2");
    }
    public void setTextTimeText(String s){
        time_text.setText(s);
    }
    public void setTextAll(){
        setTextScore();
        setTextMyHealth();
        setTextEnemyHealth();
    }
}