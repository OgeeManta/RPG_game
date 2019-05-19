import controller.Controller;
import model.Enemy;
import model.Player;
import view.CombatDisplay;
import view.Display;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Display display = new Display();
        CombatDisplay combatDisplay = new CombatDisplay();
        Player player = new Player("Dugovics");
        Enemy enemy = new Enemy("Bandit",100,1);

        Controller controller = new Controller(player,enemy,display,combatDisplay);

        JFrame frame = controller.initFrame();


        controller.updateStats();

    }
}
