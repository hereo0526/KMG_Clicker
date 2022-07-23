package com.example.newapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.presenter.ReinPresenter;

public class ReinActivity extends AppCompatActivity {

    ReinPresenter presenter = new ReinPresenter();

    private Button button_back;
    private ImageView image_weapon;
    private TextView text_point;
    private TextView text_next_level;
    private TextView text_rein;
    private Button button_next_level;
    private Button button_rein;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rein);

        button_back     = findViewById(R.id.button_back);
        image_weapon    = findViewById(R.id.image_weapon);
        text_point      = findViewById(R.id.text_point);
        text_next_level = findViewById(R.id.text_next_level);
        text_rein       = findViewById(R.id.text_rein);
        button_next_level = findViewById(R.id.button_next_level);
        button_rein       = findViewById(R.id.button_rein);

        String[] str_weapon = {"0", "@drawable/weapon1", "@drawable/weapon2", "@drawable/weapon3"};
        String packName = this.getPackageName();


        Intent intent = getIntent();
        presenter.setPoint(intent.getIntExtra("point", presenter.getPoint()));
        presenter.setIndexLevel(intent.getIntExtra("weapon_level", presenter.getIndexLevel()));
        setTextPoint();
        setTextNextLevelNeed();
        setTextReinNeed();

        int resId_default = getResources().getIdentifier(str_weapon[presenter.getIndexLevel()], "drawable", packName);
        image_weapon.setImageResource(resId_default);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("point", presenter.getPoint());
                intent.putExtra("weapon_level", presenter.getIndexLevel());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        button_next_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getPoint() >= presenter.getLevelNeed()){
                    presenter.setPoint(presenter.getPoint()-presenter.getLevelNeed());
                    setTextPoint();
                    presenter.setIndexLevel(presenter.getIndexLevel()+1);
                    int resId_default = getResources().getIdentifier(str_weapon[presenter.getIndexLevel()], "drawable", packName);
                    image_weapon.setImageResource(resId_default);
                    setTextNextLevelNeed();
                }
            }
        });

        button_rein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.getPoint() >=
                        presenter.getReinNeed()){
                    presenter.setPoint(presenter.getPoint()-presenter.getReinNeed());
                    setTextPoint();
                    presenter.setIndexRein(presenter.getIndexRein()+1);
                    setTextReinNeed();
                }
            }
        });
    }
    public void setTextPoint(){
        text_point.setText(Integer.toString(presenter.getPoint()));
    }
    public void setTextNextLevelNeed(){
        button_next_level.setText("비용 : "+presenter.getLevelNeed());
    }
    public void setTextReinNeed(){
        button_rein.setText("비용 : "+presenter.getReinNeed());
    }
}
