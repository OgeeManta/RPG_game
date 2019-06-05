package model;

import view.CombatDisplay;

import java.io.IOException;
import java.util.ArrayList;

public class BeastEnemy extends Enemy {

    private EnemyType type;

    public BeastEnemy(String name, int hp, int dmg, int expWorth, String iconPath, boolean boss, ArrayList<Item> lootTable) throws IOException {
        super(name,hp,dmg,expWorth,iconPath,boss,lootTable);
        this.type = EnemyType.BEAST;
    }
}
