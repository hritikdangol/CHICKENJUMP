import javax.swing.SwingUtilities;
import src.Player;
import src.Chicken;
import src.Game;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Player player = new Player(1000);

            Chicken chicken = new Chicken(50, 395, 50, 50);

            Game game = new Game(player, chicken);

            new FrameGUI(game);

        });

    }
}