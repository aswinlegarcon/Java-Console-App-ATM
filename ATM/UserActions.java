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

    private static double performWithdraw(double useramount, Notes note, ArrayList<String> denominationsList) {
        long count = (long) (useramount/Integer.parseInt(note.getNote()));
        if(Long.parseLong(note.getNote()) <= useramount && count <= note.getCount()) {
            useramount = useramount - (count * Integer.parseInt(note.getNote()));
            note.setCount((note.getCount() - count));
            denominationsList.add("You got " + note.getNote() + " count " + count);
            return useramount;
        }
        return useramount;
    }

//    public static void withdrawCash(Scanner s , User currentUser, Admin currentAdmin)
//    {
//        ArrayList<Notes> notesToAdded = ATMMachine.getNotesInAtm();
//        System.out.println("Current notes in ATM...");
//        for (Notes notes:notesToAdded)
//        {
//            System.out.print(notes.getNote()+" - "+notes.getCount()+"---");
//        }
//        System.out.println("Enter the amount to withdraw : ");
//        long amountToWithdraw = Long.parseLong(s.nextLine());
//
//        long currentTwoThousand = 0;
//        long currentFiveHundred = 0;
//        long currentTwoHundred = 0;
//        long currentOneHundred = 0;
//        for(Notes notes:notesToAdded)
//        {
//            if(notes.getNote().equals("2000"))
//            {
//                currentTwoThousand = notes.getCount();
//            }
//            else if(notes.getNote().equals("500"))
//            {
//                currentFiveHundred = notes.getCount();
//            }
//            else if(notes.getNote().equals("200"))
//            {
//                currentTwoHundred = notes.getCount();
//            }
//            else if(notes.getNote().equals("100"))
//            {
//                currentOneHundred = notes.getCount();
//            }
//        }
//                           // ATMMachine.updateCash("Withdraw", notesToAdded, twoThousandNotes,fiveHundredNotes,twoHundredNotes,oneHundredNotes);
//                            if (amountToWithdraw <= ATMMachine.getBalance())
//                            {
//                                if (amountToWithdraw <= currentUser.getBalance()) {
//                                    double currentBalanceInAcc = currentUser.getBalance() - amountToWithdraw;
//                                    double currentBalanceInATM = ATMMachine.getBalance() - amountToWithdraw;
//
//                                    long tempBalance = amountToWithdraw;
//                                    while (tempBalance!=0)
//                                    {
//                                        if(tempBalance == 100)
//                                        {
//                                            currentOneHundred--;
//                                            tempBalance-=100*currentOneHundred;
//                                            System.out.println("You got 1 100 Note");
//                                        }
//                                        else if(tempBalance == 200)
//                                        {
//                                            currentTwoHundred--;
//                                            System.out.println("You got 1 200 Note");
//                                        }
//                                        else if(tempBalance == 500)
//                                        {
//                                            currentFiveHundred--;
//                                            System.out.println("You got 1 500 Note");
//                                        }
//                                        else if(tempBalance == 2000)
//                                        {
//                                            currentTwoThousand--;
//                                            System.out.println("You got 1 2000 Note");
//                                        }
//                                        else if(tempBalance>100)
//                                        {
//                                            while (true)
//                                            {
//                                                if(tempBalance>2000)
//                                                {
//                                                    long twoThousandNotes = tempBalance/2000;
//                                                    if(currentTwoThousand>=twoThousandNotes)
//                                                    {
//                                                        tempBalance = tempBalance - (2000*twoThousandNotes);
//                                                        currentTwoThousand -= twoThousandNotes;
//                                                        System.out.println("You got "+twoThousandNotes+" 2000 s");
//                                                        break;
//                                                    }
//                                                }
//                                                else if(tempBalance>500)
//                                                {
//                                                    long fiveHundredNotes = tempBalance/500;
//                                                    if(currentFiveHundred>=fiveHundredNotes)
//                                                    {
//                                                        tempBalance = tempBalance - (500*fiveHundredNotes);
//                                                        currentFiveHundred -= fiveHundredNotes;
//                                                        System.out.println("You got "+fiveHundredNotes+" 500 s");
//                                                        break;
//                                                    }
//                                                }
//                                                else if(tempBalance>200)
//                                                {
//                                                    long twoHundredNotes = tempBalance/200;
//                                                    if(currentTwoHundred>=twoHundredNotes)
//                                                    {
//                                                        tempBalance = tempBalance - (200*twoHundredNotes);
//                                                        currentTwoHundred -= twoHundredNotes;
//                                                        System.out.println("You got "+twoHundredNotes+" 200 s");
//                                                        break;
//                                                    }
//                                                }
//                                                else
//                                                    System.out.println("Denomination not available");
//                                            }
//
//                                        }
//                                        for(Notes notes:notesToAdded)
//                                        {
//                                            if(notes.getNote().equals("2000"))
//                                            {
//                                                notes.setCount(currentTwoThousand);
//                                            }
//                                            else if(notes.getNote().equals("500"))
//                                            {
//                                                notes.setCount(currentFiveHundred);
//                                            }
//                                            else if(notes.getNote().equals("200"))
//                                            {
//                                                notes.setCount(currentTwoHundred);
//                                            }
//                                            else if(notes.getNote().equals("100"))
//                                            {
//                                                notes.setCount(currentOneHundred);
//                                            }
//                                        }
//
//                                    }
//
//                                    System.out.println("Withdrawl amount of Rs." + amountToWithdraw + " is successful");
//                                    System.out.println("Now notes in ATM are as follow");
//                                    for (Notes notes:notesToAdded)
//                                    {
//                                        System.out.print(notes.getNote()+" - "+notes.getCount()+"---");
//                                    }
//                                    currentUser.setBalance(currentBalanceInAcc);
//                                    ATMMachine.setBalance(currentBalanceInATM);
//                                    currentUser.addUserTransactionHistory("Your account is debited with Rs." + amountToWithdraw + "--- Balance :" + currentUser.getBalance());
//                                    currentAdmin.addATMTransactionHistory(currentUser.getUserName() + "'s account is debited with Rs." + amountToWithdraw + "--- User Balance : " + currentUser.getBalance() + "--- ATM Balance : " + ATMMachine.getBalance());
//                                    System.out.println("Your current balance is " + currentUser.getBalance());
//                                } else {
//                                    System.out.println("The amount in your account is not sufficent to withdraw");
//                                }
//                            } else {
//                                System.out.println("Sorryy....There is no amount in the ATM. Come back Later");
//
//                            }
//                        }

    public static void withdrawCash (Scanner s, User currentUser) throws CloneNotSupportedException {
        ArrayList<String> notesTransaction = new ArrayList<>();
        ArrayList<Notes> notesDuplicate = new ArrayList<>();

        System.out.print("Enter the amount to withdraw :");
        long amountToWithdraw = Long.parseLong(s.nextLine());
        long finalAmountForCalculations = amountToWithdraw;
        for(Notes notesInAtm:ATMMachine.getNotesInAtm())
        {
            notesDuplicate.add(notesInAtm.clone());
        }
        while (amountToWithdraw!=0)
        {
            for(Notes notesInDuplicate:notesDuplicate) {
                String noteType = notesInDuplicate.getNote();
                switch (noteType) {
                    case "2000", "500", "200", "100":
                        amountToWithdraw = (long) UserActions.performWithdraw(amountToWithdraw, notesInDuplicate, notesTransaction);
                        break;
                }
            }
                if(amountToWithdraw == 0)
                {
                    ATMMachine.setNotesInAtm(notesDuplicate);
                    currentUser.setBalance(currentUser.getBalance()-finalAmountForCalculations);
                    for(String notesGiven: notesTransaction)
                    {
                        System.out.println("*"+notesGiven);
                    }
                    for(Notes notes:ATMMachine.getNotesInAtm())
                    {
                        System.out.println("Note: "+ notes.getNote()+" Count : "+notes.getCount() );
                    }
                    ATMMachine.getAvailableTransactions().add(new Transactions("Withdrawed",finalAmountForCalculations,currentUser));
                    break;
                }
                else
                {
                    System.out.println("There are no denominations...Enter another amount");
                    break;
                }

            }
        }




    public static void depositCash(Scanner s,User currentUser)
    {
        System.out.print("Enter the Amount :");
        long firstAmountToDeposit = Long.parseLong(s.nextLine());
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
        if(firstAmountToDeposit==amountToDeposit)
        {
            ArrayList<Notes> notesToAdded = ATMMachine.getNotesInAtm();
            for(Notes note:notesToAdded)
            {
                String noteType = note.getNote();
                switch (noteType) {
                    case "2000":
                        note.setCount(note.getCount()+twoThousandNotes);
                        break;
                    case "500":
                        note.setCount(note.getCount()+fiveHundredNotes);
                        break;
                    case "200":
                        note.setCount(note.getCount()+twoHundredNotes);
                        break;
                    case "100":
                        note.setCount(note.getCount()+oneHundredNotes);
                        break;
                }

            }
            ATMMachine.setNotesInAtm(notesToAdded);
            ATMMachine.getAvailableTransactions().add(new Transactions("Deposited",amountToDeposit,currentUser));
            for(Notes notes:ATMMachine.getNotesInAtm())
            {
                System.out.println("Note: "+ notes.getNote()+" Count : "+notes.getCount());
            }
            double currentBalance = currentUser.getBalance() + amountToDeposit;
            double balanceInAtm = ATMMachine.getBalance() + amountToDeposit;
            currentUser.setBalance(currentBalance);
            ATMMachine.setBalance(balanceInAtm);
            System.out.println("The deposit of Rs." + amountToDeposit + " is added successfully");
            System.out.println("Your current balance is " + currentUser.getBalance());
            System.out.println("ATM balance "+ ATMMachine.getBalance());
        }
        else
        {
            System.out.println("The amount you entered in notes does not match..Enter correct amount");
        }

    }

    public static void viewTransactions(User currentUser)
    {
        ArrayList<Transactions> userHistory = ATMMachine.getAvailableTransactions();
        if (!userHistory.isEmpty()) {
            System.out.println("The Transactions you have made...\n");
            for (Transactions history : userHistory) {
                if(currentUser.getUserName().equals(history.getUser()))
                {
                    System.out.println("You "+ history.getType() + " Rs "+history.getAmount());
                }

            }
        } else {
            System.out.println("There are no Transactions..");
        }
    }

}