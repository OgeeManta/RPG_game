package model;

public class BeastEnemy extends Enemy {

    private EnemyType type;

    public BeastEnemy(String name,int hp,int dmg){
        super(name,hp,dmg);
        this.type = EnemyType.BEAST;
    }
}
