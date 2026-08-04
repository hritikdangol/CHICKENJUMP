import javax.swing.SwingUtilities;
import src.Chicken;
import src.Game;
import src.Player;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Player player = new Player(1000);

            Chicken chicken = new Chicken(40, 620, 150, 65);

            Game game = new Game(player, chicken);

            new FrameGUI(game);

        });

    }
}