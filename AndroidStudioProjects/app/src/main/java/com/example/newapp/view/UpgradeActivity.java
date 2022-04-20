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
    private TextView add_increase_text;

    private Button button_critical_ratio;
    private TextView critical_ratio_text;

    private Button button_critical_increase;
    private TextView critical_increase_text;

    private Button button_clear;

    UpgradePresenter presenter = new UpgradePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        score_text_upgrade = findViewById(R.id.score_text_upgrade);
        button_add_increase = findViewById(R.id.button_add_increase);
        add_increase_text = findViewById(R.id.add_increase_text);
        button_critical_ratio = findViewById(R.id.button_critical_ratio);
        critical_ratio_text = findViewById(R.id.critical_ratio_text);
        button_critical_increase = findViewById(R.id.button_critical_increase);
        critical_increase_text = findViewById(R.id.critical_increase_text);
        button_clear = findViewById(R.id.button_clear);

        Intent intent = getIntent();

        presenter.setScore(intent.getExtras().getInt("score"));
        presenter.setIncrease(intent.getExtras().getInt("increase"));
        presenter.setDoubleNeed(intent.getExtras().getInt("double_need"));
        presenter.setCritRatio(intent.getExtras().getInt("critical_ratio"));
        presenter.setCritIncrease(intent.getExtras().getInt("critical_increase"));
        button_add_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getScore() >= presenter.getDoubleNeed()) {
                    presenter.setScore(presenter.getScore() - presenter.getDoubleNeed());
                    presenter.addIncrease();
                    presenter.addDoubleNeed();
                    image_click.getLayoutParams().width += 20;
                    if(image_click.getLayoutParams().width >= 400)
                    {
                        image_click.setImageResource(R.drawable.buhwa);
                        image_click.getLayoutParams().width += 100;
                    }
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
    }
}
