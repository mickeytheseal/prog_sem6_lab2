package com.company;

public enum AccType {
    RUB ("₽"),
    USD ("$"),
    EUR ("€");

    private final String sign;

    AccType(String sign) {
        this.sign = sign;
    }

    //Получение символа валюты
    public String getSign() {
        return sign;
    }

    //Получение коэффицента для перевода из одной валюты в другую
    public double getRate(AccType toCast){
        double RUBtoUSD = 73.72;
        double RUBtoEUR = 88.98;
        double USDtoEUR = 0.83;
        switch(this){
            case RUB:
                if (toCast.equals(AccType.RUB)){ return 1; }
                else if (toCast.equals(AccType.USD)){ return RUBtoUSD; }
                else{ return RUBtoEUR; }
            case USD:
                if (toCast.equals(AccType.USD)){ return 1; }
                else if (toCast.equals(AccType.EUR)){ return USDtoEUR; }
                else{ return 1/ RUBtoUSD; }
            case EUR:
                if (toCast.equals(AccType.EUR)){ return 1; }
                else if (toCast.equals(AccType.RUB)){ return 1/ RUBtoEUR; }
                else{ return 1/ USDtoEUR; }
            default:
                throw new IllegalArgumentException("Wrong currency!");
        }
    }
}
