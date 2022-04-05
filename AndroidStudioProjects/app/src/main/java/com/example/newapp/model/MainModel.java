package com.example.newapp.model;

public class MainModel {

    private int score = 0;
    private int increase = 1;
    private int double_need = 10;

    private int saved_score;
    private int saved_increase;
    private int saved_double_need;

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
        this.increase = this.increase*2 + 1;
    }
    public int  getIncrease(){
        return this.increase;
    }
    public void setIncrease(int mul){
        this.increase = mul;
    }

    public void addDoubleNeed(){
        this.double_need *= 3;
    }
    public int  getDoubleNeed(){
        return this.double_need;
    }
    public void setDoubleNeed(int double_need){
        this.double_need = double_need;
    }
}
