package ATM;

import ATM.Notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class UserActions
{
    public static User checkUser()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the User name: ");
        String uname = s.nextLine();
        System.out.print("Enter the Pin : ");
        int pin = Integer.parseInt(s.nextLine());
        ArrayList<User> usersAvailable = ATMMachine.getAvailableUsers();
        for(User individualUser:usersAvailable)
        {
            if (individualUser.getUserName().equals(uname) && individualUser.getPin() == pin)
            {
                return individualUser;
            }
            else if(individualUser.getUserName().equals(uname) && individualUser.getPin() != pin)
            {
                return new User(null,0,0);
            }
        }
        return null;
    }

    public static void changePin(Scanner s,User currentUser)
    {
        System.out.print("Enter the Pin to change :");
        int pin = Integer.parseInt(s.nextLine());
        currentUser.setPin(pin);
        System.out.println("Pin changed");
    }

    private static double performWithdraw(double useramount, Notes note, ArrayList<String> denominationsList) {
        long count = (long) (useramount/Integer.parseInt(note.getNote()));
        if(Long.parseLong(note.getNote()) <= useramount && 0 < note.getCount())
        {
            if(count <= note.getCount())
            {
                useramount = useramount - (count * Integer.parseInt(note.getNote()));
                note.setCount((note.getCount() - count));
                denominationsList.add("You got " + note.getNote() + " count " + count);
            }
            else
            {
                useramount = useramount - (note.getCount() * Integer.parseInt(note.getNote()));
                denominationsList.add("You got " + note.getNote() + " count " + note.getCount());
                note.setCount((0));
            }

            return useramount;
        }
        return useramount;
    }

    public static void withdrawCash (Scanner s, User currentUser) throws CloneNotSupportedException {
        ArrayList<String> notesTransaction = new ArrayList<>();
        ArrayList<Notes> notesDuplicate = new ArrayList<>();

        System.out.print("Enter the amount to withdraw :");
        long amountToWithdraw = Long.parseLong(s.nextLine());
        if(amountToWithdraw<=currentUser.getBalance())
        {
            if(amountToWithdraw<=ATMMachine.getBalance())
            {
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
            System.out.println("Insufficient amount in ATM ....");
            return;
        }
        System.out.println("Insufficient amount in your Account ...");
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