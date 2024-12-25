package ATM;
import ATM.Notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions
{


    //        for users by admins



    public static void addUser(String name , double balance)
    {
        ATMMachine.getAvailableUsers().add(new User(name,111111,balance)); //always default pin is 111111
    }

    public static void addUser(Scanner s)
    {
        System.out.print("Enter the User's Name to Add : ");
        String userName = s.nextLine();
        System.out.print("Enter the Password of User : ");
        int pin = Integer.parseInt(s.nextLine());
        ATMMachine.getAvailableUsers().add(new User(userName, pin,1000));
        System.out.println("User Added Successfully..");

    }

    public static void deleteUser(Scanner s)
    {
        ArrayList<User> usersToRemove = ATMMachine.getAvailableUsers();
        if (!usersToRemove.isEmpty()) {
            System.out.println("The Available Users are...");
            int count = 0;
            for (User temp : usersToRemove) {
                System.out.println(count + " - " + temp.getUserName());
                count++;
            }
            System.out.print("Enter the Id no of the User to remove :");
            int removeChoice = Integer.parseInt(s.nextLine());
            ATMMachine.getAvailableUsers().remove(removeChoice);
            System.out.println("User Successfully Removed...");
        } else {
            System.out.println("No users available..Add user and try again..");
        }
    }

    public static void depositMoney(Scanner s, Admin currentAdmin)
    {
        System.out.println("Enter the no of amount of notes to deposit in ATM :");
        System.out.print("Enter the no of 2000 notes : ");
        int twoThousandNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 500 notes : ");
        int fiveHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 200 notes : ");
        int twoHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 100 notes : ");
        int oneHundredNotes = Integer.parseInt(s.nextLine());
        ATMMachine.setBalance(twoThousandNotes,fiveHundredNotes,twoHundredNotes,oneHundredNotes);
        long depositedBalance = AtmActions.getDepositedBalance(twoThousandNotes,fiveHundredNotes,twoHundredNotes,oneHundredNotes);
        currentAdmin.addATMTransactionHistory("Admin has deposited Rs." + depositedBalance + " --- ATM Balance : " + ATMMachine.getBalance());
        System.out.println("The amount of Rs." + depositedBalance + " is added successfully");
        System.out.println("Notes in ATM are as follows..");
        ArrayList<Notes> notesInAtm = ATMMachine.getNotesInAtm();
        for (Notes notes:notesInAtm )
        {
            System.out.print(notes.getNote()+" - "+notes.getCount()+"---");
        }
        System.out.println("\nThe current balance in ATM is " + ATMMachine.getBalance());
    }

    public static void viewTransactions(Admin currentAdmin)
    {
        ArrayList<String> atmHistory = currentAdmin.getATMTransactionHistory();
        if (!atmHistory.isEmpty()) {
            System.out.println("Transactions made by Users...\n");
            for (String history : atmHistory) {
                System.out.println(history);
            }
        } else {
            System.out.println("There are no Transactions..");
        }
    }





//for future
//        for ATM by admins
//        public static void addATM()


}