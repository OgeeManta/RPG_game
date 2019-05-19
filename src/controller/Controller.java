package controller;

import model.Enemy;
import model.Player;
import view.CombatDisplay;
import view.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {

    private Player player;
    private Display display;
    private CombatDisplay combatDisplay;
    private Enemy enemy;

    public Controller(Player player,Enemy enemy,Display display,CombatDisplay combatDisplay){
        this.player = player;
        this.display = display;
        this.combatDisplay = combatDisplay;
        this.enemy = enemy;
    }

    public void updateStats(){
        display.getStats().setText( "Name: " + player.getName() + "\n" +
                                    "Level: " + player.getLevel()
                                    );
    }

    public  void combat(JFrame frame,Player player,Enemy enemy){
        frame.remove(display.getRootPanel());
        frame.add(combatDisplay.getCombatRootPanel());
        frame.revalidate();
    }

    public JFrame initFrame() throws IOException {
        JFrame frame = new JFrame("Title");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(display.getRootPanel());
        frame.setVisible(true);

        display.getStats().setEditable(false);

        BufferedImage image = ImageIO.read(new File("./resources/orc.png"));
        Image dimg = image.getScaledInstance(display.getPortraitLabel().getWidth(), display.getPortraitLabel().getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);

        display.getPortraitLabel().setIcon(imageIcon);

        display.getCombatBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combat(frame,player,enemy);
            }
        });

        return frame;
    }

}
