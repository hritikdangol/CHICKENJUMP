package src;
public class Player {
    private double balance;
    private double currentBet;
    public Player(double balance) {
        this.balance = balance;
        this.currentBet = 0;
    }
    public double getBalance() {
        return balance;
    }
    public double getCurrentBet() {
        return currentBet;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
   public void placeBet(double betAmount) throws Invalidbetexception,InsufficientbalanceException {
    if (betAmount <= 10) {
        throw new Invalidbetexception("Minimum Bet amount is 10.");
    }
    if (betAmount > balance) {
        throw new Invalidbetexception("Insufficient balance.");
    }
    currentBet = betAmount;
    balance -= betAmount;
    System.out.println("Bet placed: " + betAmount);
}
}