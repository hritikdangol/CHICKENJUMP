package src;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Game {

    private Player player;
    private Chicken chicken;
    private ArrayList<Path> paths; // multiple paths ko lagi arraylist banako
    private int currentPath;
    private boolean gameOver;
    private int firePathIndex;
    private boolean started = false;

    public Game(Player player, Chicken chicken) {
        this.player = player;
        this.chicken = chicken;
        paths = new ArrayList<>();
        initializePaths();
        currentPath = 0;
        gameOver = false;
        firePathIndex = -1;
    }

    public void startGame() {

        paths.clear();

        for (int i = 1; i <= 25; i++) {
            paths.add(new Path(i));
        }

        currentPath = 0;

        gameOver = false; // Reset game over

        firePathIndex = -1; // Remove old fire
        started = true;
    }

    public void jump() {
        if (gameOver)
            return;
        chicken.jump();
        currentPath++;
       chicken.move(125);
        // Player wins but game does NOT restart automatically
        if (currentPath >= paths.size()) {

            double won = getPlayer().getCurrentBet()
                    * paths.get(paths.size() - 1).getMultiplier();

            player.setBalance(player.getBalance() + won);

            JOptionPane.showMessageDialog(null,
                    "You Won! Amount: $" + won);

            // Stop current game
            gameOver = true;
            started = false;
            player.resetBet();

            return;
        }

        Path current = paths.get(currentPath);

        if (current.hasFire()) {
            System.out.println("Game over");
            firePathIndex = currentPath;// 0-15
            gameOver = true;
            started = false;
        }
    }

    public void cashOut() {

        if (gameOver)
            return;

        if (currentPath == 0) {
            JOptionPane.showMessageDialog(null, "Jump at least one path");
            return;
        }

        Path current = paths.get(currentPath - 1);

        double won = player.getCurrentBet() * current.getMultiplier();

        player.setBalance(player.getBalance() + won);

        JOptionPane.showMessageDialog(null, "YouWon: " + won);

        gameOver = true;
        started = false;
        player.resetBet();
    }

    // game suru huna bittikai paths create garna lai
    public void initializePaths() {

        paths.clear();

        for (int i = 1; i <= 25; i++) {
            paths.add(new Path(i));
        }

        currentPath = 0;
        gameOver = false;
        firePathIndex = -1;
        started = false; // game nachalunako lagi till astartgame button click garxa
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public Chicken getChicken() {
        return chicken;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrentPath() {
        return currentPath;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getFirePathIndex() {
        return firePathIndex;
    }

    public boolean isStarted() {
        return started;
    }

    public void resetDisplay() {
        gameOver = false;
        firePathIndex = -1;
    }
}