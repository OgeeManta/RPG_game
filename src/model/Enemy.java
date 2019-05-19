package model;

public class Enemy {

    private  String name;
    private int hp;
    private int dmg;

    public Enemy(String name,int hp,int dmg){
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}
