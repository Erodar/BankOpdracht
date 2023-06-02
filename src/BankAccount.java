import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Double.NaN;

public class BankAccount {
    private final String AccountNumber;
    public Double Balance;
    public Person AccountOwner;
    public AccountType Type;

    /*AllTransfers is a hashmap containing the account number and then a list of transfers.
     *Each individual transfer is also stored as a list, so Transfers is a list of lists.
     */
    public static HashMap<String, ArrayList<ArrayList<String>>> AllTransfers = new HashMap<>();
    public static ArrayList<ArrayList<String>> Transfers = new ArrayList<>();

    public BankAccount(String Account_Number, Double balance, Person Owner, AccountType accountType) {
        this.AccountNumber = Account_Number;
        this.Balance = balance;
        this.AccountOwner = Owner;
        this.Type = accountType;
    }


    public Double getBalance() {
        try {
            return Math.floor(this.Balance * 100) / 100;
        } catch (RuntimeException e) {
            return (NaN);
        }
    }

    //withdraws money from an account. if there isn't enough money it errors.
    public void withdraw(Double Withdraw_Amount) {
        if (Balance < Withdraw_Amount) {
            System.out.println("Your balance is too low. Transaction cancelled.");
        } else {
            Balance -= Withdraw_Amount;
        }
    }

    //deposits money into an account. If the amount is negative it errors.
    public void deposit(Double Deposit_Amount) {
        if (Deposit_Amount < 0) {
            System.out.println("You can't deposit a negative amount. Transaction cancelled.");
        } else {
            Balance += Deposit_Amount;
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "AccountNumber='" + AccountNumber + '\'' +
                ", Balance=" + Balance +
                '}';
    }


    /* first, creates Transfer as a list - this clears the existing object.
     * then uses a boolean as a flag, so we can see if an account already has an existing history.
     * if it does, assigns Transfers with the existing history. if it does not, clears Transfers to prevent contamination.
     * finally the list is added to the list which is added to the hashmap.
     */
    public static void transfer_save(String AccountNumber, Double Deposit, String TransactionType) {
        ArrayList<String> Transfer = new ArrayList<>();
        boolean Found = false;
        for (Map.Entry<String, ArrayList<ArrayList<String>>> set : AllTransfers.entrySet()) {
            if (Objects.equals(set.getKey(), AccountNumber)) {
                Transfers = new ArrayList<>(set.getValue());
                Found = true;
            }
        }
        if (!Found) {
            Transfers.clear();
        }
        Transfer.add(TransactionType);
        Transfer.add(Deposit.toString());
        Transfers.add(Transfer);
        AllTransfers.put(AccountNumber, Transfers);
    }

    /* simply returns the Transfers-list attached to an account number from the hashmap.
     * if none are found, returns an empty list of lists.
     */
    public static ArrayList<ArrayList<String>> display_history(String AccountNumber) {
        ArrayList<ArrayList<String>> Empty_List = new ArrayList<>();
        for (Map.Entry<String, ArrayList<ArrayList<String>>> set : AllTransfers.entrySet()) {
            {
                if (Objects.equals(set.getKey(), AccountNumber)) {
                    return set.getValue();
                }
            }
        }
        return Empty_List;
    }
}

