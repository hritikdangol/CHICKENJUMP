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
    public void placeBet(double betAmount) {
        if (betAmount > 0 && betAmount <= balance) {
            currentBet = betAmount;
            balance -= betAmount;
            System.out.println("Bet placed: " + betAmount);
            System.out.println("Remaining Balance: " + balance);
        } else {
            System.out.println("Invalid Bet!");
        }
    }
}