package com.example.newapp.presenter;

import com.example.newapp.model.ReinModel;

public class ReinPresenter {
    ReinModel model = new ReinModel();

    public int  getPoint(){
        return model.getPoint();
    }
    public void setPoint(int i){
        model.setPoint(i);
    }

    public int  getIndexLevel(){
        return model.getIndexLevel();
    }
    public void setIndexLevel(int i){
        model.setIndexLevel(i);
    }

    public int getIndexRein(){
        return model.getIndexRein();
    }
    public void setIndexRein(int i){
        model.setIndexRein(i);
    }

    public int getLevelNeed(){
        return model.getLevelNeed();
    }
    public int getReinNeed(){
        return model.getReinNeed();
    }
}
