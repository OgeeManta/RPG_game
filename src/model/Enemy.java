package model;

import view.CombatDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy {

    private String name;
    private String iconPath;
    private int hp;
    private int dmg;
    private int expWorth;
    private ArrayList<Item> lootTable;
    private boolean boss;

    public Enemy(String name,int hp,int dmg,int expWorth,String iconPath,boolean boss,ArrayList<Item> lootTable) throws IOException {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.expWorth = expWorth;
        this.iconPath = iconPath;
        this.boss = boss;
        this.lootTable = lootTable;
    }

    public Enemy(Enemy enemy){
        this.name = enemy.getName();
        this.hp = enemy.getHp();
        this.dmg = enemy.getDmg();
        this.expWorth = enemy.getExpWorth();
        this.iconPath = enemy.getIconPath();
        this.boss = enemy.isBoss();
        this.lootTable = enemy.getLootTable();
    }

    public boolean isBoss() {
        return boss;
    }

    public String getStats(){

        StringBuilder sb = new StringBuilder();

        sb.append("Name: " + name + "\n" +
                "Hp: " + hp + "\n" +
                "Damage: " + dmg + "\n");

        String string = sb.toString();

        return string;
    }

    public ArrayList<Item> getLootTable() {
        return lootTable;
    }

    public void setLootTable(ArrayList<Item> lootTable) {
        this.lootTable = lootTable;
    }

    public String getIconPath() {
        return iconPath;
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
