package com.example.newapp.model;

public class MainModel {

    private int score = 0;

    public void changeScore(){
        this.score += 5;
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score = score;
    }

}
