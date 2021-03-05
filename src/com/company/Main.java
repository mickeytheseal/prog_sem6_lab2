package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String owner1 = "Василий Николаевич Приколдесов";
        String owner2 = "Биба Боба Пажилая";
        Account acc1 = new Account(owner1, 4,AccType.RUB);
        acc1.replenish(80);
        Account acc2 = new Account(owner2, 6,AccType.USD);
        acc2.replenish(1);
        Account acc3 = Account.combine(acc2,acc1);

        Bank bank = new Bank();
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);

        System.out.println("infoByIndex/infoByNumber [index/account number]\n" +
                "withdraw/replenish [account number] [amount]\n" +
                "combine [main_account] [add_account]\n" +
                "showData, exit");
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            String input[] = in.nextLine().split(" ");
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
                case "showData" -> System.out.println(bank.getData());
                case "exit" -> flag = false;
                default -> System.out.println("");
            }
        }

    }
}
