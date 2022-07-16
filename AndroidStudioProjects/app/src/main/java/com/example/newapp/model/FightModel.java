package com.example.newapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FightModel {

/////////////////////////////////////////////////////////////////////////
    private int score = 0;
    private int inc = 1;
    private int inc_need = 10;
    private int crit_ratio = 1;
    private int crit_ratio_need = 10;
    private int crit_check = 0;

    private int my_health = 100;
    private int my_attack = 0;
    private int enemy_health = 0;
    private int enemy_attack = 0;
    private int enemy_index = 0;

    private int up_flag = 0;
    private int up_choose = 0;
    private int click_allow = 0;

    private int max = 2;
    private int flag_enemy_down = 0;

    private int clear = 0;

    private int index_stage = 1;

    int health_1[]  = {10, 20, 400};
    int attack_1[]  = {2, 5, 15};
    int point[] = {0, 10, 3, 5, 10};
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
    public void setInc(int inc){
        this.inc = inc;
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

    public int  getPoint(){
        return this.point[index_stage];
    }

    public int  getIndexStage(){
        return this.index_stage;
    }
    public void setIndexStage(int index_stage){
        this.index_stage = index_stage;
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
        return health_1[enemy_index];
    }
    public int getEnemyAttackArr(){
        return attack_1[enemy_index];
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

    public int getClear(){
        return this.clear;
    }
    public void setClear(int clear){
        this.clear = clear;
    }

    public int getMax(){
        return this.max;
    }
    public void setMax(int max){
        this.max = max;
    }

    public int getDownFlag(){
        return this.flag_enemy_down;
    }
    public void setDownFlag(int flag_enemy_down){
        this.flag_enemy_down = flag_enemy_down;
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
