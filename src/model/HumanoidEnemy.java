package model;

public class HumanoidEnemy extends Enemy{
    private EnemyType type;

    public HumanoidEnemy(String name,int hp,int dmg){
        super(name,hp,dmg);
        this.type = EnemyType.HUMANOID;
    }

}
