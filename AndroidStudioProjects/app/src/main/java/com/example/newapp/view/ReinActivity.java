package com.example.newapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;

public class ReinActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rein);

        String[] enemy_default_id = {"@drawable/wolf", "@drawable/boss1_default"};
        String[] enemy_attack_id = {"@drawable/wolf", "@drawable/boss1_attack"};
        String packName = this.getPackageName();
    }
}
