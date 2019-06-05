package model;

import view.CombatDisplay;

import java.io.IOException;
import java.util.ArrayList;

public class HumanoidEnemy extends Enemy{
    private EnemyType type;

    public HumanoidEnemy(String name, int hp, int dmg, int expWorth, String iconPath, boolean boss, ArrayList<Item> lootTable) throws IOException {
        super(name,hp,dmg,expWorth,iconPath,boss,lootTable);
        this.type = EnemyType.HUMANOID;
    }

}
