package controller;

import model.Player;
import view.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {

    private Player player;
    private Display display;

    public Controller(Player player,Display display){
        this.player = player;
        this.display = display;
    }

    public void updateStats(){
        display.getStats().setText( "Name: " + player.getName() + "\n" +
                                    "Level: " + player.getLevel()
                                    );
    }

    public void initFrame() throws IOException {
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
    }

}
