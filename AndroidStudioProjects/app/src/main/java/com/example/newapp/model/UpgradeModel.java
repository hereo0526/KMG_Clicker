package com.example.newapp.model;

public class UpgradeModel{

    private int score = 0;
    private int inc = 1;
    private int inc_need = 10;

    private int crit_ratio = 1;
    private int crit_ratio_need = 10;

    private int crit_inc = 1;
    private int crit_inc_need = 10;

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

    public void addIncNeed(){
        this.inc_need *= 2;
    }
    public int  getIncNeed(){
        return this.inc_need;
    }
    public void setIncNeed(int inc_need){
        this.inc_need = inc_need;
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

    public void addCritIncNeed(){
        this.crit_inc_need *= 2;
    }
    public int  getCritIncNeed(){
        return this.crit_inc_need;
    }
    public void setCritIncNeed(int crit_inc_need){
        this.crit_inc_need = crit_inc_need;
    }
}
