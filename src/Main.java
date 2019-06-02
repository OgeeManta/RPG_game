import controller.Controller;
import model.*;
import view.*;

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
        CharacterDisplay characterDisplay = new CharacterDisplay();
        SkillDisplay skillDisplay = new SkillDisplay();
        EquipSkillDisplay equipSkillDisplay = new EquipSkillDisplay();
        InventoryDisplay inventoryDisplay = new InventoryDisplay();
        TalentTree talentTree = new TalentTree(talentDisplay);
        MapOfMainLand map = new MapOfMainLand(display);
        Player player = new Player("Dugovics");
        player.setTalents(talentTree.getTalentList());
        player.initBasicSkills();

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new BeastEnemy("Goblin",25,2,10,"./resources/goblinWorker.png"));
        enemies.add(new HumanoidEnemy("Bandit",30,1,15,"./resources/thug.png"));


        Controller controller = new Controller(player,enemies,talentTree.getTalentList(),display,combatDisplay,talentDisplay,characterDisplay,skillDisplay,equipSkillDisplay,inventoryDisplay,map);
        MainFrame frame = new MainFrame(controller,player,enemies,display,combatDisplay,characterDisplay,map);
        controller.initComponents(frame);
        controller.updateStats();

    }


}
