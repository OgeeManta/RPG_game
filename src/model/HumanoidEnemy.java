package model;

public class HumanoidEnemy extends Enemy{
    private EnemyType type;

    public HumanoidEnemy(String name,int hp,int dmg,int expWorth){
        super(name,hp,dmg,expWorth);
        this.type = EnemyType.HUMANOID;
    }

}
