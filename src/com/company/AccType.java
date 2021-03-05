package com.company;

public enum AccType {
    RUB ("₽"),
    USD ("$"),
    EUR ("€");

    private String sign;

    private final double RUBtoUSD = 73.72;
    private final double RUBtoEUR = 88.98;
    private final double USDtoEUR = 0.83;

    AccType(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public double getRate(AccType toCast){
        switch(this){
            case RUB:
                if (toCast.equals(AccType.RUB)){ return 1; }
                else if (toCast.equals(AccType.USD)){ return RUBtoUSD; }
                else{ return RUBtoEUR; }
            case USD:
                if (toCast.equals(AccType.USD)){ return 1; }
                else if (toCast.equals(AccType.EUR)){ return USDtoEUR; }
                else{ return 1/RUBtoUSD; }
            case EUR:
                if (toCast.equals(AccType.EUR)){ return 1; }
                else if (toCast.equals(AccType.RUB)){ return 1/RUBtoEUR; }
                else{ return 1/USDtoEUR; }
            default:
                throw new IllegalArgumentException("Wrong currency!");
        }
    }
}
