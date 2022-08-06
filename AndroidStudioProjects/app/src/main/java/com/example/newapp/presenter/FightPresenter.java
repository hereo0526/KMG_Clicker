package com.example.newapp.presenter;

import com.example.newapp.model.FightModel;
import com.example.newapp.view.FightActivity;

public class FightPresenter {

    FightModel model = new FightModel();

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

    public int  getPoint(int i){
        return model.getPoint(i);
    }

    public void addScoreCrit(){
        model.addScoreCrit();
    }
    public int flagCrit(){
        return model.flagCrit();
    }

    public int getMyHealth(){
        return model.getMyHealth();
    }
    public void setMyHealth(int i){
        model.setMyHealth(i);
    }

    public int getMyAttack(){
        return model.getMyAttack();
    }
    public void setMyAttack(int i){
        model.setMyAttack(i);
    }

    public int getEnemyHealth(){
        return model.getEnemyHealth();
    }
    public void setEnemyHealth(int i){
        model.setEnemyHealth(i);
    }
    public int getEnemyHealthDefault(int i){
        return model.getEnemyHealthDefault(i);
    }

    public int getEnemyAttack(){
        return model.getEnemyAttack();
    }
    public void setEnemyAttack(int i){
        model.setEnemyAttack(i);
    }

    public void setEnemyDefault(int i){
        model.setEnemyDefault(i);
    }

    public int getEnemyIndex(){
        return model.getEnemyIndex();
    }
    public void setEnemyIndex(int i){
        model.setEnemyIndex(i);
    }

    public int getUpFlag(){
        return model.getUpFlag();
    }
    public void setUpFlag(int i){
        model.setUpFlag(i);
    }

    public int getUpChoose(){
        return model.getUpChoose();
    }
    public void setUpChoose(int i){
        model.setUpChoose(i);
    }

    public int getClickAllow(){
        return model.getClickAllow();
    }
    public void setClickAllow(int i){
        model.setCLickAllow(i);
    }

    public int getClear(){
        return model.getClear();
    }
    public void setClear(int i){
        model.setClear(i);
    }

    public int getMax(){
        return model.getMax();
    }
    public void setMax(int i){
        model.setMax(i);
    }

    public int getDownFlag(){
        return model.getDownFlag();
    }
    public void setDownFlag(int i){
        model.setDownFlag(i);
    }
}
