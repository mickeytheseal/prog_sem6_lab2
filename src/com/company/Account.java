package com.company;
import java.util.Calendar;
import java.util.Date;

public class Account {
    private int account_num;
    private double money_amount = 0;
    private final String owner;
    private final Date creation_date;
    private int duration;
    private Date expiration_date;
    private AccType type;
    private boolean isExpired;

    //Конструктор
    public Account(String owner, int duration, AccType type){
        //this.account_num = account_num;
        this.owner = owner;
        this.duration = duration;
        this.type = type;
        creation_date = new Date();
        this.account_num = this.hashCode();

        Calendar c = Calendar.getInstance();
        c.setTime(creation_date);
        c.add(Calendar.YEAR,duration);
        expiration_date = c.getTime();

        System.out.println("Created account " + account_num);
    }


    public Account(String owner, int duration,double money_amount, AccType type){
        this.account_num = account_num;
        this.owner = owner;
        this.money_amount = money_amount;
        this.duration = duration;
        this.type = type;
        creation_date = new Date();
        this.account_num = this.hashCode();

        Calendar c = Calendar.getInstance();
        c.setTime(creation_date);
        c.add(Calendar.YEAR,duration);
        expiration_date = c.getTime();

        System.out.println("Created account " + account_num);
    }

    //Пополнение
    public void replenish(int amount){
        money_amount += amount;
    }

    //Снятие
    public void withdraw(int amount){
        if (amount > money_amount){
            System.out.println("Not enough money.");
        }
        else{
            money_amount -= amount;
            System.out.println("Successful withdraw. " + amount + type.getSign());
        }
    }

    //Java не поддерживает перегрузку операторов, поэтому для объединения счетов реализован метод combine()
    public static Account combine(Account main_account, Account add_account){
        double rate = main_account.type.getRate(add_account.type);
        return new Account(main_account.owner,main_account.duration,
                main_account.money_amount+add_account.money_amount*rate,main_account.type);
    }

    public int getNumber(){ return account_num; }
    public String getOwner(){ return owner; }
    public AccType getType(){ return type; }

    //Вывод информации
    public String getInfo(){
        return String.format("Account %d\nOwner: %s\n" +
                "Created: %s\nDuration: %d\n" +
                "Expiration date: %s\nMoney amount: %.2f%s",
                account_num,owner,creation_date.toString(),duration,
                expiration_date,money_amount, type.getSign());
    }

}
