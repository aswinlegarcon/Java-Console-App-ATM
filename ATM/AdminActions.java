package ATM;
import ATM.Notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions {


    //        for users by admins

    // function to check admin and return objects for verification
    public static Admin checkAdmin()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Admin name: ");
        String name = s.nextLine();
        System.out.print("Enter the Password: ");
        String pass = s.nextLine();
        ArrayList<ATM.Admin> adminsAvailable = ATMMachine.getAvailableAdmins();
        for(ATM.Admin individualAdmin:adminsAvailable)// loop user arraylist for checking the availability of admin
        {
            if (individualAdmin.getAdminName().equals(name) && individualAdmin.getPassword().equals(pass))// if username and password matches - return admin object
            {
                return individualAdmin;
            }
            else if(individualAdmin.getAdminName().equals(name) && !individualAdmin.getPassword().equals(pass))// if username matches but pin does not match - return new admin with null as username
            {
                return new ATM.Admin(null,null);
            }
        }
        return null;// if no admins found in the array list then return null
    }

// function to add user
    public static void addUser() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the User's Name to Add : ");
        String userName = s.nextLine();
        for(User users:ATMMachine.getAvailableUsers())
        {
            if(users.getUserName().equals(userName))//check if the username already exists
            {
                System.out.println("User already exists..");
                return;
            }
        }
        System.out.print("Enter the pin of User : ");
        int pin = Integer.parseInt(s.nextLine());
        ATMMachine.getAvailableUsers().add(new User(userName, pin, 0));// adding user object to the arraylist
        System.out.println("User Added Successfully..");
    }

//    function to delete the user
    public static void deleteUser() {
        Scanner s = new Scanner(System.in);
        ArrayList<User> usersToRemove = ATMMachine.getAvailableUsers(); // getting and storing the arraylist of users
        if (!usersToRemove.isEmpty()) { //if users not empty
            System.out.println("The Available Users are...");
            int count = 1;
            for (User temp : usersToRemove) { // display all the available users
                System.out.println(count + " - " + temp.getUserName());
                count++;
            }
            System.out.print("Enter the Id no of the User to remove :"); // asking the admin which username to remove
            int removeChoice = Integer.parseInt(s.nextLine());
            ATMMachine.getAvailableUsers().remove(removeChoice-1);// index start from zero so -1 to remove
            System.out.println("User Successfully Removed...");
        }
        else // if no users available
        {

            System.out.println("No users available..Add user and try again..");
        }
    }

//   function to deposit money in ATM
    public static void depositMoney(Admin currentAdmin) {

        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Amount :");//asking the amount
        long firstAmountToDeposit = Long.parseLong(s.nextLine());
//        asking the notes one by one
        System.out.println("Enter the no of amount of notes to deposit as User :");
        System.out.print("Enter the no of 2000 notes : ");
        int twoThousandNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 500 notes : ");
        int fiveHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 200 notes : ");
        int twoHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 100 notes : ");
        int oneHundredNotes = Integer.parseInt(s.nextLine());
//      calculating the notes entered as amount
        long amountToDeposit = AtmActions.getDepositedBalance(twoThousandNotes, fiveHundredNotes, twoHundredNotes, oneHundredNotes);
        if (firstAmountToDeposit == amountToDeposit)// if entered amount and notes entered(calculated as amount) equals
        {
            // get the notes arraylist
            for (Notes note : ATMMachine.getNotesInAtm()) // loop the notes objects
            {
                String noteType = note.getNote();// get the object name
                switch (noteType) {
                    case "2000": // if 2000 then set count of two thousand
                        note.setCount(note.getCount() + twoThousandNotes);
                        break;
                    case "500": // if 500 then set count of five hundred
                        note.setCount(note.getCount() + fiveHundredNotes);
                        break;
                    case "200": // if 200 then set count of two hundred
                        note.setCount(note.getCount() + twoHundredNotes);
                        break;
                    case "100": // if 100 then set count of one hundred
                        note.setCount(note.getCount() + oneHundredNotes);
                        break;
                }

            }
            ATMMachine.getAvailableTransactions().add(new Transactions("Deposited", amountToDeposit, currentAdmin)); // adding transaction as objects
            for (Notes notes : ATMMachine.getNotesInAtm())//loop for printing notes available after deposit
            {
                System.out.println("Note: " + notes.getNote() + " Count : " + notes.getCount());
            }
            double balanceInAtm = ATMMachine.getBalance() + amountToDeposit;
            ATMMachine.setBalance(balanceInAtm); // set ATM's balance
            System.out.println("The deposit of Rs." + amountToDeposit + " is added successfully");
            System.out.println("ATM balance " + ATMMachine.getBalance());
        }
        else// if entered amount and calculated amount does not match
        {
            System.out.println("The amount you entered in notes does not match..Enter correct amount");
        }
    }

//    function to view transactions done by admin,all,specific user
    public static void viewTransactions(Admin currentAdmin) {
        Scanner s = new Scanner(System.in);
        ArrayList<Transactions> atmHistory = ATMMachine.getAvailableTransactions();// getting transaction history
//        asking user to choose operation
        System.out.println("Enter the Operation to do \n 1. See All Transactions \n 2. See Admin Transaction \n 3. See Specific User Transactions \n 4. Exit");
        int choice = Integer.parseInt(s.nextLine());
        if (!atmHistory.isEmpty()) {//if transaction not empty
            switch (choice) {
//                to show all transactions
                case 1:
                    for (Transactions history : atmHistory) {
                        System.out.println(history.getUser() + " has " + history.getType() + " Rs." + history.getAmount());
                    }
                    break;
//                    to show admin transactions
                case 2:
                    for (Transactions history : atmHistory) {
                        if (currentAdmin.getAdminName().equals(history.getUser())) {
                            System.out.println(history.getUser() + " has " + history.getType() + " Rs." + history.getAmount());
                        }

                    }
                    break;
//                    to show specific user transactions
                case 3:
                    System.out.println("Available users..");
                    for (User users : ATMMachine.getAvailableUsers()) {//display all the available users
                        System.out.println("* " + users.getUserName());
                    }
                    System.out.print("Enter user to see history : ");// asking which user's transaction has to seen
                    String seeHistoryofUser = s.nextLine();
                    for (Transactions history : atmHistory) {
                        if (history.getUser().equals(seeHistoryofUser)) { // show transaction of that user
                            System.out.println(seeHistoryofUser + " has " + history.getType() + " Rs." + history.getAmount());
                        }
                    }
                    break;
//                    to exit
                case 4:
                    break;

            }

        }
//for future
//        for ATM by admins
//        public static void addATM()
    }
}