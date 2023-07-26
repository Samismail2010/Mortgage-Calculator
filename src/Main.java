import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte period = (byte) readNumber("Period (Years): ", 1, 30);

        printMortgage(principal, annualInterest, period);
        printPaymentSchedule(principal, annualInterest, period);
    }

    private static void printMortgage(int principal, float annualInterest, byte period) {
        double mortgage = calculateMortgage(principal, annualInterest, period);
        String result = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("MORTGAGE\n————————\nMonthly Payments: " + result);
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte period) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE\n—————————————————\n");
        for (short month = 1; month <= period * MONTHS_IN_YEAR; month++){
           double balance = calculateBalance(principal, annualInterest, period, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }
    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte period,
            short numberOfPaymentsMade) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(period * MONTHS_IN_YEAR);

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments)-1);

        return balance;
    }
    public static double calculateMortgage
            (int principal,
             float annualInterest,
             byte period) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(period * MONTHS_IN_YEAR);

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;

    }

}