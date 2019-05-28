package controller;

import model.*;
import view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Controller{

    private Player player;
    private Display display;
    private CombatDisplay combatDisplay;
    private TalentDisplay talentDisplay;
    private CharacterDisplay characterDisplay;
    private ArrayList<Enemy> enemies;
    private ArrayList<Talent> talents;
    private Enemy currentEnemy;
    private MapOfMainLand map;
    private Enemy tmpEnemy;

    public Controller(Player player,ArrayList<Enemy> enemies,ArrayList<Talent> talents,Display display,CombatDisplay combatDisplay,TalentDisplay talentDisplay, CharacterDisplay characterDisplay, MapOfMainLand map){
        this.player = player;
        this.talents = talents;
        this.display = display;
        this.combatDisplay = combatDisplay;
        this.talentDisplay = talentDisplay;
        this.characterDisplay = characterDisplay;
        this.enemies = enemies;
        this.map = map;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public void updateStats(){
        display.getStats().setText( "Name: " + player.getName() + "\n" +
                                    "Level: " + player.getLevel()
                                    );
    }

    public void refreshMap(Point p) throws IOException {
        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);
        map.getMap().get(p).setIcon(playerIcon);
    }

    public void clearMap(Point p){
        map.getMap().get(p).setIcon(null);
    }

    public  void combat(JFrame frame,Player player,Enemy enemy) throws IOException {
        MainFrame.onMapDisplay = false;
        tmpEnemy = new Enemy(getCurrentEnemy());
        combatDisplay.getEndCombatButton().setEnabled(false);
        frame.getContentPane().remove(display.getRootPanel());
        frame.getContentPane().add(combatDisplay.getCombatRootPanel());
        frame.revalidate();
        frame.repaint();

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(combatDisplay.getPlayerPortraitLabel().getWidth(), combatDisplay.getPlayerPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        BufferedImage enemyImage = ImageIO.read(new File(getCurrentEnemy().getIconPath()));
        Image enemyImg = enemyImage.getScaledInstance(combatDisplay.getEnemyPortraitLabel().getWidth(), combatDisplay.getEnemyPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon enemyIcon = new ImageIcon(enemyImg);

        combatDisplay.getPlayerPortraitLabel().setIcon(playerIcon);
        combatDisplay.getEnemyPortraitLabel().setIcon(enemyIcon);

        combatDisplay.getPlayerStatsTextArea().setText(player.getStats());
        combatDisplay.getPlayerStatsTextArea().setEditable(false);
        combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
        combatDisplay.getEnemyStatsTextArea().setEditable(false);

        combatDisplay.getCombatTextArea().setText("You have encountered " + enemy.getName() + "!");
        combatDisplay.getCombatTextArea().setEditable(false);





    }

    public void showTalentFrame(JFrame frame) throws IOException {

        MainFrame.onMapDisplay = false;
        frame.getContentPane().remove(display.getRootPanel());
        frame.getContentPane().add(talentDisplay.getTalentRootPanel());
        frame.revalidate();
        frame.repaint();

        initIcon("./resources/icons/icon.4_87.png",talentDisplay.getOffenseButton0());

        initIcon("./resources/icons/icon.1_36.png",talentDisplay.getOffenseButton00());

        initIcon("./resources/icons/icons8_79.png",talentDisplay.getOffenseButton1());

        initIcon("./resources/icons/icon.1_68.png",talentDisplay.getOffenseButton01());

        initIcon("./resources/icons/icon.2_23.png",talentDisplay.getOffenseButton2());

        refreshTalentPoints();

        initOffenseTalents();

        turnOffButtonsIfNoPointsToSpend();



    }

    public void initIcon(String path,JButton button) throws IOException {

        BufferedImage Image = ImageIO.read(new File(path));
        Image img = Image.getScaledInstance(button.getWidth(), button.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);

        button.setIcon(icon);
    }

    public void initUnlockedIcon(String path,JButton button) throws IOException {

        BufferedImage Image = ImageIO.read(new File(path));
        Image img = Image.getScaledInstance(button.getWidth(), button.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);

        button.setDisabledIcon(icon);
    }

    public void clickedTalent(JFrame frame, int index){
        if(player.getTalentPoints() > 0) {
            player.getTalents().get(index).setUnlocked(true);
            player.spendTalentPoint();
            player.getTalents().get(index).setLevel(player.getTalents().get(index).getLevel() + 1);
        }else{
            JOptionPane.showMessageDialog(frame,"You've no talent points to spend!");
        }
    }

    public void refreshTalentPoints(){
        talentDisplay.getPointsToSpend().setText("Talent points: " + player.getTalentPoints());
    }

    public void turnOffButtonsIfNoPointsToSpend(){
        if(player.getTalentPoints() <= 0){
            talentDisplay.getOffenseButton0().setEnabled(false);
            talentDisplay.getOffenseButton00().setEnabled(false);
            talentDisplay.getOffenseButton1().setEnabled(false);
            talentDisplay.getOffenseButton01().setEnabled(false);
            talentDisplay.getOffenseButton2().setEnabled(false);
            talentDisplay.getDefenseButton0().setEnabled(false);
            talentDisplay.getDefenseButton1().setEnabled(false);
            talentDisplay.getDefenseButton2().setEnabled(false);
            talentDisplay.getUtilityButton0().setEnabled(false);
            talentDisplay.getUtilityButton1().setEnabled(false);
            talentDisplay.getUtilityButton2().setEnabled(false);
        }
    }

    public void refreshTalentDisplay(JButton button,Talent talent,int nextLevel){
        StringBuilder sb = new StringBuilder();
        sb.append(talent.getToolTipText());
        int previousLevel = talent.getValue();
        talent.setValue(talent.getValue() + nextLevel);
        talent.setToolTipText(talent.getToolTipText() + previousLevel + " --> " + talent.getValue());
        button.setToolTipText(talent.getToolTipText());
        talent.setToolTipText(sb.toString());
    }

    public void initOffenseTalents() throws IOException {

        talentDisplay.getOffenseButton0().setEnabled(true);
        talentDisplay.getOffenseButton00().setEnabled(true);

        for(int i=0;i<player.getTalents().size();++i){
            if(player.getTalents().get(i).isUnlocked()){
                if(player.getTalents().get(0).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icon.4_87.png",talentDisplay.getOffenseButton0());
                    talentDisplay.getOffenseButton1().setEnabled(true);
                }
                if(player.getTalents().get(1).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icon.1_36.png",talentDisplay.getOffenseButton00());
                    talentDisplay.getOffenseButton01().setEnabled(true);
                }
                if(player.getTalents().get(2).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icons8_79.png",talentDisplay.getOffenseButton1());
                }
                if(player.getTalents().get(3).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icon.1_68.png",talentDisplay.getOffenseButton01());
                }
                if(player.getTalents().get(2).isUnlocked() && player.getTalents().get(3).isUnlocked()){
                    talentDisplay.getOffenseButton2().setEnabled(true);
                }
                if(player.getTalents().get(4).isUnlocked()){
                    initUnlockedIcon("./resources/icons/icon.2_23.png",talentDisplay.getOffenseButton2());
                }
            }
        }
    }

    public void showCharacterFrame(JFrame frame) throws IOException {

        MainFrame.onMapDisplay = false;
        frame.getContentPane().remove(display.getRootPanel());
        frame.getContentPane().add(characterDisplay.getCharRootPanel());
        frame.revalidate();
        frame.repaint();

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        characterDisplay.getPortraitLabel().setIcon(playerIcon);

        characterDisplay.getStats().setText(player.getStats());

    }

    public void initComponents(JFrame frame) throws IOException {

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        refreshMap(player.getCurrentLocation());

        display.getPortraitLabel().setIcon(playerIcon);

        display.getButton4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showCharacterFrame(frame);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        display.getButton3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showTalentFrame(frame);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        talentDisplay.getOffenseButton0().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,0);
                player.decreaseDamage(player.getTalents().get(0).getValue());
                refreshTalentDisplay(talentDisplay.getOffenseButton0(),player.getTalents().get(0),2);
                player.increaseDamage(player.getTalents().get(0).getValue());
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getOffenseButton00().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,1);
                refreshTalentDisplay(talentDisplay.getOffenseButton00(),player.getTalents().get(1),1);
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getOffenseButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,2);
                refreshTalentDisplay(talentDisplay.getOffenseButton1(),player.getTalents().get(2),5);
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getOffenseButton01().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,3);
                refreshTalentDisplay(talentDisplay.getOffenseButton01(),player.getTalents().get(3),3);
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getOffenseButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedTalent(frame,4);
                refreshTalentDisplay(talentDisplay.getOffenseButton2(),player.getTalents().get(4),1);
                refreshTalentPoints();
                try {
                    initOffenseTalents();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                turnOffButtonsIfNoPointsToSpend();
            }
        });

        talentDisplay.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.onMapDisplay = true;
                frame.getContentPane().remove(talentDisplay.getTalentRootPanel());
                frame.getContentPane().add(display.getRootPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        characterDisplay.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.onMapDisplay = true;
                frame.getContentPane().remove(characterDisplay.getCharRootPanel());
                frame.getContentPane().add(display.getRootPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        combatDisplay.getAttackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentEnemy().getHp() - player.getDmg() > 0){
                    String output = player.attackEnemy(getCurrentEnemy());
                    combatDisplay.getCombatTextArea().setText(output);
                    combatDisplay.getPlayerStatsTextArea().setText(player.getStats());
                    combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
                }else{
                    StringBuilder sb = new StringBuilder();
                    sb.append("You have defeated the enemy.\nYou received ");
                    sb.append(getCurrentEnemy().getExpWorth() + " experience points.");
                    combatDisplay.getCombatTextArea().setText(sb.toString());
                    player.setCurrExp(player.getCurrExp() + getCurrentEnemy().getExpWorth());
                    getCurrentEnemy().setHp(0);
                    combatDisplay.getEnemyStatsTextArea().setText(getCurrentEnemy().getStats());
                    combatDisplay.getEndCombatButton().setEnabled(true);
                }
            }
        });

        combatDisplay.getEndCombatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.onMapDisplay = true;
                enemies.remove(getCurrentEnemy());
                enemies.add(tmpEnemy);
                frame.getContentPane().remove(combatDisplay.getCombatRootPanel());
                frame.getContentPane().add(display.getRootPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

    }
}
