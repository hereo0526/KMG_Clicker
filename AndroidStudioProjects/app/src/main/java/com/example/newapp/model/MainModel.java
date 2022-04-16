package com.example.newapp.model;

import java.util.Random;

public class MainModel {

    private int score = 0;
    private int increase = 1;
    private int double_need = 10;
    private int critical = 1;
    private int critical_increase;
    private int critical_check;

    public void addScore(){
        this.score += increase;
    }
    public int  getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score = score;
    }

    public void addIncrease(){
        this.increase += 1;
    }
    public int  getIncrease(){
        return this.increase;
    }
    public void setIncrease(int mul){
        this.increase = mul;
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

    public void addScoreCrit(){
        Random random = new Random();
        if(random.nextInt(100) <= 50){
            score += critical;
            critical_check = 1;
        }
    }
    public int flagCrit(){
        if(critical_check == 1){
            critical_check = 0;
            return 1;
        }
        else
            return 0;
    }
    public int getCrit(){
        return critical;
    }
    public void setCrit(){
        critical = increase + critical_increase;
    }
}
