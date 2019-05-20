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
    private Enemy currentEnemy;
    private MapOfMainLand map;

    public Controller(Player player,ArrayList<Enemy> enemies,Display display,CombatDisplay combatDisplay,TalentDisplay talentDisplay,MapOfMainLand map){
        this.player = player;
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
                 "Level: " + player.getLevel()
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
                                    "Level: " + player.getLevel()
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

        BufferedImage playerImage = ImageIO.read(new File("./resources/circle.png"));
        Image playerImg = playerImage.getScaledInstance(talentDisplay.getOffenseButton0().getWidth(), talentDisplay.getOffenseButton0().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        talentDisplay.getOffenseButton0().setIcon(playerIcon);

    }

    public void initComponents(JFrame frame) throws IOException {

        BufferedImage playerImage = ImageIO.read(new File("./resources/orc.png"));
        Image playerImg = playerImage.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon playerIcon = new ImageIcon(playerImg);

        display.getPortraitLabel().setIcon(playerIcon);

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
