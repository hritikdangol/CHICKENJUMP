import java.awt.*;
import javax.swing.*;
import src.Game;

public class FrameGUI extends JFrame {

    private Game game;

    private JLabel balanceLabel;
    private JLabel betLabel;
    private JLabel multiplierLabel;
    private JButton cashOutButton;
    private PanelGUI panel;

    public FrameGUI(Game game) {

        this.game = game;

        setTitle("Chicken Jump");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        balanceLabel = new JLabel("Balance: $" + game.getPlayer().getBalance());
        betLabel = new JLabel("Bet: $" + game.getPlayer().getCurrentBet());
        multiplierLabel = new JLabel("Multiplier: x1.00");

        // Button
        cashOutButton = new JButton("Cash Out");

        cashOutButton.addActionListener(e -> {

            game.cashOut();

            balanceLabel.setText("Balance: $" + game.getPlayer().getBalance());

        });

        // Bottom Panel
        JPanel bottom = new JPanel();

        bottom.add(balanceLabel);
        bottom.add(betLabel);
        bottom.add(multiplierLabel);
        bottom.add(cashOutButton);

        // Game Panel
        panel = new PanelGUI(game);

        setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);

        panel.requestFocusInWindow();
    }
}