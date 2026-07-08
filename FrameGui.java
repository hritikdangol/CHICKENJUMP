import javax.swing.*;
import src.Game;
class GameFrame extends JFrame {

    private Game game;

    public GameFrame(Game game) {

        this.game = game;

        setTitle("Chicken Jump");

        setSize(900,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new PanelGUI(game));

        setVisible(true);

    }

}