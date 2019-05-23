import controller.Controller;
import model.*;
import view.CombatDisplay;
import view.Display;
import view.MainFrame;
import view.TalentDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        Display display = new Display();
        CombatDisplay combatDisplay = new CombatDisplay();
        TalentDisplay talentDisplay = new TalentDisplay();
        TalentTree talentTree = new TalentTree(talentDisplay);
        MapOfMainLand map = new MapOfMainLand(display);
        Player player = new Player("Dugovics");
        player.setTalents(talentTree.getTalentList());

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new BeastEnemy("Wolf",25,2));
        enemies.add(new HumanoidEnemy("Bandit",30,1));


        Controller controller = new Controller(player,enemies,talentTree.getTalentList(),display,combatDisplay,talentDisplay,map);
        MainFrame frame = new MainFrame(controller,player,enemies,display,combatDisplay,map);
        controller.initComponents(frame);
        controller.updateStats();

    }


}
