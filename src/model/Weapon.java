package model;

import javax.swing.*;

public class Weapon extends Item {

    private int damage;

    public Weapon(String name, String toolTipText, ItemType type, boolean canUseInCombat, ImageIcon icon, int damage){
        super(name,toolTipText,type,canUseInCombat,icon);
        this.damage = damage;
    }

    public Weapon(Weapon weapon){
        super(weapon.getName(), weapon.getToolTipText(), weapon.getType(), weapon.isCanUseInCombat(), weapon.getIcon());
        this.damage = weapon.damage;
    }

    public int getDamage() {
        return damage;
    }

}
