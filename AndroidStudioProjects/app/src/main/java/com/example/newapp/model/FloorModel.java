package com.example.newapp.model;

public class FloorModel {
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private int inc = 0;
    private int point = 0;
    private int my_health = 0;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public int  getInc(){
        return this.inc;
    }
    public void setInc(int inc){
        this.inc = inc;
    }

    public int  getPoint(){
        return this.point;
    }
    public void setPoint(int point){
        this.point = point;
    }

    public int  getMyHealth(){
        return this.my_health;
    }
    public void setMyHealth(int my_health){
        this.my_health = my_health;
    }
}
