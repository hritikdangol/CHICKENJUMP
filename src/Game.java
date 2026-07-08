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
        for (int i = 1; i <= 15; i++) {   //total path 15 wata xa ((limited pathss))
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
        chicken.jump();   //path agadi badhxaa jump garda
        currentPath++;
        if (currentPath <= paths.size()-1) {    //15-1== trueeeee 14 samma janxa
            Path current = paths.get(currentPath);  
            currentPath++;
            if (current.hasFire()) {
                System.out.println("Game Over!");
                gameOver = true;
            } else {
     currentPath++;
            }
        }
        if (currentPath == paths.size() && !gameOver) {
            Path current = paths.get(currentPath - 1);
            System.out.println("Congratulations! You won! \nAmount won:" + (player.getBalance() * current.getMultiplier()));
        }
    }
    public void cashOut() {
        if (gameOver) {
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
       public Chicken getChicken() {
    return chicken;
}

    public boolean isGameOver() {
        return gameOver;
    }
}