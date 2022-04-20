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

    public int getCritRatio(){
        return model.getCritRatio();
    }
    public int getCritIncrease(){
        return model.getCritIncrease();
    }

    public void addScoreCrit(){
        model.addScoreCrit();
    }
    public int flagCrit(){
        return model.flagCrit();
    }
}
