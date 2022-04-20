package com.example.newapp.presenter;

import com.example.newapp.model.UpgradeModel;

public class UpgradePresenter {
    UpgradeModel model = new UpgradeModel();

    public void addScore(){
        model.addScore();
    }
    public int  getScore(){
        return model.getScore();
    }
    public void setScore(int i){
        model.setScore(i);
    }

    public void addIncrease(){
        model.addIncrease();
    }
    public int  getIncrease(){
        return model.getIncrease();
    }
    public void setIncrease(int i){
        model.setIncrease(i);
    }

    public void addDoubleNeed(){
        model.addDoubleNeed();
    }
    public int  getDoubleNeed(){
        return model.getDoubleNeed();
    }
    public void setDoubleNeed(int i){
        model.setDoubleNeed(i);
    }

    public void addCritRatio(){
        model.addCritRatio();
    }
    public int  getCritRatio(){
        return model.getCritRatio();
    }
    public void setCritRatio(int i){
        model.setCritRatio(i);
    }

    public void addCritIncrease(){
        model.addCritIncrease();
    }
    public int  getCritIncrease(){
        return model.getCritIncrease();
    }
    public void setCritIncrease(int i){
        model.setCritIncrease(i);
    }
}
