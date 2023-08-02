package com.codewithsam;

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;
    private int principal;
    private float annualInterest;
    private byte period;

    public MortgageCalculator(int principal, float annualInterest, byte period) {
        //constructor;

        this.principal = principal;
        this.annualInterest = annualInterest;
        this.period = period;
    }

    public double calculateBalance(short numberOfPaymentsMade) {
        //calculateBalance Method;
        float monthlyInterest = getMonthlyInterest();
        short numberOfPayments = getNumberOfPayments();

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments)-1);

        return balance;
    }


    public double calculateMortgage() {

        float monthlyInterest = getMonthlyInterest();
        short numberOfPayments = getNumberOfPayments();

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;

    }

    public double [] getRemainingBalances(){
        var balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= balances.length; month++)
            balances[month -1] = calculateBalance(month);

        return balances;
    }

    //All getters below
    private short getNumberOfPayments() {
        return (short) (period * MONTHS_IN_YEAR);
    }
    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }
}
