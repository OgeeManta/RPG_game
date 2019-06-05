package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ItemDatabase {

    private Consumable potion;
    private Consumable apple;
    private Weapon axe;
    private Item pebbles;

    public ItemDatabase() throws IOException {

        ImageIcon icon = getIcon("./resources/icons/icon.1_69.png");
        Consumable potion = new Consumable("Health Potion","A potion that heals you for 50 hp",ItemType.CONSUMABLE,true,icon,50);
        this.potion = potion;

        icon = getIcon("./resources/icons/icon.2_15.png");
        Consumable apple = new Consumable("Apple","An apple that restores 10 hp when eaten",ItemType.CONSUMABLE,true,icon,10);
        this.apple = apple;

        icon = getIcon("./resources/icons/icon.4_10.png");
        Weapon axe = new Weapon("Axe","An axe that increases your damage by 5",ItemType.WEAPON,false,icon,5);
        this.axe = axe;

        icon = getIcon("./resources/icons/icon.1_46.png");
        Item pebbles = new Item("Pebbles","A bunch of small stones",ItemType.JUNK,false,icon);
        this.pebbles = pebbles;

    }

    public Consumable getPotion() {
        return potion;
    }

    public Consumable getApple() {
        return apple;
    }

    public Weapon getAxe() {
        return axe;
    }

    public Item getPebbles() {
        return pebbles;
    }

    public ArrayList<Item> basicLootTable(){
        ArrayList<Item> loot = new ArrayList<Item>();
        Consumable apple = new Consumable(getApple());
        Item pebbles = new Item(getPebbles());
        loot.add(apple);
        loot.add(pebbles);
        return loot;
    }

    private ImageIcon getIcon(String path) throws IOException {
        BufferedImage Image = ImageIO.read(new File(path));
        java.awt.Image img = Image.getScaledInstance(50, 50,
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);

        return icon;
    }

}
