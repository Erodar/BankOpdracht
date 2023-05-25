import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Double.NaN;

public class Bank {
    /* create a hashmap that stores the account number
     * and the attached BankAccount object
     */
    public HashMap<String, BankAccount> map = new HashMap<>();

    /* Define value of currencies in a hashmap
     * for later use in the currency exchange method
     */
    public static HashMap<String, Double> currency;

    public static HashMap<Person, BankAccount> AllOwners = new HashMap<>();

    static {
        currency = new HashMap<>();
        currency.put("Dollar", 1.08);
        currency.put("Pound", 0.87);
        currency.put("Yen", 149.27);
        currency.put("Won", 1422.60);
    }

    /* create a new account as a BankAccount object
     * and send the initial deposit to transfer history
     */
    public void Create_Account(String Account_Number, Double Initial_Deposit, Person Owner) {
        BankAccount Account = new BankAccount(Account_Number, Initial_Deposit, Owner);
        map.put(Account_Number, Account);
        BankAccount.transfer_save(Account_Number, Initial_Deposit, "Initial Deposit");
        AllOwners.put(Owner, Account);
    }

    /* returns the BankAccount object attached to an account number.
     * If it doesn't exist, crashes.
     */
    public BankAccount getAccount(String Account_Number) {
        if (map.get(Account_Number) == null) {
            return new BankAccount(null, NaN, null);
        } else {
            return map.get(Account_Number);
        }
    }

    /* takes an account number and a deposit amount.
     * adds the amount to the balance associated to the account number.
     */
    public void deposit(String Account_Number, Double Deposit) {
        this.getAccount(Account_Number).deposit(Deposit);
        BankAccount.transfer_save(Account_Number, Deposit, "Deposit");
    }


    //same as previous, except now it's the withdrawal method.
    public void withdraw(String Account_Number, Double Withdrawal) {
        this.getAccount(Account_Number).withdraw(Withdrawal);
        BankAccount.transfer_save(Account_Number, Withdrawal, "Withdrawal");
    }

    //for transfers. basically a combination of the last two methods in one.
    public void transfer(String Withdrawal_Account, String Deposit_Account, Double Transfer_Amount) {
        this.getAccount(Withdrawal_Account).withdraw(Transfer_Amount);
        BankAccount.transfer_save(Withdrawal_Account, Transfer_Amount, "Withdrawal (Transfer)");
        this.getAccount(Deposit_Account).deposit(Transfer_Amount);
        BankAccount.transfer_save(Deposit_Account, Transfer_Amount, "Deposit (Transfer)");
    }

    //recalculates the amount of money in the account based on the exchange rates defined at the top.
    public Double currency_exchange(String Account_Number, String Currency_Type) {
        return (this.getAccount(Account_Number).getBalance() * currency.get(Currency_Type));
    }

    public static Double Find_Balance(Person person) {
        for (Map.Entry<Person, BankAccount> set : AllOwners.entrySet()) {
            if (Objects.equals(set.getKey(), person)) {
                return set.getValue().getBalance();
            }
        }
        return NaN;
    }

    public static String Find_Richest_Prick() {
        int BigBalance = 0;
        String Richest_Person = "No one has an account!";
        for (Map.Entry<Person, BankAccount> set : AllOwners.entrySet()) {
            if (set.getValue().Balance > BigBalance) {
                Richest_Person = set.getValue().AccountOwner.Name;
            }
        }
        return Richest_Person;
    }

    public static String Bank_Dating_Service() {
        String lovebirds = "";
        int lovers = 0;
        for (Map.Entry<Person, BankAccount> set : AllOwners.entrySet()) {
            if (Objects.equals(set.getValue().AccountOwner.RelationshipStatus, "Single")) {
                lovebirds = lovebirds.concat(set.getValue().AccountOwner.Name);
                lovers++;
                if (lovers == 2) {
                    return lovebirds;
                }
                lovebirds = lovebirds.concat(" & ");
            }
        } return lovebirds;
    }
}


