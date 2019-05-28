package model;

import view.CombatDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy {

    private String name;
    private String iconPath;
    private int hp;
    private int dmg;
    private int expWorth;

    public Enemy(String name,int hp,int dmg,int expWorth,String iconPath) throws IOException {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.expWorth = expWorth;
        this.iconPath = iconPath;
    }

    public Enemy(Enemy enemy){
        this.name = enemy.getName();
        this.hp = enemy.getHp();
        this.dmg = enemy.getDmg();
        this.expWorth = enemy.getExpWorth();
        this.iconPath = enemy.getIconPath();
    }

    public String getStats(){

        StringBuilder sb = new StringBuilder();

        sb.append("Name: " + name + "\n" +
                "Hp: " + hp + "\n" +
                "Damage: " + dmg + "\n");

        String string = sb.toString();

        return string;
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
