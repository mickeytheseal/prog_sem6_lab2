package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String owner1 = "Петров Василий Николаевич";
        String owner2 = "Иванова Анна Петровна";
        Account acc1 = new Account(owner1, 4,AccType.RUB);
        acc1.replenish(80);
        Account acc2 = new Account(owner1, 6,AccType.USD);
        acc2.replenish(20);
        Account acc3 = new Account(owner2, 6,AccType.EUR);
        acc3.replenish(30);

        Bank bank = new Bank();
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);

        System.out.println("""
                Commands list:
                infoByIndex/infoByNumber [index/account number]
                withdraw/replenish [account number] [amount]
                combine [main_account] [add_account]
                getClientData [client]
                getBankData, exit""");

        Scanner in = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            String[] input = in.nextLine().split(" ");
            String choice = input[0];
            int id;
            int add_account;
            int amount;
            switch (choice) {
                case "infoByIndex" -> {
                    id = Integer.parseInt(input[1]);
                    System.out.println(bank.findByIndex(id).getInfo());
                }
                case "infoByNumber" -> {
                    id = Integer.parseInt(input[1]);
                    System.out.println(bank.findByNumber(id).getInfo());
                }
                case "withdraw" -> {
                    id = Integer.parseInt(input[1]);
                    amount = Integer.parseInt(input[2]);
                    bank.findByNumber(id).withdraw(amount);
                }
                case "replenish" -> {
                    id = Integer.parseInt(input[1]);
                    amount = Integer.parseInt(input[2]);
                    bank.findByNumber(id).replenish(amount);
                }
                case "combine" -> {
                    id = Integer.parseInt(input[1]);
                    add_account = Integer.parseInt(input[2]);
                    bank.addAccount(Account.combine(bank.findByNumber(id), bank.findByNumber(add_account)));
                    bank.deleteAccount(bank.findByNumber(id));
                    bank.deleteAccount(bank.findByNumber(add_account));
                }
                case "getClientData" -> {
                    String client = input[1] + " " + input[2] + " " + input[3];
                    System.out.println(bank.getClientData(client));
                }
                case "getBankData" -> System.out.println(bank.getBankData());
                case "exit" -> flag = false;
                default -> System.out.println("");
            }
        }
    }
}
