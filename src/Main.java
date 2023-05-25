public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();

        // Nieuwe rekeningen aanmaken

        bank.Create_Account("NL01ABCD1234567890", 1000.0);

        bank.Create_Account("NL02EFGH1234567890", 500.0);

//         Geld storten

        bank.deposit("NL01ABCD1234567890", 250.0);
        bank.deposit("NL01ABCD1234567890", 10.0);
        bank.deposit("NL01ABCD1234567890", 5.0);
        bank.withdraw("NL01ABCD1234567890", 2.5);
        bank.withdraw("NL01ABCD1234567890", 5.5);

//         Geld opnemen

        bank.withdraw("NL02EFGH1234567890", 200.0);
        bank.deposit("NL01ABCD1234567890", 250.0);

//         Saldo controleren

        System.out.println(bank.getAccount("NL01ABCD1234567890").getBalance());

        bank.transfer("NL02EFGH1234567890", "NL01ABCD1234567890", 250.0);

        System.out.println(bank.currency_exchange("NL01ABCD1234567890", "Won"));

        System.out.println(BankAccount.display_history("NL02EFGH1234567890"));
        System.out.println(BankAccount.display_history("NL01ABCD1234567890"));
    }

}