package com.example.newapp.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.presenter.FightPresenter;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class FightActivity<clickAllow> extends AppCompatActivity {

    DecimalFormat formatter = new DecimalFormat("###,###");

    FightPresenter presenter = new FightPresenter();

    private ProgressBar progress_enemy;
    private TextView enemy_health;
    private ImageView image_enemy;
    private ImageView image_sword_cut;
    private ImageView image_click;
    private TextView time_text;
    private TextView score_text;
    private TextView critical_text;
    private ProgressBar progress_my;
    private TextView my_health;
    private Button button_fight;
    private Button button_upgrade;
    private Button button_attack;
    private Button button_run;



    Animation animation_pop;
    Animation animation_critical;
    Animation animation_enemy_hit;
    Animation animation_sword_cut;
    Animation animation_enemy_down;
    Animation animation_enemy_emerge;
    Animation animation_enemy_attack;

    int time_count = 10;
    private Timer timer;
    public void tempTask() {
        if (timer == null)
            timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTextTimeText("10");
                        if (time_count > 0)
                            time_count--;
                        setTextTimeText(Integer.toString(time_count));
                        if (time_count == 0) {
                            presenter.setClickAllow(0);
                            timer.cancel();
                            timer = null;
                            presenter.setUpFlag(1);
                            if (presenter.getClear() == 0) {
                                image_click.setVisibility(View.INVISIBLE);
                                button_attack.setVisibility(View.VISIBLE);
                                button_upgrade.setVisibility(View.VISIBLE);
                            } else
                                presenter.setClear(0);
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    public void clear(){
        presenter.setScore(0);
        presenter.setInc(1);
        presenter.setIncNeed(10);
        presenter.setCritRatio(1);
        presenter.setCritRatioNeed(10);
        presenter.setMyHealth(100);
        presenter.setEnemyIndex(0);
        presenter.setEnemyHealth(presenter.getEnemyHealthArr());
        presenter.setEnemyAttack(presenter.getEnemyAttackArr());
        time_count = 0;
        image_enemy.setVisibility(View.VISIBLE);
        image_click.setVisibility(View.INVISIBLE);
        button_fight.setVisibility(View.VISIBLE);
        button_attack.setVisibility(View.INVISIBLE);
        button_upgrade.setVisibility(View.INVISIBLE);
        progress_enemy.setProgress(100);
        progress_enemy.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.green),
                android.graphics.PorterDuff.Mode.SRC_IN);
        progress_my.setProgress(100);
        progress_my.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.green),
                android.graphics.PorterDuff.Mode.SRC_IN);
        setTextTimeText("0");
        setTextAll();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    private View decorView;
    private int uiOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        progress_enemy  = findViewById(R.id.progress_enemy);
        enemy_health    = findViewById(R.id.enemy_health);
        image_enemy     = findViewById(R.id.image_enemy);
        image_sword_cut = findViewById(R.id.image_sword_cut);
        image_click     = findViewById(R.id.image_click);
        time_text       = findViewById(R.id.time_text);
        score_text      = findViewById(R.id.score_text);
        critical_text   = findViewById(R.id.critical_text);
        progress_my     = findViewById(R.id.progress_my);
        my_health       = findViewById(R.id.my_health);
        button_fight    = findViewById(R.id.button_fight);
        button_upgrade  = findViewById(R.id.button_upgrade);
        button_attack   = findViewById(R.id.button_attack);
        button_run      = findViewById(R.id.button_run);

        image_sword_cut.setVisibility(View.INVISIBLE);
        image_click.setVisibility(View.INVISIBLE);
        critical_text.setVisibility(View.INVISIBLE);
        button_attack.setVisibility(View.INVISIBLE);
        button_upgrade.setVisibility(View.INVISIBLE);

        String[] enemy_default_id = {"@drawable/wolf", "@drawable/boss1_default"};
        String[] enemy_attack_id = {"@drawable/wolf", "@drawable/boss1_attack"};
        String packName = this.getPackageName();

        SharedPreferences pref_get = getSharedPreferences("preferences", MODE_PRIVATE);
        if( (pref_get != null) && (pref_get.contains("score")) ) {
            int saved_score = pref_get.getInt("score", 0);
            presenter.setScore(saved_score);
            setTextScore();
        }
        if( (pref_get != null) && (pref_get.contains("my_hp")) ) {
            int saved_my_hp = pref_get.getInt("my_hp", 100);
            presenter.setMyHealth(saved_my_hp);
            setTextMyHealth();
        }
        if( (pref_get != null) && (pref_get.contains("enemy_hp")) ) {
            int saved_enemy_hp = pref_get.getInt("enemy_hp", 1000);
            presenter.setEnemyHealth(saved_enemy_hp);
            setTextEnemyHealth();
        }
        if( (pref_get != null) && (pref_get.contains("inc")) ) {
            int saved_inc = pref_get.getInt("inc", 1);
            presenter.setInc(saved_inc);
        }
        if( (pref_get != null) && (pref_get.contains("inc_need")) ) {
            int saved_inc_need = pref_get.getInt("inc_need", 10);
            presenter.setIncNeed(saved_inc_need);
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
        if( (pref_get != null) && (pref_get.contains("enemy_index")) ) {
            int saved_enemy_index = pref_get.getInt("enemy_index", 1);
            presenter.setEnemyIndex(saved_enemy_index);
        }
        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent intent = result.getData();
                            presenter.setScore(intent.getIntExtra("score_up", presenter.getScore()));
                            presenter.setInc(intent.getIntExtra("inc_up", presenter.getInc()));
                            presenter.setIncNeed(intent.getIntExtra("inc_need_up", presenter.getIncNeed()));
                            presenter.setCritRatio(intent.getIntExtra("crit_ratio_up", presenter.getCritRatio()));
                            presenter.setCritRatioNeed(intent.getIntExtra("crit_ratio_need_up", presenter.getCritRatioNeed()));
                            presenter.setCritInc(intent.getIntExtra("crit_inc_up", presenter.getCritInc()));
                            if(intent.getIntExtra("clear_check", 0) == 1) {
                                clear();
                            }
                            setTextAll();
                        }
                    }
                }
        );
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
/*
        presenter.setEnemyAttack(presenter.getEnemyAttackArr());
        presenter.setEnemyHealth(presenter.getEnemyHealthArr());
        int resId_default = getResources().getIdentifier(enemy_default_id[presenter.getEnemyIndex()], "drawable", packName);
        image_enemy.setImageResource(resId_default);
        progress_enemy.setProgress((int)((double)presenter.getEnemyHealth()/(double)presenter.getEnemyHealthArr()*100.0));
        if(progress_enemy.getProgress() < 30)
            progress_enemy.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        else if(progress_enemy.getProgress() < 60)
            progress_enemy.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
        else
            progress_enemy.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

 */
        clear();
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        image_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getClickAllow() == 1){
                    presenter.addScore();
                    presenter.addScoreCrit();
                    if(presenter.flagCrit() == 1){
                        setTextCrit(formatter.format(presenter.getCritInc()));
                        critical_text.setVisibility(View.VISIBLE);
                        animation_critical = null;
                        animation_critical = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.critical);
                        critical_text.startAnimation(animation_critical);
                        critical_text.setVisibility(View.INVISIBLE);
                    }
                    setTextScore();

                    animation_pop = null;
                    animation_pop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop);
                    image_click.startAnimation(animation_pop);
                }
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        button_fight.setOnClickListener(new View.OnClickListener() {
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
                button_fight.setVisibility(View.INVISIBLE);
                button_attack.setVisibility(View.INVISIBLE);
                button_upgrade.setVisibility(View.INVISIBLE);


            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        button_upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getUpFlag() == 1) {
                    if (presenter.getUpChoose() == 1){
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable(){
                                            @Override
                                            public void run() {
                                                presenter.setMyHealth(presenter.getMyHealth() - presenter.getEnemyAttack());
                                                setTextMyHealth();
                                                int resId_attack = getResources().getIdentifier(enemy_attack_id[presenter.getEnemyIndex()], "drawable", packName);
                                                image_enemy.setImageResource(resId_attack);
                                                animation_enemy_attack = null;
                                                animation_enemy_attack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_attack);
                                                image_enemy.startAnimation(animation_enemy_attack);
                                            }
                                        });
                                    }
                                },
                                1000
                        );
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable(){
                                            @Override
                                            public void run() {
                                                int resId_default = getResources().getIdentifier(enemy_default_id[presenter.getEnemyIndex()], "drawable", packName);
                                                image_enemy.setImageResource(resId_default);
                                                Intent intent = new Intent(FightActivity.this, UpgradeActivity.class);
                                                intent.putExtra("score", presenter.getScore());
                                                intent.putExtra("inc", presenter.getInc());
                                                intent.putExtra("inc_need", presenter.getIncNeed());
                                                intent.putExtra("crit_ratio", presenter.getCritRatio());
                                                intent.putExtra("crit_ratio_need", presenter.getCritRatioNeed());
                                                intent.putExtra("crit_inc", presenter.getCritInc());
                                                startActivityResult.launch(intent);
                                                button_fight.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                },
                                1600
                        );
                        presenter.setUpChoose(0);
                    }
                    else{
                        Intent intent = new Intent(FightActivity.this, UpgradeActivity.class);
                        intent.putExtra("score", presenter.getScore());
                        intent.putExtra("inc", presenter.getInc());
                        intent.putExtra("inc_need", presenter.getIncNeed());
                        intent.putExtra("crit_ratio", presenter.getCritRatio());
                        intent.putExtra("crit_ratio_need", presenter.getCritRatioNeed());
                        intent.putExtra("crit_inc", presenter.getCritInc());
                        startActivityResult.launch(intent);
                        button_fight.setVisibility(View.VISIBLE);
                    }
                    button_attack.setVisibility(View.INVISIBLE);
                }
            }
        });
        button_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getUpFlag() == 1) {
                    presenter.setMyAttack(presenter.getScore());
                    button_fight.setVisibility(View.VISIBLE);
                    button_attack.setVisibility(View.INVISIBLE);
                    button_upgrade.setVisibility(View.INVISIBLE);
                    if(presenter.getEnemyHealth() - presenter.getMyAttack() <= 0){//적 잡음
                        presenter.setEnemyHealth(0);
                        presenter.setScore(0);
                        setTextAll();
                        progress_enemy.setProgress(0);

                        animation_enemy_hit = null;
                        animation_enemy_hit = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_hit);
                        image_enemy.startAnimation(animation_enemy_hit);
                        animation_sword_cut = null;
                        animation_sword_cut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sword_cut);
                        image_sword_cut.startAnimation(animation_sword_cut);
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable(){
                                            @Override
                                            public void run() {
                                                animation_enemy_down = null;
                                                animation_enemy_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_down);
                                                image_enemy.startAnimation(animation_enemy_down);
                                            }
                                        });
                                    }
                                },
                                700
                        );
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable(){
                                            @Override
                                            public void run() {
                                                presenter.setEnemyIndex(presenter.getEnemyIndex()+1);
                                                int resId_default = getResources().getIdentifier(enemy_default_id[presenter.getEnemyIndex()], "drawable", packName);
                                                presenter.setEnemyHealth(presenter.getEnemyHealthArr());
                                                presenter.setEnemyAttack(presenter.getEnemyAttackArr());
                                                setTextEnemyHealth();
                                                image_enemy.setImageResource(resId_default);
                                                image_enemy.setVisibility(View.VISIBLE);
                                                animation_enemy_emerge = null;
                                                animation_enemy_emerge = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_emerge);
                                                image_enemy.startAnimation(animation_enemy_emerge);
                                                progress_enemy.setProgress(100);
                                                progress_enemy.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                                                presenter.setEnemyHealth(presenter.getEnemyHealthArr());
                                                presenter.setEnemyAttack(presenter.getEnemyAttackArr());
                                                presenter.setEnemyIndex(presenter.getEnemyIndex());
                                            }
                                        });
                                    }
                                },
                                1400
                        );
                    }
                    else{
                        presenter.setEnemyHealth(presenter.getEnemyHealth() - presenter.getMyAttack());
                        setTextEnemyHealth();
                        /////////////////
                        animation_enemy_hit = null;
                        animation_enemy_hit = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_hit);
                        image_enemy.startAnimation(animation_enemy_hit);
                        animation_sword_cut = null;
                        animation_sword_cut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sword_cut);
                        image_sword_cut.startAnimation(animation_sword_cut);

                        progress_enemy.setProgress((int)((double)presenter.getEnemyHealth()/(double)presenter.getEnemyHealthArr()*100.0));
                        if(progress_enemy.getProgress() < 30)
                            progress_enemy.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        else if(progress_enemy.getProgress() < 60)
                            progress_enemy.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
                        /////////////////적이 피해를 입는 모션

                        /////////////////
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        button_attack.setVisibility(View.INVISIBLE);
                                        button_upgrade.setVisibility(View.INVISIBLE);
                                        presenter.setScore(0);
                                        runOnUiThread(new Runnable(){
                                            @Override
                                            public void run() {
                                                presenter.setMyHealth(presenter.getMyHealth() - presenter.getEnemyAttack());
                                                setTextMyHealth();
                                                int resId_attack = getResources().getIdentifier(enemy_attack_id[presenter.getEnemyIndex()], "drawable", packName);
                                                image_enemy.setImageResource(resId_attack);
                                                animation_enemy_attack = null;
                                                animation_enemy_attack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_attack);
                                                image_enemy.startAnimation(animation_enemy_attack);
                                                setTextScore();
                                                progress_my.setProgress(presenter.getMyHealth());
                                                if(progress_my.getProgress() < 30)
                                                    progress_my.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                                                else if(progress_my.getProgress() < 60)
                                                    progress_my.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
                                            }
                                        });
                                    }
                                },
                                1000
                        );
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        runOnUiThread(new Runnable(){
                                            @Override
                                            public void run() {
                                                int resId_default = getResources().getIdentifier(enemy_default_id[presenter.getEnemyIndex()], "drawable", packName);
                                                image_enemy.setImageResource(resId_default);
                                            }
                                        });
                                    }
                                },
                                1600
                        );
                        /////////////////적이 공격하는 모션
                    }
                }
            }
        });

        button_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
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
        editor.putInt("enemy_index", presenter.getEnemyIndex());

        editor.apply();
        editor.commit();
    }

    public void setTextScore(){
        score_text.setText(Integer.toString(presenter.getScore()));
    }
    public void setTextMyHealth(){
        String myhealth = Integer.toString(presenter.getMyHealth());
        my_health.setText("HP : "+myhealth);
    }
    public void setTextEnemyHealth(){
        String enemyHealth = Integer.toString(presenter.getEnemyHealth());
        enemy_health.setText(enemyHealth);
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