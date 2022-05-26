package com.example.newapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.presenter.UpgradePresenter;

public class UpgradeActivity extends AppCompatActivity {

    private TextView score_text_upgrade;

    private Button button_add_inc;
    private TextView inc_need_text;

    private Button button_crit_ratio;
    private TextView crit_ratio_text;

    //private Button button_crit_inc;
    //private TextView crit_inc_text;

    private Button button_clear;
    private ImageView image_back;

    UpgradePresenter presenter = new UpgradePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        score_text_upgrade = findViewById(R.id.score_text_upgrade);
        button_add_inc = findViewById(R.id.button_add_inc);
        inc_need_text = findViewById(R.id.inc_need_text);
        button_crit_ratio = findViewById(R.id.button_crit_ratio);
        crit_ratio_text = findViewById(R.id.crit_ratio_text);
        //button_crit_inc = findViewById(R.id.button_crit_inc);
        //crit_inc_text = findViewById(R.id.crit_inc_text);
        button_clear = findViewById(R.id.button_clear);
        image_back = findViewById(R.id.image_back);

        Intent intent = getIntent();

        presenter.setScore(intent.getExtras().getInt("score"));
        presenter.setInc(intent.getExtras().getInt("inc"));
        presenter.setIncNeed(intent.getExtras().getInt("inc_need"));
        presenter.setCritRatio(intent.getExtras().getInt("crit_ratio"));
        presenter.setCritRatioNeed(intent.getExtras().getInt("crit_ratio_need"));
        presenter.setCritInc(intent.getExtras().getInt("crit_inc"));

        setTextAll();

        button_add_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getScore() >= presenter.getIncNeed()) {
                    presenter.setScore(presenter.getScore() - presenter.getIncNeed());
                    presenter.addInc();
                    presenter.addIncNeed();
                    setTextScoreUpgrade();
                    setTextInc();
                    setTextIncNeed();
                }
            }
        });
        button_crit_ratio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getScore() >= presenter.getCritRatioNeed()){
                    presenter.setScore(presenter.getScore() - presenter.getCritRatioNeed());
                    presenter.addCritRatio();
                    presenter.addCritRatioNeed();
                    setTextScoreUpgrade();
                    setTextCritRatio();
                    setTextCritRatioNeed();
                }
            }
        });
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setScore(0);
                presenter.setInc(1);
                presenter.setIncNeed(10);
                presenter.setCritRatio(1);
                presenter.setCritRatioNeed(10);
                presenter.setCritInc(1);
                presenter.setCritIncNeed(10);
                setTextAll();
            }
        });
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("score_up", presenter.getScore());
                intent.putExtra("inc_up", presenter.getInc());
                intent.putExtra("inc_need_up", presenter.getIncNeed());
                intent.putExtra("crit_ratio_up", presenter.getCritRatio());
                intent.putExtra("crit_ratio_need_up", presenter.getCritRatioNeed());
                intent.putExtra("crit_inc_up", presenter.getCritInc());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    public void setTextScoreUpgrade(){
        score_text_upgrade.setText("점수 : "+Integer.toString(presenter.getScore()));
    }
    public void setTextInc(){
        inc_need_text.setText("+"+Integer.toString(presenter.getInc()));
    }
    public void setTextIncNeed(){
        button_add_inc.setText("비용 : "+Integer.toString(presenter.getIncNeed()));
    }
    public void setTextCritRatio(){
        crit_ratio_text.setText(Integer.toString(presenter.getCritRatio())+"%");
    }
    public void setTextCritRatioNeed(){
        button_crit_ratio.setText("비용 : "+Integer.toString(presenter.getCritRatioNeed()));
    }
    public void setTextCritInc(){
        //crit_inc_text.setText(Integer.toString(presenter.getCritIncNeed()));
    }
    public void setTextAll(){
        setTextScoreUpgrade();
        setTextInc();
        setTextIncNeed();
        setTextCritRatio();
        setTextCritRatioNeed();
        setTextCritInc();
    }
}
