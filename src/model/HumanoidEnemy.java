package model;

import view.CombatDisplay;

import java.io.IOException;

public class HumanoidEnemy extends Enemy{
    private EnemyType type;

    public HumanoidEnemy(String name, int hp, int dmg, int expWorth,String iconPath) throws IOException {
        super(name,hp,dmg,expWorth,iconPath);
        this.type = EnemyType.HUMANOID;
    }

}
