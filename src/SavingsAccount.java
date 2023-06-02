public class SavingsAccount extends BankAccount {
    private double InterestRate = 1.25;
    private double WithdrawalLimit = 5000;
    public SavingsAccount(String Account_Number, Double balance, Person Owner) {
        super(Account_Number, balance, Owner, AccountType.SAVINGSACCOUNT);
    }

    public void setInterestRate(double interestRate) {
        this.InterestRate = interestRate;
    }

    public double getInterestRate() {
        return InterestRate;
    }

    public void addInterest() {
        this.Balance = this.Balance * ((100 + InterestRate)/100);
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        this.WithdrawalLimit = withdrawalLimit;
    }

    public void withdraw(Double Withdraw_Amount) {
        if (this.Balance < Withdraw_Amount) {
            System.out.println("Your balance is too low. Transaction cancelled.");
        } else if (Withdraw_Amount > this.WithdrawalLimit) {
            System.out.println("Exceeding withdrawal limit!");
        } else {
            Balance -= Withdraw_Amount;
        }
    }

}
