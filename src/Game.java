package src;
import java.util.ArrayList;
public class Game {
    private Player player;
    private Chicken chicken;
    private ArrayList<Path> paths;
    private int currentPath;
    private boolean gameOver;
    public Game(Player player, Chicken chicken) {
        this.player = player;
        this.chicken = chicken;

        paths = new ArrayList<>();
        currentPath = 0;
        gameOver = false;
    }
    public void startGame() {
        paths.clear();
        for (int i = 1; i <= 15; i++) {
            paths.add(new Path(i));
        }
        currentPath = 0;
        gameOver = false;
        System.out.println("Game Started!");
    }
    public void jump() {
        if (gameOver) {
            return;
        }
        else
        chicken.jump();
        currentPath++;
        if (currentPath <= paths.size()) {
            Path current = paths.get(currentPath - 1);
            if (current.hasFire()) {
                System.out.println("Game Over!");
                gameOver = true;
            } else {
                System.out.println("Safe");
                System.out.println("Multiplier : " + current.getMultiplier() + "x");
            }
        }
        if (currentPath == paths.size() && !gameOver) {
            System.out.println("Congratulations! You crossed all paths!");
        }
    }
    public void cashOut() {
        if (gameOver) {
            System.out.println("Cannot Cash Out!");
            return;
        }
        if (currentPath == 0) {
            System.out.println("Cannot withdraw");
            return;
        }
        Path current = paths.get(currentPath - 1);
        double won = player.getBalance() * current.getMultiplier();
        System.out.println("You won " + won);
    }
    public ArrayList<Path> getPaths() {
        return paths;
    }
    public boolean isGameOver() {
        return gameOver;
    }
}