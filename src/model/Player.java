package model;

public class Player {

    private String name;
    private int level;

    public Player(String name){
        this.name = name;
        this.level = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getLevel() {
        return level;
    }
}
