package com.example.newapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainModel {

/////////////////////////////////////////////////////////////////////////
    private int score = 0;
    private int inc = 1;
    private int inc_need = 10;

    private int crit_ratio = 1;
    private int crit_ratio_need = 10;

    private int crit_inc = 1;

    private int crit_check = 0;

    private int my_health = 100;
    private int my_attack = 10;

    private int enemy_health = 1000;
    private int enemy_attack = 2;
    private int enemy_index = 1;

    private int up_flag = 0;
    private int up_choose = 0;
    private int click_allow = 0;
    ArrayList<Integer> enemy_health_arr = new ArrayList<Integer>(Arrays.asList(1000, 5000, 10000));
    ArrayList<Integer> enemy_attack_arr = new ArrayList<Integer>(Arrays.asList(2, 5, 15));
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
    public void addScore(){
        this.score += inc;
    }
    public int  getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score = score;
    }

    public void addInc(){
        this.inc += 1;
    }
    public int  getInc(){
        return this.inc;
    }
    public void setInc(int mul){
        this.inc = mul;
    }

    public void addIncNeed(){
        this.inc_need *= 2;
    }
    public int  getIncNeed(){
        return this.inc_need;
    }
    public void setIncNeed(int inc_need){
        this.inc_need = inc_need;
    }

    public void addCritRatio(){
        this.crit_ratio += 1;
    }
    public int  getCritRatio(){
        return this.crit_ratio;
    }
    public void setCritRatio(int crit_ratio){
        this.crit_ratio = crit_ratio;
    }

    public void addCritRatioNeed(){
        this.crit_ratio_need *= 2;
    }
    public int  getCritRatioNeed(){
        return this.crit_ratio_need;
    }
    public void setCritRatioNeed(int crit_ratio_need){
        this.crit_ratio_need = crit_ratio_need;
    }

    public void addCritInc(){
        this.crit_inc += 1;
    }
    public int  getCritInc(){
        return this.crit_inc;
    }
    public void setCritInc(int crit_inc){
        this.crit_inc = crit_inc;
    }

    public int getMyHealth(){
        return this.my_health;
    }
    public void setMyHealth(int myHealth){
        this.my_health = myHealth;
    }

    public int getMyAttack(){
        return this.my_attack;
    }
    public void setMyAttack(int myAttack){
        this.my_attack = myAttack;
    }

    public int getEnemyHealth(){
        return this.enemy_health;
    }
    public void setEnemyHealth(int enemy_health){
        this.enemy_health = enemy_health;
    }

    public int getEnemyAttack(){
        return this.enemy_attack;
    }
    public void setEnemyAttack(int enemyAttack){
        this.enemy_attack = enemyAttack;
    }

    public int getEnemyHealthArr(){
        return enemy_health_arr.get(enemy_index);
    }
    public int getEnemyAttackArr(){
        return enemy_attack_arr.get(enemy_index);
    }

    public int getEnemyIndex(){
        return this.enemy_index;
    }
    public void setEnemyIndex(int enemy_index){
        this.enemy_index = enemy_index;
    }

    public int getUpFlag(){
        return this.up_flag;
    }
    public void setUpFlag(int up_flag){
        this.up_flag = up_flag;
    }

    public int getUpChoose(){
        return this.up_choose;
    }
    public void setUpChoose(int up_choose){
        this.up_choose = up_choose;
    }

    public int getClickAllow(){
        return this.click_allow;
    }
    public void setCLickAllow(int click_allow){
        this.click_allow = click_allow;
    }
/////////////////////////////////////////////////////////////////////////
    public void addScoreCrit(){
        Random random = new Random();
        if(random.nextInt(100) <= crit_ratio){
            score += inc*2;
            crit_check = 1;
        }
    }
    public int flagCrit(){
        if(crit_check == 1){
            crit_check = 0;
            return 1;
        }
        else
            return 0;
    }

/////////////////////////////////////////////////////////////////////////

}
