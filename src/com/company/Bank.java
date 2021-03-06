package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Bank {
    private ArrayList<Account> data = new ArrayList<Account>();

    //Добавление и удаление аккаунта
    public void addAccount(Account account) {
        data.add(account);
    }
    public void deleteAccount(Account account){
        data.remove(account);
    }

    //Поиск по индексу
    public Account findByIndex(int index) {
        return data.get(index);
    }

    //Поиск по номеру счета
    public Account findByNumber(int number) {
        for (Account acc : data) {
            if (acc.getNumber() == number) {
                return acc;
            }
        }
        throw new IllegalArgumentException("No account with specified number.");
    }

    //Сбор информации по всем аккаунтам клиента
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
                acclist.append(account.getNumber());
                acclist.append(" ");
                switch (account.getType()){
                    case RUB -> rubles += account.getMoney();
                    case USD -> dollars += account.getMoney();
                    case EUR -> euros += account.getMoney();
                }
                if (account.getExpiration_date().after(last_account_expires)){ last_account_expires = account.getExpiration_date(); }
            }
        }
        return String.format("""
                        Client %s has %d accounts
                        Accounts list: [ %s]
                        Financial summary: %.2f₽ %.2f$ %.2f€
                        Last account expiration date: %s""",
                client,accounts_amount,acclist.toString(),rubles,dollars,euros,last_account_expires.toString());
    }

    //Номера аккаунтов в банке
    public String getBankData(){
        StringBuilder output = new StringBuilder("Accounts: [ ");
        for (Account account: data) {
            output.append(account.getNumber());
            output.append(" ");
        }
        output.append("]");
        return output.toString();
    }
}
