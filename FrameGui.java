import java.awt.*;
import javax.swing.*;
import src.Game;
import src.InsufficientbalanceException;
import src.Invalidbetexception;
import src.InvalidstartGameException;

public class FrameGUI extends JFrame {

    private Game game;
    private JTextField betField;
    private JButton betButton;
    private JButton startButton;
    private JLabel balanceLabel;
    private JLabel betLabel;
    private JLabel multiplierLabel;
    private JButton cashOutButton;
    private PanelGUI panel;

    public FrameGUI(Game game) {

        this.game = game;
        setTitle("Chicken Jump");
        setSize(1366, 768);
        setLocationRelativeTo(null);//center ma rakha
        setResizable(false);  //resize off garxa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        balanceLabel = new JLabel("Balance: " + game.getPlayer().getBalance());
        betField = new JTextField(8);
        betButton = new JButton("Place Bet");
        startButton = new JButton("Start Game");
        betButton.addActionListener(e -> placeBet());
        startButton.addActionListener(e -> startGame());

        betLabel = new JLabel("Bet:0.0 " + game.getPlayer().getCurrentBet());
        multiplierLabel = new JLabel("Multiplier: x1.00");

        cashOutButton = new JButton("Cash Out");
        cashOutButton.setFocusable(false); // Prevent Space bar from clicking button

        cashOutButton.addActionListener(e -> {

            game.cashOut();

            game.getChicken().setX(40);
            game.getChicken().setY(620);
            panel.repaint();
            game.resetDisplay();
            updateBalance();
            updateMultiplier();
            betLabel.setText("Bet: 0.0");
            betField.setText("");

            panel.requestFocusInWindow();
        });
        JPanel bottom = new JPanel();

        bottom.add(balanceLabel);

        bottom.add(new JLabel("Bet"));
        bottom.add(betField);
        bottom.add(betButton);
        bottom.add(startButton);
        bottom.add(betLabel);
        bottom.add(multiplierLabel);
        bottom.add(cashOutButton);
        panel = new PanelGUI(game, this);
        panel.repaint();
        setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER); // windows ko boottom ma fix gana ko lagi
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);

        // Give keyboard focus to game panel
        SwingUtilities.invokeLater(() -> panel.requestFocusInWindow());
    }

    public void updateBalance() {
        balanceLabel.setText("Balance: $" + game.getPlayer().getBalance());
    }

    public void updateMultiplier() {

        if (game.getCurrentPath() == 0) {
            multiplierLabel.setText("Multiplier: x1.00");
            return;
        }

        double multiplier = game.getPaths().get(game.getCurrentPath() - 1).getMultiplier();

        multiplierLabel.setText("Multiplier: x" + multiplier);
    }

    public void resetBetUI() {
        betLabel.setText("Bet: 0.0");
        betField.setText("");
    }

    // Place Bet button
    private void placeBet() {

        try {

            double bet = Double.parseDouble(betField.getText());

            game.getPlayer().placeBet(bet);

            updateBalance();

            betLabel.setText("Bet: " + game.getPlayer().getCurrentBet());

            JOptionPane.showMessageDialog(this, "Bet Successful!");

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Enter a valid number.");

        } catch (Invalidbetexception | InsufficientbalanceException | InvalidstartGameException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

    }

    // Start Game button
    private void startGame() {

        try {

            game.getPlayer().startGame();

            // Reset game for a new round

            game.initializePaths();
            game.getChicken().setX(40);
            game.getChicken().setY(620);

            // Allow playing again
            game.startGame();

            panel.repaint();

            updateBalance();
            updateMultiplier();

            panel.requestFocusInWindow();

        } catch (InvalidstartGameException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}