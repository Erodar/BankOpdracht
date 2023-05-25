import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class TransferHistory {
    //AllTransfers is a hashmap containing the account number and then a list of transfers.
    //Each transfer is also stored as a list, so Transfers is a list of lists.
    public static HashMap<String, ArrayList<ArrayList<String>>> AllTransfers = new HashMap<>();
    public static ArrayList<ArrayList<String>> Transfers = new ArrayList<>();

    //first, creates Transfer as a list - this clears the existing object.
    //then uses a boolean as a flag, so we can see if an account already has an existing history.
    //if it does, assigns Transfers with the existing history. if it does not, clears Transfers to prevent contamination.
    //finally the list is added to the list which is added to the hashmap.
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

    //simply returns the list of lists attached to an account number from the hashmap.
    //otherwise, returns an empty list of lists.
    public static ArrayList<ArrayList<String>> display_history(String AccountNumber) {
        ArrayList<ArrayList<String>> Account_History = new ArrayList<>();
        for (Map.Entry<String, ArrayList<ArrayList<String>>> set : AllTransfers.entrySet()) {
            {
                if (Objects.equals(set.getKey(), AccountNumber)) {
                    return set.getValue();
                }
            }
        }
        return Account_History;
    }
}