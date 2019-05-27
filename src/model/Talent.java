package model;

public class Talent {

    private String name;
    private String toolTipText;
    private int value;
    private int level;
    private boolean unlocked;

    public Talent(String name,String toolTipText,int value){
        this.name = name;
        this.toolTipText = toolTipText;
        this.unlocked = false;
        this.value = value;
        this.level = 0;
    }



    public String getToolTipText() {
        return toolTipText;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}
