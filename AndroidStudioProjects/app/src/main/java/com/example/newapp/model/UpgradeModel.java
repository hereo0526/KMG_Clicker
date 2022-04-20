package com.example.newapp.model;

public class UpgradeModel{

    private int score = 0;
    private int increase = 1;
    private int double_need = 10;

    private int critical_ratio = 1;
    private int critical_ratio_need = 10;

    private int critical_increase_need = 10;
    private int critical_increase = 1;

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

    public void addCritRatio(){
        this.critical_ratio += 1;
    }
    public int  getCritRatio(){
        return this.critical_ratio;
    }
    public void setCritRatio(int critical_ratio){
        this.critical_ratio = critical_ratio;
    }

    public void addCritIncrease(){
        this.critical_increase += 1;
    }
    public int  getCritIncrease(){
        return this.critical_increase;
    }
    public void setCritIncrease(int critical_increase){
        this.critical_increase = critical_increase;
    }
}
