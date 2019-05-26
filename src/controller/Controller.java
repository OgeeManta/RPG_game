package controller;

import model.*;
import view.CombatDisplay;
import view.Display;
import view.TalentDisplay;

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
    private ArrayList<Enemy> enemies;
    private ArrayList<Talent> talents;
    private Enemy currentEnemy;
    private MapOfMainLand map;

    public Controller(Player player,ArrayList<Enemy> enemies,ArrayList<Talent> talents,Display display,CombatDisplay combatDisplay,TalentDisplay talentDisplay,MapOfMainLand map){
        this.player = player;
        this.talents = talents;
        this.display = display;
        this.combatDisplay = combatDisplay;
        this.talentDisplay = talentDisplay;
        this.enemies = enemies;
        this.map = map;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    public String getPlayerStatsString(){
        return ( "Name: " + player.getName() + "\n" +
                 "Level: " + player.getLevel() + "\n" +
                 "Damage: " + player.getDmg()
                );
    }

    public String getEnemyStatsString(){
        return (  currentEnemy.getName() + "\n" +
                 "Hp: " + currentEnemy.getHp() + "\n" +
                 "Damage: " + currentEnemy.getDmg()
        );
    }

    public void updateStats(){
        display.getStats().setText( "Name: " + player.getName() + "\n" +
                                    "Level: " + player.getLevel() + "\n" +
                                    "Damage: " + player.getDmg()
                                    );
    }

    public void refreshMap(Point p) throws IOException {
        BufferedImage playerImage = ImageIO.read(new File("./resources/circle.png"));
        Image playerImg = playerImage.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);
        map.getMap().get(p).setIcon(playerIcon);
    }

    public void clearMap(Point p){
        map.getMap().get(p).setIcon(null);
    }

    public  void combat(JFrame frame,Player player,Enemy enemy) throws IOException {
        frame.remove(display.getRootPanel());
        frame.add(combatDisplay.getCombatRootPanel());
        frame.revalidate();

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(combatDisplay.getPlayerPortraitLabel().getWidth(), combatDisplay.getPlayerPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        BufferedImage enemyImage = ImageIO.read(new File("./resources/portrait.jpg"));
        Image enemyImg = enemyImage.getScaledInstance(combatDisplay.getEnemyPortraitLabel().getWidth(), combatDisplay.getEnemyPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon enemyIcon = new ImageIcon(enemyImg);

        combatDisplay.getPlayerPortraitLabel().setIcon(playerIcon);
        combatDisplay.getEnemyPortraitLabel().setIcon(enemyIcon);

        combatDisplay.getPlayerStatsTextArea().setText(getPlayerStatsString());
        combatDisplay.getPlayerStatsTextArea().setEditable(false);
        combatDisplay.getEnemyStatsTextArea().setText(getEnemyStatsString());
        combatDisplay.getEnemyStatsTextArea().setEditable(false);

        combatDisplay.getCombatTextArea().setText("You have encountered " + enemy.getName() + "!");
        combatDisplay.getCombatTextArea().setEditable(false);



    }

    public void showTalentFrame(JFrame frame) throws IOException {
        frame.remove(display.getRootPanel());
        frame.add(talentDisplay.getTalentRootPanel());
        frame.revalidate();
        frame.repaint();

        BufferedImage Image = ImageIO.read(new File("./resources/sword.png"));
        Image swordImg = Image.getScaledInstance(talentDisplay.getOffenseButton0().getWidth(), talentDisplay.getOffenseButton0().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon swordIcon = new ImageIcon(swordImg);

        talentDisplay.getOffenseButton0().setIcon(swordIcon);

        talentDisplay.getOffenseButton0().setDisabledIcon(swordIcon);

        Image = ImageIO.read(new File("./resources/dagger.png"));
        Image daggerImg = Image.getScaledInstance(talentDisplay.getOffenseButton00().getWidth(), talentDisplay.getOffenseButton00().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon daggerIcon = new ImageIcon(daggerImg);

        talentDisplay.getOffenseButton00().setIcon(daggerIcon);
        talentDisplay.getOffenseButton00().setDisabledIcon(daggerIcon);

        Image = ImageIO.read(new File("./resources/VolskathAxe.png"));
        Image axeImg = Image.getScaledInstance(talentDisplay.getOffenseButton01().getWidth(), talentDisplay.getOffenseButton01().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon axeIcon = new ImageIcon(axeImg);

        talentDisplay.getOffenseButton01().setIcon(axeIcon);
        talentDisplay.getOffenseButton01().setDisabledIcon(axeIcon);

        refreshTalentPoints();

        initOffenseTalents();

        talentDisplay.getOffenseButton0().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                refreshPlayerTalent(0);

                //Unique modifier of talent
                player.setDmg(player.getDmg() + player.getTalents().get(0).getValue());

                refreshTalentDisplay(talentDisplay.getOffenseButton0(),player.getTalents().get(0));
                refreshTalentPoints();
                initOffenseTalents();
                turnOffButtonsIfNoPointsToSpend();
                frame.revalidate();
            }
        });

        talentDisplay.getOffenseButton00().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPlayerTalent(1);

                player.setCrit(player.getCrit() + player.getTalents().get(1).getValue());

                refreshTalentDisplay(talentDisplay.getOffenseButton00(),player.getTalents().get(1));
                refreshTalentPoints();
                initOffenseTalents();
                turnOffButtonsIfNoPointsToSpend();
                frame.revalidate();
            }
        });

        talentDisplay.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(talentDisplay.getTalentRootPanel());
                try {
                    initComponents(frame);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    public void refreshPlayerTalent(int index){
        player.getTalents().get(index).setUnlocked(true);
        player.setTalentPoints(player.getTalentPoints() - 1);
        player.getTalents().get(index).setLevel(player.getTalents().get(index).getLevel() + 1);
        player.getTalents().get(index).setValue(player.getTalents().get(index).getValue());
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

    public void refreshTalentDisplay(JButton button,Talent talent){
        button.setToolTipText(talent.getToolTipText() + " " + talent.getValue());
    }

    public void initOffenseTalents(){

        talentDisplay.getOffenseButton0().setEnabled(true);
        talentDisplay.getOffenseButton00().setEnabled(true);

        for(int i=0;i<player.getTalents().size();++i){
            if(player.getTalents().get(i).isUnlocked()){
                if(player.getTalents().get(0).isUnlocked()){
                    talentDisplay.getOffenseButton1().setEnabled(true);
                }
                if(player.getTalents().get(1).isUnlocked()){
                    talentDisplay.getOffenseButton2().setEnabled(true);
                }
            }
        }
    }

    public void initComponents(JFrame frame) throws IOException {

        frame.add(display.getRootPanel());
        frame.revalidate();
        frame.repaint();

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        display.getPortraitLabel().setIcon(playerIcon);

        updateStats();

        display.getCombatBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Random random = new Random();
                    setCurrentEnemy(enemies.get(random.nextInt(enemies.size())));
                    combat(frame, player, currentEnemy);
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
    }
}
