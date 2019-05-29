package view;

import controller.Controller;
import model.Enemy;
import model.MapOfMainLand;
import model.Player;
import model.Square;

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

public class MainFrame extends JFrame implements KeyListener {

    private Display display;
    private CombatDisplay combatDisplay;
    private CharacterDisplay characterDisplay;

    public static boolean onMapDisplay;

    private Player player;
    private MapOfMainLand map;
    private Controller controller;
    private ArrayList<Enemy> enemies;

    public MainFrame(Controller controller, Player player, ArrayList<Enemy> enemies,Display display,CombatDisplay combatDisplay, CharacterDisplay characterDisplay,MapOfMainLand map) throws IOException {
        this.display = display;
        this.combatDisplay = combatDisplay;
        this.characterDisplay = characterDisplay;
        this.player = player;
        this.map = map;
        this.controller = controller;
        this.enemies = enemies;
        this.onMapDisplay = true;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(display.getRootPanel());
        setVisible(true);

        display.getStats().setEditable(false);
    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
            System.out.println("Left key pressed!");
            if( player.getCurrentLocation().getY() != 0 && this.onMapDisplay == true) {
                controller.clearMap(player.getCurrentLocation());
                player.getCurrentLocation().setLocation(player.getCurrentLocation().getX(), player.getCurrentLocation().getY() - 1);
                try {
                    controller.refreshMap(player.getCurrentLocation());
                    if(player.getCurrentLocation().getX() == 1 && player.getCurrentLocation().getY() == 5){
                        bossEncounter();
                    }else{
                        combatEncounter();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            System.out.println("Right key pressed!");
            if(player.getCurrentLocation().getY() != 5 && this.onMapDisplay == true) {
                controller.clearMap(player.getCurrentLocation());
                player.getCurrentLocation().setLocation(player.getCurrentLocation().getX(), player.getCurrentLocation().getY() + 1);
                try {
                    controller.refreshMap(player.getCurrentLocation());
                    if(player.getCurrentLocation().getX() == 1 && player.getCurrentLocation().getY() == 5){
                        bossEncounter();
                    }else{
                        combatEncounter();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(keyCode == KeyEvent.VK_DOWN){
            System.out.println("Down key pressed!");
            if(player.getCurrentLocation().getX() != 5 && this.onMapDisplay == true) {
                controller.clearMap(player.getCurrentLocation());
                player.getCurrentLocation().setLocation(player.getCurrentLocation().getX() + 1, player.getCurrentLocation().getY());
                try {
                    controller.refreshMap(player.getCurrentLocation());
                    if(player.getCurrentLocation().getX() == 1 && player.getCurrentLocation().getY() == 5){
                        bossEncounter();
                    }else{
                        combatEncounter();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(keyCode == KeyEvent.VK_UP){
            System.out.println("Up key pressed!");
            if( player.getCurrentLocation().getX() != 0 && this.onMapDisplay == true) {
                controller.clearMap(player.getCurrentLocation());
                player.getCurrentLocation().setLocation(player.getCurrentLocation().getX() - 1, player.getCurrentLocation().getY());
                try {
                    controller.refreshMap(player.getCurrentLocation());
                    if(player.getCurrentLocation().getX() == 1 && player.getCurrentLocation().getY() == 5){
                        bossEncounter();
                    }else{
                        combatEncounter();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void combatEncounter() throws IOException {
        if(player.getCanCombat() == true){
            if(Math.random() < 0.3) {
                Random random = new Random();
                controller.setCurrentEnemy(enemies.get(random.nextInt(enemies.size())));
                controller.combat(this, player, controller.getCurrentEnemy());
            }
        }
    }

    public void bossEncounter() throws IOException {
        if (player.isFirstBossDefeated() == false) {
            System.out.println(player.isFirstBossDefeated());
            Enemy boss = new Enemy("Dagobert Bácsi", 200, 15, 120, "./resources/volskath.png");
            controller.setCurrentEnemy(boss);
            controller.combat(this, player, controller.getCurrentEnemy());
            //controller.getEnemies().remove(controller.getCurrentEnemy());
            controller.clearMap(player.getCurrentLocation());
            controller.refreshMap(player.getCurrentLocation());
            player.setFirstBossDefeated(true);
        }else{
            combatEncounter();
        }
    }
}
