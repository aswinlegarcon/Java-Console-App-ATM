package ATM;

import java.util.ArrayList;
import java.util.Scanner;

public class UserActions
{


    public static void changePin(Scanner s,User currentUser)
    {
        System.out.print("Enter the Pin to change :");
        int pin = Integer.parseInt(s.nextLine());
        currentUser.setPin(pin);
        System.out.println("Pin changed");
    }

    public static void withdrawCash(Scanner s,User currentUser )
    {
        System.out.print("Enter the amount to withdraw : ");
        long amountToWithdraw = Long.parseLong(s.nextLine());
        if (amountToWithdraw <= ATMMachine.getBalance()) {
            if (amountToWithdraw <= currentUser.getBalance()) {
                System.out.println("Withdrawl amount of Rs." + amountToWithdraw + " is successful");
                double currentBalanceInAcc = currentUser.getBalance() - amountToWithdraw;
                double currentBalanceInATM = ATMMachine.getBalance() - amountToWithdraw;
                currentUser.setBalance(currentBalanceInAcc);
                ATMMachine.setBalance(currentBalanceInATM);
                currentUser.addUserTransactionHistory("Your account is debited with Rs." + amountToWithdraw + "--- Balance :" + currentUser.getBalance());
                Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is debited with Rs." + amountToWithdraw + "--- User Balance : " + currentUser.getBalance() + "--- ATM Balance : " + ATMMachine.getBalance());
                System.out.println("Your current balance is " + currentUser.getBalance());
            } else {
                System.out.println("The amount in your account is not sufficent to withdraw");
            }
        } else {
            System.out.println("Sorryy....There is no amount in the ATM. Come back Later");

        }

    }

    public static void depositCash(Scanner s,User currentUser)
    {
        System.out.print("Enter the amount to deposit : ");
        long amountToDeposit = Long.parseLong(s.nextLine());
        double currentBalance = currentUser.getBalance() + amountToDeposit;
        double balanceInAtm = ATMMachine.getBalance() + amountToDeposit;
        currentUser.setBalance(currentBalance);
        ATMMachine.setBalance(balanceInAtm);
        currentUser.addUserTransactionHistory("Your account is credited with Rs." + amountToDeposit + "--- Balance :" + currentUser.getBalance());
        Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is credited with Rs." + amountToDeposit + "--- User Balance : " + currentUser.getBalance() + "--- ATM Balance : " + ATMMachine.getBalance());
        System.out.println("The deposit of Rs." + amountToDeposit + " is added successfully");
        System.out.println("Your current balance is " + currentUser.getBalance());
    }

    public static void viewTransactions(User currentUser)
    {
        ArrayList<String> userHistory = currentUser.getUserTransactionHistory();
        if (!userHistory.isEmpty()) {
            System.out.println("The Transactions you have made...\n");
            for (String history : userHistory) {
                System.out.println(history);
            }
        } else {
            System.out.println("There are no Transactions..");
        }
    }

}