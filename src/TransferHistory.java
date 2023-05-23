import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class TransferHistory {
    public static HashMap<String, ArrayList<ArrayList<String>>> AllTransfers = new HashMap<>();
    public static ArrayList<ArrayList<String>> Transfers = new ArrayList<>();
    public static ArrayList<ArrayList<String>> Temp = new ArrayList<>();

    public static void transfer_save(String AccountNumber, Double Deposit, String TransactionType) {
        ArrayList<String> Transfer = new ArrayList<>();
        boolean Found = false;
        Transfers.clear();
        for (Map.Entry<String, ArrayList<ArrayList<String>>> set : AllTransfers.entrySet()) {
            if (Objects.equals(set.getKey(), AccountNumber)) {
                Temp = new ArrayList<>(set.getValue());
                Found = true;
            }
        }
        if (!Found) {
            Temp = new ArrayList<>();
        }
        Transfer.add(TransactionType);
        Transfer.add(Deposit.toString());
        Transfers.add(Transfer);
        Temp.addAll(Transfers);
        AllTransfers.put(AccountNumber, Temp);
    }

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