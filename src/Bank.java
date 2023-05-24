import java.util.HashMap;

public class Bank {
    //create a hashmap that stores the account number and the attached BankAccount object
    public HashMap<String, BankAccount> map = new HashMap<>();

    // Define value of currencies in a hashmap for later use in the currency exchange method
    public static HashMap<String, Double> currency;
    static {
        currency = new HashMap<>();
        currency.put("Dollar", 1.08);
        currency.put("Pound", 0.87);
        currency.put("Yen", 149.27);
        currency.put("Won", 1422.60);
    }

    //create a new account as a BankAccount object, and send the initial deposit to transfer history
    public void Create_Account(String Account_Number, Double Initial_Deposit) {
        BankAccount Account = new BankAccount(Account_Number, Initial_Deposit);
        map.put(Account_Number, Account);
        TransferHistory.transfer_save(Account_Number, Initial_Deposit, "Initial Deposit");
    }

    //returns the BankAccount object attached to an account number. If it doesn't exist, crashes.
    public BankAccount getAccount(String Account_Number) {
        try {
            return map.get(Account_Number);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    //uses the getaccount function to access the deposit method of the BankAccount class.
    public void deposit(String Account_Number, Double Deposit) {
        this.getAccount(Account_Number).deposit(Deposit);
        TransferHistory.transfer_save(Account_Number, Deposit, "Deposit");
    }


    //same as previous, except now it's the withdraw method.
    public void withdraw(String Account_Number, Double Withdrawal) {
        this.getAccount(Account_Number).withdraw(Withdrawal);
        TransferHistory.transfer_save(Account_Number, Withdrawal, "Withdrawal");
    }
    //for transfers. basically a combination of the last two methods in one.
    public void transfer(String Withdrawal_Account, String Deposit_Account, Double Transfer_Amount) {
        this.getAccount(Withdrawal_Account).withdraw(Transfer_Amount);
        TransferHistory.transfer_save(Withdrawal_Account, Transfer_Amount, "Withdrawal (Transfer)");
        this.getAccount(Deposit_Account).deposit(Transfer_Amount);
        TransferHistory.transfer_save(Deposit_Account, Transfer_Amount, "Deposit (Transfer)");
    }

    //recalculates the amount of money in the account based on the exchange rates defined at the top.
    public Double currency_exchange(String Account_Number, String Currency_Type) {
        return (this.getAccount(Account_Number).getBalance() * currency.get(Currency_Type));
    }
}


