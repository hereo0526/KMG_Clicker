package com.example.newapp.model;

public class MainModel {

    private int score = 0;
    private int mul = 1;
    private int double_need = 10;

    public void addScore(){
        this.score += mul;
    }
    public int  getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score = score;
    }

    public void addIncrease(){
        this.mul = this.mul*2 + 1;
    }
    public int  getIncrease(){
        return this.mul;
    }
    public void setIncrease(int mul){
        this.mul = mul;
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
