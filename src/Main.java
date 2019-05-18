import controller.Controller;
import model.Player;
import view.Display;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Display display = new Display();
        Player player = new Player("Dugovics");

        Controller controller = new Controller(player,display);

        controller.initFrame();
        controller.updateStats();

    }
}
