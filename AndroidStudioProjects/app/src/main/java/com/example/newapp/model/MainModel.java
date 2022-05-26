package com.example.newapp.model;

import android.content.Intent;

import com.example.newapp.view.UpgradeActivity;

import java.util.Random;
import java.util.Timer;

public class MainModel {

/////////////////////////////////////////////////////////////////////////
    private int score = 0;
    private int inc = 1;
    private int double_need = 10;

    private int crit_ratio = 1;
    private int crit_ratio_need = 10;

    private int crit_inc = 1;

    private int crit_check = 0;
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
    public void addScore(){
        this.score += inc;
    }
    public int  getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score = score;
    }

    public void addInc(){
        this.inc += 1;
    }
    public int  getInc(){
        return this.inc;
    }
    public void setInc(int mul){
        this.inc = mul;
    }

    public void addDoubleNeed(){
        this.double_need *= 2;
    }
    public int  getDoubleNeed(){
        return this.double_need;
    }
    public void setDoubleNeed(int double_need){
        this.double_need = double_need;
    }

    public void addCritRatio(){
        this.crit_ratio += 1;
    }
    public int  getCritRatio(){
        return this.crit_ratio;
    }
    public void setCritRatio(int crit_ratio){
        this.crit_ratio = crit_ratio;
    }

    public void addCritRatioNeed(){
        this.crit_ratio_need *= 2;
    }
    public int  getCritRatioNeed(){
        return this.crit_ratio_need;
    }
    public void setCritRatioNeed(int crit_ratio_need){
        this.crit_ratio_need = crit_ratio_need;
    }

    public void addCritInc(){
        this.crit_inc += 1;
    }
    public int  getCritInc(){
        return this.crit_inc;
    }
    public void setCritInc(int crit_inc){
        this.crit_inc = crit_inc;
    }
/////////////////////////////////////////////////////////////////////////
    public void addScoreCrit(){
        Random random = new Random();
        if(random.nextInt(100) <= crit_ratio){
            score += inc*2;
            crit_check = 1;
        }
    }
    public int flagCrit(){
        if(crit_check == 1){
            crit_check = 0;
            return 1;
        }
        else
            return 0;
    }
/////////////////////////////////////////////////////////////////////////
    public void startClick(){

    }
}
