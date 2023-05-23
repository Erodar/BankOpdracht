import java.util.HashMap;

public class Bank {
    public HashMap<String, BankAccount> map = new HashMap<>();
    public static HashMap<String, Double> currency;
    static {
        currency = new HashMap<>();
        currency.put("Dollar", 1.08);
        currency.put("Pound", 0.87);
        currency.put("Yen", 149.27);
        currency.put("Won", 1422.60);
    }


    public void Create_Account(String Account_Number, Double First_Saldo) {
        BankAccount Account = new BankAccount(Account_Number, First_Saldo);
        map.put(Account_Number, Account);
        TransferHistory.transfer_save(Account_Number, First_Saldo, "Initial Deposit");
    }

    public BankAccount getAccount(String Account_Number) {
        try {
            return map.get(Account_Number);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }


    public void deposit(String Account_Number, Double Deposit) {
        this.getAccount(Account_Number).deposit(Deposit);
        TransferHistory.transfer_save(Account_Number, Deposit, "Deposit");
    }



    public void withdraw(String Account_Number, Double Withdrawal) {
        this.getAccount(Account_Number).withdraw(Withdrawal);
        TransferHistory.transfer_save(Account_Number, Withdrawal, "Withdrawal");
    }

    public void transfer(String Withdrawal_Account, String Deposit_Account, Double Transfer_Amount) {
        this.getAccount(Withdrawal_Account).withdraw(Transfer_Amount);
        TransferHistory.transfer_save(Withdrawal_Account, Transfer_Amount, "Withdrawal (Transfer)");
        this.getAccount(Deposit_Account).deposit(Transfer_Amount);
        TransferHistory.transfer_save(Deposit_Account, Transfer_Amount, "Deposit (Transfer)");
    }

    public Double currency_exchange(String Account_Number, String Currency_Type) {
        return (this.getAccount(Account_Number).getBalance() * currency.get(Currency_Type));
    }
}


