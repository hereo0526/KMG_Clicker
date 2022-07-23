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
    private ImageView image_stage_clear;
    private TextView text_clear;
    private TextView text_point;
    private Button button_stage_clear;



    Animation animation_pop;
    Animation animation_critical;
    Animation animation_enemy_hit;
    Animation animation_sword_cut;
    Animation animation_enemy_down;
    Animation animation_enemy_emerge;
    Animation animation_enemy_attack;

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
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

    public void clear(){
        presenter.setScore(0);
        presenter.setInc(1);
        presenter.setIncNeed(10);
        presenter.setCritRatio(1);
        presenter.setCritRatioNeed(10);
        presenter.setMyHealth(100);
        presenter.setEnemyIndex(0);
        presenter.setEnemyDefault();
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
        setTextTimeText("0");
    }

    public void stageClear(){
        button_upgrade.setEnabled(false);
        button_attack.setEnabled(false);
        button_fight.setEnabled(false);
        button_run.setEnabled(false);

        image_stage_clear.bringToFront();
        text_clear.bringToFront();
        text_point.bringToFront();
        button_stage_clear.bringToFront();

        setTextPoint();
        image_stage_clear.setVisibility(View.VISIBLE);
        text_clear.setVisibility(View.VISIBLE);
        text_point.setVisibility(View.VISIBLE);
        button_stage_clear.setVisibility(View.VISIBLE);
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
        image_stage_clear  = findViewById(R.id.image_stage_clear);
        text_clear         = findViewById(R.id.text_clear);
        text_point         = findViewById(R.id.text_point);
        button_stage_clear = findViewById(R.id.button_stage_clear);

        image_sword_cut.setVisibility(View.INVISIBLE);
        image_click.setVisibility(View.INVISIBLE);
        critical_text.setVisibility(View.INVISIBLE);
        button_attack.setVisibility(View.INVISIBLE);
        button_upgrade.setVisibility(View.INVISIBLE);

        image_stage_clear.setVisibility(View.INVISIBLE);
        text_clear.setVisibility(View.INVISIBLE);
        text_point.setVisibility(View.INVISIBLE);
        button_stage_clear.setVisibility(View.INVISIBLE);

        String[] default_1 = {"@drawable/wolf", "@drawable/boss1_default"};
        String[] attack_1  = {"@drawable/wolf", "@drawable/boss1_attack"};
        String packName = this.getPackageName();


        ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            clear();
                            Intent intent = getIntent();
                            presenter.setIndexStage(intent.getIntExtra("stage_level", presenter.getIndexStage()));
                            presenter.setInc(intent.getIntExtra("weapon_level", presenter.getInc()));
                            presenter.setEnemyDefault();
                            setTextAll();
                        }
                    }
                }
        );
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        clear();
        setTextAll();

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        button_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        image_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getClickAllow() == 1){
                    presenter.addScore();
                    presenter.addScoreCrit();
                    if(presenter.flagCrit() == 1){
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
        ///     ///   /////////     ///////    /////////       ///      ////////    ////////
        ///     ///   ///    ///   ///         ///    ///     /////     ///   ///   ///
        ///     ///   /////////   ///    ///   ////////      //  ///    ///    ///  //////
        ///     ///   ///          ///    ///  ///   ///    /////////   ///   ///   ///
          ///////     ///           ///////    ///    ///  ///     ///  ////////    ////////
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
                                                int resId_attack = getResources().getIdentifier(attack_1[presenter.getEnemyIndex()], "drawable", packName);
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
                                                int resId_default = getResources().getIdentifier(default_1[presenter.getEnemyIndex()], "drawable", packName);
                                                image_enemy.setImageResource(resId_default);
                                                Intent intent = new Intent(FightActivity.this, UpgradeActivity.class);
                                                intent.putExtra("score", presenter.getScore());
                                                intent.putExtra("inc", presenter.getInc());
                                                intent.putExtra("inc_need", presenter.getIncNeed());
                                                intent.putExtra("crit_ratio", presenter.getCritRatio());
                                                intent.putExtra("crit_ratio_need", presenter.getCritRatioNeed());
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
                        startActivityResult.launch(intent);
                        button_fight.setVisibility(View.VISIBLE);
                    }
                    button_attack.setVisibility(View.INVISIBLE);
                }
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
            ///     /////////  /////////     ///        //////     ///   ///
           /////       ///        ///       /////     ///    ///   /// ///
          //  ///      ///        ///      //  ///    ///          /////
         /////////     ///        ///     /////////   ///    ///   /// ///
        ///     ///    ///        ///    ///     ///    //////     ///   ///
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

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
                        presenter.setEnemyIndex(presenter.getEnemyIndex()+1);
                        if(presenter.getEnemyIndex() == presenter.getMax()){
                            presenter.setDownFlag(1);
                        }
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
                                            new Timer().schedule(
                                                new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if(presenter.getDownFlag() == 1) {
                                                                    stageClear();
                                                                }
                                                                else {
                                                                    int resId_default = getResources().getIdentifier(default_1[presenter.getEnemyIndex()], "drawable", packName);
                                                                    presenter.setEnemyDefault();
                                                                    setTextEnemyHealth();
                                                                    image_enemy.setImageResource(resId_default);
                                                                    image_enemy.setVisibility(View.VISIBLE);
                                                                    animation_enemy_emerge = null;
                                                                    animation_enemy_emerge = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enemy_emerge);
                                                                    image_enemy.startAnimation(animation_enemy_emerge);
                                                                    progress_enemy.setProgress(100);
                                                                    progress_enemy.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                                                                }
                                                            }
                                                        });
                                                    }
                                                },
                                                700
                                            );
                                        }
                                    });
                                }
                            },
                            700
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

                        progress_enemy.setProgress((int)((double)presenter.getEnemyHealth()/(double)presenter.getEnemyHealthDefault()*100.0));
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
                                                int resId_attack = getResources().getIdentifier(attack_1[presenter.getEnemyIndex()], "drawable", packName);
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
                                                int resId_default = getResources().getIdentifier(default_1[presenter.getEnemyIndex()], "drawable", packName);
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
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        button_stage_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FloorActivity.class);
                intent.putExtra("point", presenter.getPoint());
                setResult(RESULT_OK, intent);
                finishActivity(1);
                finish();
            }
        });
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
    public void setTextTimeText(String s){
        time_text.setText(s);
    }
    public void setTextPoint(){
        text_point.setText("+"+presenter.getPoint());
    }
    public void setTextAll(){
        setTextScore();
        setTextMyHealth();
        setTextEnemyHealth();
    }
}