package ATM;
import ATM.Notes.Note;


import java.util.ArrayList;
import java.util.Scanner;

// implementing AdminAction interface to get a template of all methods
public class AdminActions implements ATM.Templates.AdminActions {


    //        for users by admins

    // function to check admin and return objects for verification
//    Overriding method from Admin Interface
    public Account login()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Admin name: ");
        String name = s.nextLine();
        System.out.print("Enter the Password: ");
        String pass = s.nextLine();
        ArrayList<Account> adminsAvailable = ATMMachine.getAvailableAccounts();
        for(Account individualAdmin:adminsAvailable)// loop user arraylist for checking the availability of admin
        {
            if(individualAdmin instanceof Admin) // check if the current object came inside loop is of type Admin's object
            {
                if (individualAdmin.getUserName().equals(name) && individualAdmin.getPassword().equals(pass))// if username and password matches - return admin object
                {
                    return individualAdmin;
                }
                else if(individualAdmin.getUserName().equals(name) && !individualAdmin.getPassword().equals(pass))// if username matches but pin does not match - return new admin with null as username
                {
                    return new Account(null,null);
                }
            }

        }
        return null;// if no admins found in the array list then return null
    }


    // function to add user
    //    Overriding method from Admin Interface
    @Override
    public void addUser()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the User's Name to Add : ");
        String userName = s.nextLine();
        for(Account users:ATMMachine.getAvailableAccounts()) // loop over all the accounts
        {
            if (users instanceof User) // true if the current object came in is of object type User
            {
                if (users.getUserName().equals(userName))//check if the username already exists
                {
                    System.out.println("User already exists..");
                    return;
                }
            }
        }
                System.out.print("Enter the password of User : ");
                String password = s.nextLine();
                ATMMachine.getAvailableAccounts().add(new User(userName, password, 0));// adding user object to the Account arraylist
                System.out.println("User Added Successfully..");
    }

    //    function to delete the user
    //    Overriding method from Admin Interface
    @Override
    public  void deleteUser() {
        Scanner s = new Scanner(System.in);
        ArrayList<Account> usersToRemove = ATMMachine.getAvailableAccounts(); // getting and storing the arraylist of users
        if (!usersToRemove.isEmpty()) { //if users not empty
            System.out.println("The Available Users are...");
            int count = 1;
            for (Account temp : usersToRemove) { // display all the available users
                if(temp instanceof User)
                {
                    System.out.println(count + " - " + temp.getUserName());
                    count++;
                }
            }
            System.out.print("Enter the name of the User to remove :"); // asking the admin which username to remove
            String removeChoice = s.nextLine();
            for (Account temp : usersToRemove) { // display all the available accounts
                if(temp instanceof User) //display all the available users
                {
                    if(temp.getUserName().equals(removeChoice))
                    {
                        ATMMachine.getAvailableAccounts().remove(temp);// removing that current user object
                        System.out.println("User Successfully Removed...");
                        return;
                    }
                }
            }
        }
        else // if no users available
        {
            System.out.println("No users available..Add user and try again..");
        }
    }

    //   function to deposit money in ATM
    //    Overriding method from Admin Interface
    @Override
    public void depositMoney(Account currentAccount) {
        Scanner s = new Scanner(System.in);
        Admin currentAdmin = (Admin)currentAccount; // typecasting Account object to Admin reference type to access methods
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
            for (Note note : ATMMachine.getNotesInAtm().getNoteObjects()) // loop the notes objects
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
            currentAdmin.getAvailableTransactions().add(new Transactions("Deposited", amountToDeposit, currentAdmin.getUserName())); // adding transaction as objects
            for (Note note : ATMMachine.getNotesInAtm().getNoteObjects())//loop for printing notes available after deposit
            {
                System.out.println("Note: " + note.getNote() + " Count : " + note.getCount());
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
    //    Overriding method from Admin Interface
    @Override
    public void viewTransactions(Account currentAccount) {
        Scanner s = new Scanner(System.in);
        Admin currentAdmin = (Admin)currentAccount;
        ArrayList<Transactions> atmHistory = currentAdmin.getAvailableTransactions();// getting transaction history
//        asking user to choose operation
        while (true)
        {
            System.out.println("Enter the Operation to do \n 1. See All Transactions \n 2. See Admin Transaction \n 3. See Specific User Transactions \n 4. Exit");
            int choice = Integer.parseInt(s.nextLine());

                switch (choice) {
//                to show all transactions
                    case 1:
                        boolean checkTransactionAvailability = false; // variable to check if transactions are available or not
                        if (atmHistory.isEmpty()) { // if admin transaction empty
                            System.out.println("No admin transactions found...!!");
                        }
                        else // or print
                        {
                            for (Transactions history : atmHistory) {
                                System.out.println(history.getUser() + " has " + history.getType() + " Rs." + history.getAmount());
                            }
                        }

                        for(Account users : ATMMachine.getAvailableAccounts())
                        {
                            if(users instanceof User)
                            {
                                if(!users.getAvailableTransactions().isEmpty())
                                {
                                    checkTransactionAvailability = true;
                                    for (Transactions history : users.getAvailableTransactions()) {
                                        System.out.println(history.getUser() + " has " + history.getType() + " Rs." + history.getAmount());
                                    }
                                }
                            }
                        }
                        if(!checkTransactionAvailability)
                        {
                            System.out.println("No transactions for user..");
                        }
                        break;
//                    to show admin transactions
                    case 2:
                        if (!atmHistory.isEmpty()) {//if transaction not empty
                            for (Transactions history : atmHistory) {
                                System.out.println(history.getUser() + " has " + history.getType() + " Rs." + history.getAmount());
                            }
                            break;
                        }
                        System.out.println("No admin transactions found...!!");
                        break;
//                    to show specific user transactions
                    case 3:
                        boolean checkUserAvailability = false;
                        System.out.println("Available users..");
                        for (Account users : ATMMachine.getAvailableAccounts()) {//display all the available users
                            if(users instanceof User)
                            {
                                System.out.println("* " + users.getUserName());
                                checkUserAvailability = true;
                            }
                        }
                        if(!checkUserAvailability)
                        {
                            System.out.println("No Users found..");
                            break;
                        }
                        System.out.print("Enter user to see history : ");// asking which user's transaction has to seen
                        String seeHistoryofUser = s.nextLine();
                        for(Account user: ATMMachine.getAvailableAccounts())
                        {
                            if(user instanceof User)
                            {
                                if(user.getUserName().equals(seeHistoryofUser))
                                {
                                    if(user.getAvailableTransactions().isEmpty())
                                    {
                                        System.out.println("No transactions found for this user...");
                                        break;
                                    }
                                    for (Transactions history : user.getAvailableTransactions()) {
                                        // show transaction of that user
                                        System.out.println(seeHistoryofUser + " has " + history.getType() + " Rs." + history.getAmount());
                                    }
                                }
                            }
                        }

                        break;
//                    to exit
                    case 4:
                        return;

                }


        }

    }
//    Method to view all the users
    public void viewUsers()
    {
        System.out.println("The Available Users are :");
        for(Account account:ATMMachine.getAvailableAccounts()) // get all the acoount objects
        {
            if(account instanceof User) // if object is a of User object
            {
                System.out.println("->"+account.getUserName()); // print the user's name
            }
        }
    }
//    Method to add admins
    public void addAdmin()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Admin name to add : "); // get the admin name to add
        String adminName = s.nextLine();
        for(Account account: ATMMachine.getAvailableAccounts()) // loop over account objects
        {
            if(account.getClass().getSimpleName().equals("Admin")) // if the object is of Admin (Using getClass()) can also use instanceOf operator
            {
                if (account.getUserName().equals(adminName)) // if the entered name already exist print already exist
                {
                    System.out.println("Admin already exists..");
                    return; // return if already exists
                }
            }
        }
        System.out.print("Enter the password : "); // get the password
        String password = s.nextLine();
        ATMMachine.getAvailableAccounts().add(new Admin(adminName,password)); // add the admin object to the arraylist of accounts
        System.out.println("Admin added successfully..");
    }

    //for future
//        for ATM by admins
//        public static void addATM()

}