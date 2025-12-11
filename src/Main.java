import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    static final byte number_of_months = 12;
    static final byte number_of_years = 100;


    static void main(String[] args) {

        // Mortgage Calculator

        int principalAmount = 0;
        float annualInterestRate = 0;
        byte years = 0;


        principalAmount = (int) readNumber("Principal ($1K-$1M): ", 1000, 1000000);
        annualInterestRate = (float) readNumber("Annual Interest Rate: ", 0, 30);
        years = (byte) readNumber("Period(Years): ", 0, 30);

        printMortgage(principalAmount, annualInterestRate, years);
        printPaymentSchedule(years, principalAmount, annualInterestRate);


    }

    private static void printPaymentSchedule(byte years, int principalAmount, float annualInterestRate) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        System.out.println();
        System.out.println("Payment Schedules");
        System.out.println("-----------------");
        for (short month = 1; month <= years * number_of_months; month++) {
            double balance = calculateBalance(principalAmount, annualInterestRate, years, month);
            System.out.println(currency.format(balance));
        }
    }

    private static void printMortgage(int principalAmount, float annualInterestRate, byte years) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        double mortgage = calculateMortgage(principalAmount, annualInterestRate, years);
        System.out.println();
        System.out.println("Mortgage");
        System.out.println("--------");
        System.out.println();
        System.out.println("Monthly Mortgage Payment: " + currency.format(mortgage));
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(prompt);
        double value;
        value = scanner.nextDouble();
        while (value <= min || value >= max) {
            System.out.println("Enter a number between " + min + " and " + max);
            System.out.print(prompt);
            value = scanner.nextDouble();
        }
        return value;
    }

    public static double calculateBalance(int principalAmount, float annualInterestRate, byte years, short numberOfPaymentsMade) {

        float monthlyInterest = annualInterestRate / number_of_months / number_of_years;
        float numberOfPayments = years * number_of_months;
        double balance = principalAmount * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade)) / ((Math.pow(1 + monthlyInterest, numberOfPayments) - 1));
        return balance;
    }

    public static double calculateMortgage(int principalAmount, float annualInterestRate, byte years) {

        float monthlyInterest = annualInterestRate / number_of_months / number_of_years;
        float numberOfPayments = years * number_of_months;
        double m1 = Math.pow(1 + monthlyInterest, numberOfPayments);
        double mortgage = principalAmount * (monthlyInterest * m1) / (m1 - 1);
        return mortgage;
    }


}
