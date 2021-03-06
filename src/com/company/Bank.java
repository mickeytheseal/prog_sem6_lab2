package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Bank {
    private ArrayList<Account> data = new ArrayList<Account>();

    public void addAccount(Account account) {
        data.add(account);
    }
    public void deleteAccount(Account account){
        data.remove(account);
    }

    public Account findByIndex(int index) {
        return data.get(index);
    }

    public Account findByNumber(int number) {
        for (Account acc : data) {
            if (acc.getNumber() == number) {
                return acc;
            }
        }
        throw new IllegalArgumentException("No account with specified number.");
    }

    public String getClientData(String client){
        int accounts_amount = 0;
        StringBuilder acclist = new StringBuilder("");
        double rubles = 0;
        double dollars = 0;
        double euros = 0;
        Date last_account_expires = new Date(0);

        for (Account account: data) {
            if (account.getOwner().equals(client)){
                accounts_amount++;
                acclist.append(account.getNumber() + " ");
                switch (account.getType()){
                    case RUB -> rubles += account.getMoney();
                    case USD -> dollars += account.getMoney();
                    case EUR -> euros += account.getMoney();
                }
                if (account.getExpiration_date().after(last_account_expires)){ last_account_expires = account.getExpiration_date(); }
            }
        }
        return String.format("Client %s has %d accounts\nAccounts list: [ " + acclist.toString() + " ]\n" +
                "Financial summary: %.2f₽ %.2f$ %.2f€\n" +
                "Last account expiration date: %s",
                client,accounts_amount,rubles,dollars,euros,last_account_expires.toString());
    }

    public String getData(){
        StringBuilder output = new StringBuilder("Accounts: [ ");
        for (Account account: data) {
            output.append(account.getNumber());
            output.append(" ");
        }
        output.append("]");
        return output.toString();
    }
}
