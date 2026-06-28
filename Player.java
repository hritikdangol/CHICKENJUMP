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
   public void placeBet(double betAmount) throws InvalidBetexception,InsufficientBalanceException {
    if (betAmount <= 0) {
        throw new InvalidBetexception("Bet amount must be greater than 0.");
    }
    if (betAmount > balance) {
        throw new InvalidBetexception("Insufficient balance.");
    }
    currentBet = betAmount;
    balance -= betAmount;
    System.out.println("Bet placed: " + betAmount);
}
}