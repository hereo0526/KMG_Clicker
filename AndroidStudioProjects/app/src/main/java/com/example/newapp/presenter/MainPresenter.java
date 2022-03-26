package com.example.newapp.presenter;

import com.example.newapp.model.MainModel;
import com.example.newapp.view.MainActivity;

public class MainPresenter {

    MainModel model = new MainModel();
    MainActivity view;
    public String changeText(){
        view = new MainActivity();
        model.changeScore();
        //view.showText(Integer.toString(model.getScore()));
        return Integer.toString(model.getScore());
    }

}
