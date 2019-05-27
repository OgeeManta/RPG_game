package model;

public class Enemy {

    private  String name;
    private int hp;
    private int dmg;
    private int expWorth;

    public Enemy(String name,int hp,int dmg,int expWorth){
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.expWorth = expWorth;
    }

    public String getStats(){

        StringBuilder sb = new StringBuilder();

        sb.append("Name: " + name + "\n" +
                "Hp: " + hp + "\n" +
                "Damage: " + dmg + "\n");

        String string = sb.toString();

        return string;
    }

    public int getExpWorth() {
        return expWorth;
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
