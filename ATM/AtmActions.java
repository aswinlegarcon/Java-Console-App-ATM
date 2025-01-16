package ATM;

import ATM.Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AtmActions {

    public static void startAtm() throws CloneNotSupportedException {
        Scanner s = new Scanner(System.in);
        ATMMachine.getAvailableAccounts().add(new Admin("admin", "admin")); // creating an admin for login
        while (true) { // infinite loop until user chooses exit
            System.out.println("Enter Your Role : \n 1. Admin \n 2. User \n 3. Exit"); // asking roles
            int roleChoice = Integer.parseInt(s.nextLine());
//            for choice 1 - IF ADMIN
            if (roleChoice == 1) {
                Admin currentAdmin = (Admin)AdminActions.checkAdmin(); // storing account object type-casted to admin object in a reference (returns null if no admin , returns object of admin if admin found , return new object with username null if password wrong)
                if(currentAdmin==null)
                {
                    System.out.println("No admins found..");
                }
                else if(currentAdmin.getUserName()==null)
                {
                    System.out.println("Invalid credentials...");
                }
                else
                {
                    AtmActions.adminEntry(currentAdmin); //if current object returned then calling operations of admins
                }

            }
//            for choice 2 IF USER
            else if (roleChoice == 2)
            {
                User currentUser = (User)UserActions.checkUser();// storing account object type casted to user object in a reference (returns null if no user , returns object of user if user found , return new object with username null if password wrong)
                if(currentUser==null)
                {
                    System.out.println("No users found..");
                }
                else if(currentUser.getUserName()==null)
                {
                    System.out.println("Invalid credentials...");
                }
                else
                {
                    AtmActions.userEntry(currentUser);//if current object returned then calling operations of users
                }
            }
//            for choice 3 IF EXIT
            else if (roleChoice == 3)
            {
                System.exit(1);
            }
            else
            {
                System.out.println("Enter correct option...");
            }

        }
    }


    public static void userEntry(User currentUser) throws CloneNotSupportedException
    {
        Scanner s = new Scanner(System.in);
        System.out.println("User Login Success..");
        while (true) {
            System.out.println("Enter the Operation to do.."); // asking which operation to do
            System.out.println(" 1. Change Pin \n 2. Check Balance \n 3. Withdraw Cash \n 4. Deposit Cash \n 5. Show History \n 6. Logout");
            int operationChoice = Integer.parseInt(s.nextLine());
//                         1 - change the PIN of user
            if (operationChoice == 1)
            {
                UserActions.changePin(s,currentUser);
            }
//                        2 - Check balance of user
            else if (operationChoice == 2)
            {
                System.out.println("Your current balance is " + currentUser.getBalance());
            }
//                        3 - Withdraw Cash from ATM
            else if (operationChoice == 3)
            {
                UserActions.withdrawCash(s,currentUser);
            }
//                        4 - Deposit cash to ATM and Account
            else if (operationChoice == 4)
            {
                UserActions.depositCash(s,currentUser);
            }
//                        5 - View the transactions made by user
            else if (operationChoice == 5)
            {
                UserActions.viewTransactions(currentUser);
            }
//                        6 - Logout of account
            else if (operationChoice == 6)
            {
                System.out.println("Logged out...");
                return;
            }
//                        if wrong input
            else
            {
                System.out.println("Enter the correct option");
            }

        }
    }


    public static void adminEntry(Admin currentAdmin) {
        Scanner s = new Scanner(System.in);
        System.out.println("Access Granted");
        while (true)
        {
//                    Asking the operations to do ...
            System.out.println("Enter the operation to do... \n 1. Add User \n 2. Delete User \n 3. Deposit Cash in ATM \n 4. Show All Transaction History\n 5. Logout");
            int operationChoice = Integer.parseInt(s.nextLine());
//                    1 - Adding a new User
            if (operationChoice == 1)
            {
                AdminActions.addUser();
            }
//                    2 - Deleting an existing user
            else if (operationChoice == 2)
            {
                AdminActions.deleteUser();
            }
//                    3 - Deposit the money into ATM
            else if (operationChoice == 3)
            {
                AdminActions.depositMoney(currentAdmin);
            }
//                    4 - View all transactions (all,specific user,admin)
            else if (operationChoice == 4)
            {
                AdminActions.viewTransactions(currentAdmin);
            }
//                    5 - Logout of Account
            else if (operationChoice == 5)
            {
                System.out.println("Logged out....");
                return;
            }
//                    For wrong input
            else
            {
                System.out.println("Enter the correct option");
            }
        }
    }


    //    Method to calculating total amount with all the notes
    public static long getDepositedBalance(int twoThousand,int fiveHundred, int twoHundred, int oneHundred)
    {
        long balance = 2000*twoThousand + 500*fiveHundred + 200*twoHundred + 100*oneHundred;
        return balance;
    }

}