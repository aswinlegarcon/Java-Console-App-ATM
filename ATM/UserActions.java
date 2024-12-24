package ATM;

import ATM.Notes.Notes;

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
        System.out.println("The Available notes and balance in ATM...");
        ArrayList<Notes> notes =  ATMMachine.getNotesInAtm();
        for(Notes noNotes:notes)
        {
            System.out.println(" "+noNotes.getNote()+" - "+noNotes.getCount());
        }
        System.out.println("The Total Balance in ATM is : "+ATMMachine.getBalance());
        System.out.println("Your Balance in ACC is : "+currentUser.getBalance());
        System.out.println("Enter the amount of notes to withdraw...");
        getNotes:while (true)
        {
            System.out.print("Enter the no of 2000 notes : ");
            int twoThousandNotes = Integer.parseInt(s.nextLine());
            if(twoThousandNotes > notes.get(0).getCount() || (2000*twoThousandNotes) > currentUser.getBalance())
            {
                System.out.println("Notes not available.. or The amount not available in your account");
                continue getNotes;
            }
            else
            {
                System.out.print("Enter the no of 500 notes : ");
                int fiveHundredNotes = Integer.parseInt(s.nextLine());
                if(fiveHundredNotes > notes.get(1).getCount() || (500*fiveHundredNotes) > currentUser.getBalance())
                {
                    System.out.println("Notes not available.. or The amount not available in your account");
                    continue getNotes;
                }
                else
                {
                    System.out.print("Enter the no of 200 notes : ");
                    int twoHundredNotes = Integer.parseInt(s.nextLine());
                    if(twoHundredNotes > notes.get(2).getCount() || (200*twoHundredNotes) > currentUser.getBalance())
                    {
                        System.out.println("Notes not available.. or The amount not available in your account");
                        continue getNotes;
                    }
                    else
                    {
                        System.out.print("Enter the no of 100 notes : ");
                        int oneHundredNotes = Integer.parseInt(s.nextLine());
                        if(oneHundredNotes > notes.get(3).getCount() || (100*oneHundredNotes) > currentUser.getBalance())
                        {
                            System.out.println("Notes not available.. or The amount not available in your account");
                            continue getNotes;
                        }
                        else
                        {
                            long amountToWithdraw = 2000 * twoThousandNotes + 500 * fiveHundredNotes + 200 * twoHundredNotes + 100 * oneHundredNotes;
                            ArrayList<Notes> notesToAdded = ATMMachine.getNotesInAtm();
                            notesToAdded.get(0).setCount(notesToAdded.get(0).getCount() - twoThousandNotes);
                            notesToAdded.get(1).setCount(notesToAdded.get(1).getCount() - fiveHundredNotes);
                            notesToAdded.get(2).setCount(notesToAdded.get(2).getCount() - twoHundredNotes);
                            notesToAdded.get(3).setCount(notesToAdded.get(3).getCount() - oneHundredNotes);
                            if (amountToWithdraw <= ATMMachine.getBalance())
                            {
                                if (amountToWithdraw <= currentUser.getBalance()) {
                                    double currentBalanceInAcc = currentUser.getBalance() - amountToWithdraw;
                                    double currentBalanceInATM = ATMMachine.getBalance() - amountToWithdraw;
                                    System.out.println("Withdrawl amount of Rs." + amountToWithdraw + " is successful");
                                    currentUser.setBalance(currentBalanceInAcc);
                                    ATMMachine.setBalance(currentBalanceInATM);
                                    currentUser.addUserTransactionHistory("Your account is debited with Rs." + amountToWithdraw + "--- Balance :" + currentUser.getBalance());
                                    Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is debited with Rs." + amountToWithdraw + "--- User Balance : " + currentUser.getBalance() + "--- ATM Balance : " + ATMMachine.getBalance());
                                    System.out.println("Your current balance is " + currentUser.getBalance());
                                    break ;
                                } else {
                                    System.out.println("The amount in your account is not sufficent to withdraw");
                                    break ;
                                }
                            } else {
                                System.out.println("Sorryy....There is no amount in the ATM. Come back Later");
                                break ;

                            }
                        }
                    }
                }



            }

        }




    }

    public static void depositCash(Scanner s,User currentUser)
    {
        System.out.println("Enter the no of amount of notes to deposit as User :");
        System.out.print("Enter the no of 2000 notes : ");
        int twoThousandNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 500 notes : ");
        int fiveHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 200 notes : ");
        int twoHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 100 notes : ");
        int oneHundredNotes = Integer.parseInt(s.nextLine());

        long amountToDeposit = 2000*twoThousandNotes + 500*fiveHundredNotes + 200*twoHundredNotes + 100*oneHundredNotes;
        ArrayList<Notes> notesToAdded = ATMMachine.getNotesInAtm();
        notesToAdded.get(0).setCount(notesToAdded.get(0).getCount() + twoThousandNotes);
        notesToAdded.get(1).setCount(notesToAdded.get(1).getCount() + fiveHundredNotes);
        notesToAdded.get(2).setCount(notesToAdded.get(2).getCount() + twoHundredNotes);
        notesToAdded.get(3).setCount(notesToAdded.get(3).getCount() + oneHundredNotes);
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