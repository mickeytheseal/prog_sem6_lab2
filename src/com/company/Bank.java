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
        int rubles = 0;
        int dollars = 0;
        int euros = 0;
        Date last_account_expires;

        for (Account account: data) {
            if (account.getOwner().equals(client)){
                accounts_amount++;
            }
        }
        return null;
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
