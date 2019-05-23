package model;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    private String name;
    private int level;
    private int exp;
    private double hp;
    private double dmg;
    private ArrayList<Talent> talents;
    private int talentPoints;
    private Point currentLocation;

    public Player(String name){
        this.name = name;
        this.level = 1;
        this.exp = 0;
        this.hp = 200;
        this.dmg = 2;
        this.talents = new ArrayList<Talent>();
        this.talentPoints = 1;
        this.currentLocation = new Point(0,0);
    }

    public int getTalentPoints() {
        return talentPoints;
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
