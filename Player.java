class Player {
    private double balance;

    Player(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void placeBet(double betAmount) {
        if (betAmount > 0 && betAmount <= balance) {
            balance -= betAmount;
            System.out.println("Bet placed: " + betAmount);
        } else {
            System.out.println("Invalid bet!");
        }
    }
}