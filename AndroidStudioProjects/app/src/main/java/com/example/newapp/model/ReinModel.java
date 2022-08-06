package com.example.newapp.model;

public class ReinModel {
    /////////////////////////////////////////////////////////////////////////
    int point = 0;
    int index_level = 1;
    int index_rein = 1;
    int next_level_need[] = {0, 10, 30, 80};
    int rein_need[] = {0, 2, 5, 9, 15};
    /////////////////////////////////////////////////////////////////////////

    public int  getPoint(){
        return this.point;
    }
    public void setPoint(int point){
        this.point = point;
    }

    public int getIndexLevel(){
        return this.index_level;
    }
    public void setIndexLevel(int index_level){
        this.index_level = index_level;
    }

    public int getIndexRein(){
        return this.index_rein;
    }
    public void setIndexRein(int index_rein){
        this.index_rein = index_rein;
    }

    public int getLevelNeed(){
        return this.next_level_need[index_level];
    }
    public int getReinNeed(){
        return this.rein_need[index_rein];
    }


}
