package model;

import view.CombatDisplay;
import view.SkillDisplay;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    private String name;
    private int level;
    private int currExp;
    private int exp;
    private double hp;
    private double mana;
    private double dmg;
    private double fireDmg;
    private double poisonDmg;
    private double crit;
    private boolean critHit;
    private ArrayList<Talent> talents;
    private ArrayList<Skill> skills;
    private Skill skillSlot1;
    private Skill skillSlot2;
    private Skill skillSlot3;
    private Skill skillSlot4;
    private int talentPoints;
    private Point currentLocation;
    private boolean canCombat;

    private boolean firstBossDefeated;

    public Player(String name){
        this.name = name;
        this.level = 1;
        this.currExp = 0;
        this.exp = 50;
        this.hp = 200;
        this.mana = 50;
        this.dmg = 2;
        this.fireDmg = 0;
        this.poisonDmg = 0;
        this.crit = 0;
        this.critHit = false;
        this.skills = new ArrayList<Skill>();
        this.talents = new ArrayList<Talent>();
        this.talentPoints = 5;
        this.currentLocation = new Point(0,0);
        this.canCombat = true;
        this.firstBossDefeated = false;
    }

    public String attackEnemy(Enemy enemy){
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        int result = r.nextInt(100);
        if(result > crit) {
            sb.append("You hit the enemy for : ");
            sb.append(getDmg());
            damageDisplay(enemy, sb);
            enemy.setHp(enemy.getHp() - (int) calculateDamage());
            setHp(getHp() - enemy.getDmg());
        }else{
            sb.append("You CRITICALLY hit the enemy for : ");
            sb.append(getDmg()*2);
            damageDisplay(enemy, sb);
            enemy.setHp(enemy.getHp() - (int) calculateDamage()*2);
            setHp(getHp() - enemy.getDmg());
        }

        return sb.toString();
    }

    public String useSkill(Enemy enemy, Skill skill, SkillDisplay skillDisplay){
        StringBuilder sb = new StringBuilder();
        if(mana >= skill.getManaCost()){
            if(skill.getName().equals("Double Strike")) {
                skillDisplay.getSkill_1().setToolTipText("You strike twice with your weapon dealing twice the amount of your overall damage to the target.");
                skill.setDamage((int)calculateDamage() * 2);
                sb.append("You have used your ");
                sb.append(skill.getName());
                sb.append(" skill on the enemy.\n");
                sb.append("The enemy suffered " + skill.getDamage() + " damage.\n");
                sb.append("The enemy retaliated for: ");
                sb.append(enemy.getDmg());
                sb.append(" damage" + "\n");
                enemy.setHp(enemy.getHp() - skill.getDamage());
                setHp(getHp() - enemy.getDmg());
                setMana(getMana() - skill.getManaCost());
            }
            if(skill.getName().equals("Lesser Heal")) {
                skillDisplay.getSkill_2().setToolTipText("Weak healing spell that heals you for a small amount.");
                skill.setHealing(skill.getHealing() * skill.getLevel());
                sb.append("You cast ");
                sb.append(skill.getName());
                sb.append(" on yourself.\n");
                sb.append("You healed yourself for " + skill.getHealing() + " health points..\n");
                sb.append("The enemy attack you for ");
                sb.append(enemy.getDmg());
                sb.append(" damage" + "\n");
                setHp(getHp() + skill.getHealing());
                setHp(getHp() - enemy.getDmg());
                setMana(getMana() - skill.getManaCost());
            }

        }else{
            sb.append("You don't have enough mana!");
        }

        return sb.toString();
    }

    public void initBasicSkills(){
        Skill doubleStrike = new Skill("Double Strike",(int)calculateDamage()*2,0,10);
        Skill lesserHeal = new Skill("Lesser Heal",0,25,10);
        Skill empty = new Skill("Empty",0,0,0);
        addSkill(doubleStrike);
        addSkill(lesserHeal);
        skillSlot1 = doubleStrike;
        skillSlot2 = lesserHeal;
        skillSlot3 = empty;
        skillSlot4 = empty;
    }

    public void addSkill(Skill skill){
        skills.add(skill);
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public Skill getSkillSlot1() {
        return skillSlot1;
    }

    public void setSkillSlot1(Skill skillSlot1) {
        this.skillSlot1 = skillSlot1;
    }

    public Skill getSkillSlot2() {
        return skillSlot2;
    }

    public void setSkillSlot2(Skill skillSlot2) {
        this.skillSlot2 = skillSlot2;
    }

    public Skill getSkillSlot3() {
        return skillSlot3;
    }

    public void setSkillSlot3(Skill skillSlot3) {
        this.skillSlot3 = skillSlot3;
    }

    public Skill getSkillSlot4() {
        return skillSlot4;
    }

    public void setSkillSlot4(Skill skillSlot4) {
        this.skillSlot4 = skillSlot4;
    }

    public double calculateDamage(){
        double damage = dmg + fireDmg + poisonDmg;
        return damage;
    }

    private void damageDisplay(Enemy enemy, StringBuilder sb) {
        sb.append(" damage" + "\n");
        if(fireDmg > 0){
            sb.append("You also inflicted " + fireDmg + " fire damage\n");
        }
        if(poisonDmg > 0){
            sb.append("The enemy also suffers " + poisonDmg + " poison damage\n");
        }
        sb.append("The enemy retaliated for: ");
        sb.append(enemy.getDmg());
        sb.append(" damage" + "\n");
    }

    public String getStats(){

        StringBuilder sb = new StringBuilder();

        sb.append("Name: " + name + "\n" +
                "Hp: " + hp + "\n" +
                "Mana: " + mana + "\n" +
                "Physical Damage: " + dmg + "\n" +
                "Elemental Damage: " + "\n" +
                "   -Fire: " + fireDmg + "\n" +
                "   -Poison: " + poisonDmg + "\n" +
                "Critical Hit Chance: " + crit + "%\n" +
                "Experience Points: " + currExp + "/" + exp + "\n" +
                "Talents: ");
        for(int i=0;i<talents.size();++i){
            if(talents.get(i).getLevel() != 0) {
                sb.append("" + talents.get(i).getName() + "(" + talents.get(i).getLevel() + ")\n").toString();
            }
        }

        String string = sb.toString();

        return string;
    }

    public boolean checkLevelUp(){
        if(currExp >= exp){
            currExp = 0;
            exp = exp + 100;
            talentPoints = talentPoints + 1;
            level = level + 1;
            return true;
        }else{
            return false;
        }
    }

    public double getCrit() {
        return crit;
    }

    public void setCrit(double crit) {
        this.crit = crit;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public boolean isCritHit() {
        return critHit;
    }

    public void setCritHit(boolean critHit) {
        this.critHit = critHit;
    }

    public int getCurrExp() {
        return currExp;
    }

    public void setCurrExp(int currExp) {
        this.currExp = currExp;
    }

    public boolean getCanCombat() {
        return canCombat;
    }

    public boolean isFirstBossDefeated() {
        return firstBossDefeated;
    }

    public void setFirstBossDefeated(boolean firstBossDefeated) {
        this.firstBossDefeated = firstBossDefeated;
    }

    public void setCanCombat(boolean canCombat) {
        this.canCombat = canCombat;
    }

    public int getTalentPoints() {
        return talentPoints;
    }

    public void spendTalentPoint(){
        talentPoints = talentPoints - 1;
    }

    public void increaseFireDmg(int damage){
        fireDmg = fireDmg + damage;
    }

    public void decreaseFireDmg(int damage){
        fireDmg = fireDmg - damage;
    }

    public void increasePoisonDmg(int damage){
        poisonDmg = poisonDmg + damage;
    }

    public void decreasePoisonDmg(int damage){
        poisonDmg = poisonDmg - damage;
    }

    public void increaseCrit(int critical){
        crit = crit + critical;
    }

    public void decreaseCrit(int critical){
        crit = crit - critical;
    }

    public void increaseDamage(int damage){
        dmg = dmg + damage;
    }

    public void decreaseDamage(int damage){
        dmg = dmg - damage;
    }

    public void setTalentPoints(int talentPoints) {
        this.talentPoints = talentPoints;
    }

    public ArrayList<Talent> getTalents() {
        return talents;
    }

    public void setTalents(ArrayList<Talent> talents) {
        this.talents = talents;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
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

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getDmg() {
        return dmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }
}
