package ATM;
import ATM.Notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions {


    //        for users by admins

    public static Admin checkAdmin()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Admin name: ");
        String name = s.nextLine();
        System.out.print("Enter the Password: ");
        String pass = s.nextLine();
        ArrayList<ATM.Admin> adminsAvailable = ATMMachine.getAvailableAdmins();
        for(ATM.Admin individualAdmin:adminsAvailable)
        {
            if (individualAdmin.getAdminName().equals(name) && individualAdmin.getPassword().equals(pass))
            {
                return individualAdmin;
            }
            else if(individualAdmin.getAdminName().equals(name) && !individualAdmin.getPassword().equals(pass))
            {
                return new ATM.Admin(null,null);
            }
        }
        return null;
    }


    public static void addUser() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the User's Name to Add : ");
        String userName = s.nextLine();
        System.out.print("Enter the Password of User : ");
        int pin = Integer.parseInt(s.nextLine());
        ATMMachine.getAvailableUsers().add(new User(userName, pin, 0));
        System.out.println("User Added Successfully..");
    }

    public static void deleteUser() {
        Scanner s = new Scanner(System.in);
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

    public static void depositMoney(Admin currentAdmin) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the no of amount of notes to deposit in ATM :");
        System.out.print("Enter the no of 2000 notes : ");
        int twoThousandNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 500 notes : ");
        int fiveHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 200 notes : ");
        int twoHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 100 notes : ");
        int oneHundredNotes = Integer.parseInt(s.nextLine());
        ATMMachine.setBalance(twoThousandNotes, fiveHundredNotes, twoHundredNotes, oneHundredNotes);
        long depositedBalance = AtmActions.getDepositedBalance(twoThousandNotes, fiveHundredNotes, twoHundredNotes, oneHundredNotes);
        ATMMachine.getAvailableTransactions().add(new Transactions("Deposited", depositedBalance, currentAdmin));
        System.out.println("The amount of Rs." + depositedBalance + " is added successfully");
        System.out.println("Notes in ATM are as follows..");
        ArrayList<Notes> notesInAtm = ATMMachine.getNotesInAtm();
        for (Notes notes : notesInAtm) {
            System.out.print(notes.getNote() + " - " + notes.getCount() + "---");
        }
        System.out.println("\nThe current balance in ATM is " + ATMMachine.getBalance());
    }

    public static void viewTransactions(Admin currentAdmin) {
        Scanner s = new Scanner(System.in);
        ArrayList<Transactions> atmHistory = ATMMachine.getAvailableTransactions();
        System.out.println("Enter the Operation to do \n 1. See All Transactions \n 2. See Admin Transaction \n 3. See Specific User Transactions \n 4. Exit");
        int choice = Integer.parseInt(s.nextLine());
        if (!atmHistory.isEmpty()) {
            switch (choice) {
                case 1:
                    for (Transactions history : atmHistory) {
                        System.out.println(history.getUser() + " has " + history.getType() + " Rs." + history.getAmount());
                    }
                    break;
                case 2:
                    for (Transactions history : atmHistory) {
                        if (currentAdmin.getAdminName().equals(history.getUser())) {
                            System.out.println(history.getUser() + " has " + history.getType() + " Rs." + history.getAmount());
                        }

                    }
                    break;
                case 3:
                    System.out.println("Available users..");
                    for (User users : ATMMachine.getAvailableUsers()) {
                        System.out.println("* " + users.getUserName());
                    }
                    System.out.print("Enter user to see history : ");
                    String seeHistoryofUser = s.nextLine();
                    for (Transactions history : atmHistory) {
                        if (history.getUser().equals(seeHistoryofUser)) {
                            System.out.println(seeHistoryofUser + " has " + history.getType() + " Rs." + history.getAmount());
                        }

                    }
                    break;
                case 4:
                    break;

            }

        }
//for future
//        for ATM by admins
//        public static void addATM()
    }
}