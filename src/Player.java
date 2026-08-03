package src;

public class Player {
    private double balance = 0;
    private Double currentBet = null;
    // double doesnt holds nullvalue so we use Double class which can hold null
    // value
    private boolean startGame = false;

    public Player() {
        this(0);
    }

    public Player(double balance) {
        this.balance = balance;
        this.currentBet = null;
    }

    public double getBalance() {
        return balance;
    }

    public Double getCurrentBet() {
        return currentBet;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void resetBet() {
        currentBet = null;
        startGame = false;
    }

    public void placeBet(double betAmount)
            throws Invalidbetexception, InsufficientbalanceException, InvalidstartGameException {
        if (betAmount < 10) {
            throw new Invalidbetexception("Minimum Bet amount is 10.");
        }
        if (betAmount > balance) {
            throw new InsufficientbalanceException("Insufficient balance.");
        }

        currentBet = betAmount;
        balance -= betAmount;
        System.out.println("Bet placed: " + betAmount);
    }

    public void startGame() throws InvalidstartGameException {
        if (currentBet == null || currentBet <= 0) {
            throw new InvalidstartGameException("Place a bet before starting the game.");
        } else
            startGame = true;
    }
}