package model;

import javax.swing.*;

public class Consumable extends Item {

    private int heal;

    public Consumable(String name, String toolTipText, ItemType type, boolean canUseInCombat, ImageIcon icon, int heal){
        super(name,toolTipText,type,canUseInCombat,icon);
        this.heal = heal;
    }

    public Consumable(Consumable consumable){
        super(consumable.getName(), consumable.getToolTipText(), consumable.getType(), consumable.isCanUseInCombat(), consumable.getIcon());
        this.heal = consumable.heal;
    }

    public int getHeal() {
        return heal;
    }
}
