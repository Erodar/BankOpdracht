import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Double.NaN;

public class Bank {
    /* create a hashmap that stores the account number
     * and the attached BankAccount object
     */
    public static HashMap<String, BankAccount> AccountNumbsHashMap = new HashMap<>();

    /* Define value of currencies in a hashmap
     * for later use in the currency exchange method
     */
    public static HashMap<String, Double> currency;

    public static HashMap<Person, ArrayList<BankAccount>> AllOwners = new HashMap<>();

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
    public void Create_Account(String Account_Number, Double Initial_Deposit, Person Owner, AccountType accountType) {
        if (accountType == AccountType.BANKACCOUNT) {
            BankAccount Account = new BankAccount(Account_Number, Initial_Deposit, Owner, AccountType.BANKACCOUNT);
            AccountNumbsHashMap.put(Account_Number, Account);
            boolean found = false;
            for (Map.Entry<Person, ArrayList<BankAccount>> set : AllOwners.entrySet()) {
                if (Objects.equals(set.getKey(), Owner)) {
                    set.getValue().add(Account);
                    found = true;
                }
            }
            if (!found) {
                ArrayList<BankAccount> NewList = new ArrayList<>();
                NewList.add(Account);
                AllOwners.put(Owner, NewList);
            }
            BankAccount.transfer_save(Account_Number, Initial_Deposit, "Initial Deposit");
        } else if (accountType == AccountType.SAVINGSACCOUNT) {
            SavingsAccount Account = new SavingsAccount(Account_Number, Initial_Deposit, Owner);
            AccountNumbsHashMap.put(Account_Number, Account);
            boolean found = false;
            for (Map.Entry<Person, ArrayList<BankAccount>> set : AllOwners.entrySet()) {
                if (Objects.equals(set.getKey(), Owner)) {
                    set.getValue().add(Account);
                    found = true;
                }
            }
            if (!found) {
                ArrayList<BankAccount> NewList = new ArrayList<>();
                NewList.add(Account);
                AllOwners.put(Owner, NewList);
            }
            BankAccount.transfer_save(Account_Number, Initial_Deposit, "Initial Deposit");
        }
    }

    /* returns the BankAccount object attached to an account number.
     * If it doesn't exist, crashes.
     */
    public static BankAccount getAccount(String Account_Number) {
        if (AccountNumbsHashMap.get(Account_Number) == null) {
            return new BankAccount(null, NaN, null, null);
        } else {
            return AccountNumbsHashMap.get(Account_Number);
        }
    }

    /* takes an account number and a deposit amount.
     * adds the amount to the balance associated to the account number.
     */
    public void deposit(String Account_Number, Double Deposit) {
        getAccount(Account_Number).deposit(Deposit);
        BankAccount.transfer_save(Account_Number, Deposit, "Deposit");
    }


    //same as previous, except now it's the withdrawal method.
    public void withdraw(String Account_Number, Double Withdrawal) {
        getAccount(Account_Number).withdraw(Withdrawal);
        BankAccount.transfer_save(Account_Number, Withdrawal, "Withdrawal");
    }

    //for transfers. basically a combination of the last two methods in one.
    public void transfer(String Withdrawal_Account, String Deposit_Account, Double Transfer_Amount) {
        getAccount(Withdrawal_Account).withdraw(Transfer_Amount);
        BankAccount.transfer_save(Withdrawal_Account, Transfer_Amount, "Withdrawal (Transfer)");
        getAccount(Deposit_Account).deposit(Transfer_Amount);
        BankAccount.transfer_save(Deposit_Account, Transfer_Amount, "Deposit (Transfer)");
    }

    //recalculates the amount of money in the account based on the exchange rates defined at the top.
    public static Double currency_exchange(String Account_Number, String Currency_Type) {
        return (getAccount(Account_Number).getBalance() * currency.get(Currency_Type));
    }

    public static ArrayList<BankAccount> Find_Balance(Person person) {
        ArrayList<BankAccount> AccountList = new ArrayList<>();
        for (Map.Entry<Person, ArrayList<BankAccount>> set : AllOwners.entrySet()) {
            if (Objects.equals(set.getKey(), person)) {
                AccountList.addAll(set.getValue());
            }
        }
        return AccountList;
    }

    public static String Find_Richest_Prick() {
        double BigBalance = 0;
        String Richest_Person = "No one has an account!";
        for (Map.Entry<Person, ArrayList<BankAccount>> set : AllOwners.entrySet()) {
            for (BankAccount account : set.getValue()) {
                if (account.Balance > BigBalance) {
                    Richest_Person = account.AccountOwner.Name;
                    BigBalance = account.Balance;
                }
            }
        }
        return Richest_Person;
    }
}


