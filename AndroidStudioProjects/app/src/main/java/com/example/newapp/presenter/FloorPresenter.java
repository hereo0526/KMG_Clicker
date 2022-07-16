package com.example.newapp.presenter;

import com.example.newapp.model.FloorModel;

public class FloorPresenter {
    FloorModel model = new FloorModel();

    public int  getInc(){
        return model.getInc();
    }
    public void setInc(int i){
        model.setInc(i);
    }

    public int  getPoint(){
        return model.getPoint();
    }
    public void setPoint(int i){
        model.setPoint(i);
    }
}
