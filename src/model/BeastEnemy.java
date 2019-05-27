package model;

public class BeastEnemy extends Enemy {

    private EnemyType type;

    public BeastEnemy(String name,int hp,int dmg,int expWorth){
        super(name,hp,dmg,expWorth);
        this.type = EnemyType.BEAST;
    }
}
