import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player player = new Player(1000);
        Chicken chicken = new Chicken(100, 300, 50, 50);
        Game game = new Game(player, chicken);
        game.startGame();
        System.out.print("Enter bet amount: ");
        double betAmount = sc.nextDouble();
        player.placeBet(betAmount);
        game.jump();
        game.jump();
        game.jump();
        game.cashOut();
        sc.close();
    }
}