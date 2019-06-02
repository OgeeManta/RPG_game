package model;

import javax.swing.*;

public class Item {

    private String name;
    private ItemType type;
    private boolean canUseInCombat;
    private ImageIcon icon;

    public Item(String name, ItemType type, boolean canUseInCombat, ImageIcon icon) {
        this.name = name;
        this.type = type;
        this.canUseInCombat = canUseInCombat;
        this.icon = icon;
    }
}
