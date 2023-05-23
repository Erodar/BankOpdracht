public class BankAccount {
    private final String AccountNumber;
    public Double Balance;

    public BankAccount(String Account_Number, Double balance){
        this.AccountNumber = Account_Number;
        this.Balance = balance;
    }
//    public String getAccountNumber() {
//        return AccountNumber;
//    }
    public Double getBalance() {
        return Balance;
    }

    public void withdraw (Double Withdraw_Amount) {
        if (this.Balance < Withdraw_Amount) {
            throw new RuntimeException("You don't have enough money in the account.");
        }
        this.Balance -= Withdraw_Amount;
    }

    public void deposit (Double Deposit_Amount) {
        if (Deposit_Amount < 0) {
            throw new RuntimeException("You can't deposit a negative amount!");
        }
        this.Balance += Deposit_Amount;
    }
    @Override
    public String toString() {
        return "BankAccount{" +
                "AccountNumber='" + AccountNumber + '\'' +
                ", Balance=" + Balance +
                '}';
    }
}

