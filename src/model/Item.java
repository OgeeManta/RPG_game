package model;

import javax.swing.*;

public class Item {

    private String name;
    private String toolTipText;
    private ItemType type;
    private boolean canUseInCombat;
    private ImageIcon icon;

    public Item(String name,String toolTipText, ItemType type, boolean canUseInCombat, ImageIcon icon) {
        this.name = name;
        this.toolTipText = toolTipText;
        this.type = type;
        this.canUseInCombat = canUseInCombat;
        this.icon = icon;
    }

    public Item(Item item){
        this.name = item.name;
        this.toolTipText = item.toolTipText;
        this.type = item.type;
        this.canUseInCombat = item.canUseInCombat;
        this.icon = item.icon;
    }

    public String getName() {
        return name;
    }

    public String getToolTipText() {
        return toolTipText;
    }

    public ItemType getType() {
        return type;
    }

    public boolean isCanUseInCombat() {
        return canUseInCombat;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
