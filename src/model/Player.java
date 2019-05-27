package model;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    private String name;
    private int level;
    private int currExp;
    private int exp;
    private double hp;
    private double dmg;
    private ArrayList<Talent> talents;
    private int talentPoints;
    private Point currentLocation;
    private boolean canCombat;

    public Player(String name){
        this.name = name;
        this.level = 1;
        this.currExp = 0;
        this.exp = 50;
        this.hp = 200;
        this.dmg = 2;
        this.talents = new ArrayList<Talent>();
        this.talentPoints = 5;
        this.currentLocation = new Point(0,0);
        this.canCombat = true;
    }

    public String attackEnemy(Enemy enemy){
        StringBuilder sb = new StringBuilder();
        sb.append("You hit the enemy for : ");
        sb.append(getDmg());
        sb.append(" damage" + "\n");
        sb.append("The enemy retaliated for: ");
        sb.append(enemy.getDmg());
        sb.append(" damage" + "\n");
        enemy.setHp(enemy.getHp() - (int)getDmg());
        setHp(getHp() - enemy.getDmg());

        return sb.toString();
    }

    public String getStats(){

        StringBuilder sb = new StringBuilder();

        sb.append("Name: " + name + "\n" +
                "Hp: " + hp + "\n" +
                "Damage: " + dmg + "\n" +
                "exp: " + currExp + "/" + exp + "\n" +
                "Talents: ");
        for(int i=0;i<talents.size();++i){
            if(talents.get(i).getLevel() != 0) {
                sb.append("" + talents.get(i).getName() + "(" + talents.get(i).getLevel() + ") , ").toString();
            }
        }

        String string = sb.toString();

        return string;
    }

    public int getCurrExp() {
        return currExp;
    }

    public void setCurrExp(int currExp) {
        this.currExp = currExp;
    }

    public boolean getCanCombat() {
        return canCombat;
    }

    public void setCanCombat(boolean canCombat) {
        this.canCombat = canCombat;
    }

    public int getTalentPoints() {
        return talentPoints;
    }

    public void spendTalentPoint(){
        talentPoints = talentPoints - 1;
    }

    public void increaseDamage(int damage){
        dmg = dmg + damage;
    }

    public void decreaseDamage(int damage){
        dmg = dmg - damage;
    }

    public void setTalentPoints(int talentPoints) {
        this.talentPoints = talentPoints;
    }

    public ArrayList<Talent> getTalents() {
        return talents;
    }

    public void setTalents(ArrayList<Talent> talents) {
        this.talents = talents;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getDmg() {
        return dmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }
}
