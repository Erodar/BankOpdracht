public class BankAccount {
    //defining the two main attributes. AccountNumber is private and final since it never needs to be changed.
    //the balance can be edited in other classes too, though, since there's no reason to make it private.
    private final String AccountNumber;
    public Double Balance;

    //BankAccounts only have two attributes, the balance in the account and the account number.
    public BankAccount(String Account_Number, Double balance){
        this.AccountNumber = Account_Number;
        this.Balance = balance;
    }
    //commented out because not used; just returns the accountnumber of an object.
//    public String getAccountNumber() {
//        return AccountNumber;
//    }
    //returns the balance of an object.
    public Double getBalance() {
        return Balance;
    }

    //withdraws money from an account. if there isn't enough money it errors.
    public void withdraw (Double Withdraw_Amount) {
        if (this.Balance < Withdraw_Amount) {
            throw new RuntimeException("You don't have enough money in the account.");
        }
        this.Balance -= Withdraw_Amount;
    }

    //deposits money into an account. If the amount is negative it errors.
    public void deposit (Double Deposit_Amount) {
        if (Deposit_Amount < 0) {
            throw new RuntimeException("You can't deposit a negative amount!");
        }
        this.Balance += Deposit_Amount;
    }

    //i just used the constructor for a simple function that returns a bankaccount object as a string.
    @Override
    public String toString() {
        return "BankAccount{" +
                "AccountNumber='" + AccountNumber + '\'' +
                ", Balance=" + Balance +
                '}';
    }
}

