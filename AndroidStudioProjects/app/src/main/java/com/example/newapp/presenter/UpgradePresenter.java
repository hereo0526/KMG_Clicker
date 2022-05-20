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

    public void addInc(){
        model.addInc();
    }
    public int  getInc(){
        return model.getInc();
    }
    public void setInc(int i){
        model.setInc(i);
    }

    public void addIncNeed(){
        model.addIncNeed();
    }
    public int  getIncNeed(){
        return model.getIncNeed();
    }
    public void setIncNeed(int i){
        model.setIncNeed(i);
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

    public void addCritRatioNeed(){
        model.addCritRatioNeed();
    }
    public int  getCritRatioNeed(){
        return model.getCritRatioNeed();
    }
    public void setCritRatioNeed(int i){
        model.setCritRatioNeed(i);
    }

    public void addCritInc(){
        model.addCritInc();
    }
    public int  getCritInc(){
        return model.getCritInc();
    }
    public void setCritInc(int i){
        model.setCritInc(i);
    }

    public void addCritIncNeed(){
        model.addCritIncNeed();
    }
    public int  getCritIncNeed(){
        return model.getCritIncNeed();
    }
    public void setCritIncNeed(int i){
        model.setCritIncNeed(i);
    }
}
