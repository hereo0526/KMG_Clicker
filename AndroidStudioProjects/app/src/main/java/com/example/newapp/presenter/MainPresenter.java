package com.example.newapp.presenter;

import com.example.newapp.model.MainModel;
import com.example.newapp.view.MainActivity;

public class MainPresenter {

    MainModel model = new MainModel();

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

    public void addScoreCrit(){
        model.addScoreCrit();
    }
    public int flagCrit(){
        return model.flagCrit();
    }

    public void startClick(){
        model.startClick();
    }
}
