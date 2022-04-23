package com.example.newapp.view;

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

    private Button button_add_increase;
    private TextView double_need_text;

    private Button button_critical_ratio;
    private TextView critical_ratio_text;

    private Button button_critical_increase;
    private TextView critical_increase_text;

    private Button button_clear;
    private ImageView image_back;

    UpgradePresenter presenter = new UpgradePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        score_text_upgrade = findViewById(R.id.score_text_upgrade);
        button_add_increase = findViewById(R.id.button_add_increase);
        double_need_text = findViewById(R.id.double_need_text);
        button_critical_ratio = findViewById(R.id.button_critical_ratio);
        critical_ratio_text = findViewById(R.id.critical_ratio_text);
        button_critical_increase = findViewById(R.id.button_critical_increase);
        critical_increase_text = findViewById(R.id.critical_increase_text);
        button_clear = findViewById(R.id.button_clear);
        image_back = findViewById(R.id.image_back);

        Intent intent = getIntent();

        presenter.setScore(intent.getExtras().getInt("score"));
        presenter.setIncrease(intent.getExtras().getInt("increase"));
        presenter.setDoubleNeed(intent.getExtras().getInt("double_need"));
        presenter.setCritRatio(intent.getExtras().getInt("critical_ratio"));
        presenter.setCritIncrease(intent.getExtras().getInt("critical_increase"));

        setTextScoreUpgrade();
        setTextDoubleNeed();
        setTextCritRatio();
        setTextCritIncrease();

        button_add_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getScore() >= presenter.getDoubleNeed()) {
                    presenter.setScore(presenter.getScore() - presenter.getDoubleNeed());
                    presenter.addIncrease();
                    presenter.addDoubleNeed();
                    setTextIncreaseAll();
                }
            }
        });
        /*
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
        });*/
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpgradeActivity.this, MainActivity.class);
                intent.putExtra("score_up", presenter.getScore());
                intent.putExtra("increase_up", presenter.getIncrease());
                intent.putExtra("double_need_up", presenter.getDoubleNeed());
                intent.putExtra("critical_ratio_up", presenter.getCritRatio());
                intent.putExtra("critical_increase_up", presenter.getCritIncrease());
                finish();
            }
        });
    }
    public void setTextScoreUpgrade(){
        score_text_upgrade.setText(Integer.toString(presenter.getScore()));
    }
    public void setTextIncrease(){
        button_add_increase.setText("클릭점수"+"+"+Integer.toString(presenter.getIncrease()));
    }
    public void setTextDoubleNeed(){
        double_need_text.setText(Integer.toString(presenter.getDoubleNeed()));
    }
    public void setTextCritRatio(){
        critical_ratio_text.setText(Integer.toString(presenter.getCritRatio()));
    }
    public void setTextCritIncrease(){
        critical_increase_text.setText(Integer.toString(presenter.getCritIncrease()));
    }
    public void setTextIncreaseAll(){
        setTextScoreUpgrade();
        setTextIncrease();
        setTextDoubleNeed();

    }
}
