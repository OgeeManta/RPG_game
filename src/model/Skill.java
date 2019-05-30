package model;

public class Skill {

    private String name;
    private int damage;
    private int healing;
    private int manaCost;
    private int level;

    public Skill(String name,int damage,int healing,int manaCost){
        this.name = name;
        this.damage = damage;
        this. healing = healing;
        this.manaCost = manaCost;
        this.level = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealing() {
        return healing;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}
