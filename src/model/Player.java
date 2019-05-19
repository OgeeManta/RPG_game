package model;

public class Player {

    private String name;
    private int level;
    private int exp;
    private double hp;
    private double dmg;

    public Player(String name){
        this.name = name;
        this.level = 1;
        this.exp = 0;
        this.hp = 200;
        this.dmg = 2;
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
