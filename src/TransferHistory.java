import java.util.ArrayList;


public class TransferHistory {
    public static ArrayList<ArrayList<String>> Transfers = new ArrayList<>();

    public static void transfer_positive(String AccountNumber, Double Deposit) {
        ArrayList<String> Transfer = new ArrayList<>();
        Transfer.add(AccountNumber);
        Transfer.add("Deposit");
        Transfer.add(Deposit.toString());
        Transfers.add(Transfer);
    }

    public static void transfer_negative(String AccountNumber, Double Deposit) {
        ArrayList<String> Transfer = new ArrayList<>();
        Transfer.add(AccountNumber);
        Transfer.add("Withdrawal");
        Transfer.add(Deposit.toString());
        Transfers.add(Transfer);
    }

    public static ArrayList display_history(String AccountNumber) {
        ArrayList<ArrayList<String>> Account_History = new ArrayList<>();
        for (ArrayList<String> transfer : Transfers) {
            if (transfer.contains(AccountNumber)) {
                Account_History.add(transfer);
            }
        }
        return Account_History;
    }
}