package ATM;

import java.util.Scanner;

public class AtmActions {

    public static void startAtm() throws CloneNotSupportedException {
        Scanner s = new Scanner(System.in);
        AdminActions adminActions = new AdminActions();
        UserActions userActions = new UserActions();
        ATMMachine.getAvailableAccounts().add(new Admin("admin", "admin")); // creating an admin for login
        while (true) { // infinite loop until user chooses exit
            System.out.println("Enter Your Role : \n 1. Admin \n 2. User \n 3. Exit"); // asking roles
            int roleChoice = Integer.parseInt(s.nextLine());
//            for choice 1 - IF ADMIN
            if (roleChoice == 1) {
                Account currentAdmin = adminActions.login(); // storing account object type-casted to admin object in a reference (returns null if no admin , returns object of admin if admin found , return new object with username null if password wrong)
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
                Account currentUser = userActions.login();// storing account object type casted to user object in a reference (returns null if no user , returns object of user if user found , return new object with username null if password wrong)
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


    public static void userEntry(Account currentAccount) throws CloneNotSupportedException
    {
        Scanner s = new Scanner(System.in);
        UserActions userActions = new UserActions();
        User currentUser = (User) currentAccount;
        System.out.println("User Login Success..");
        while (true) {
            System.out.println("Enter the Operation to do.."); // asking which operation to do
            System.out.println(" 1. Change Pin \n 2. Check Balance \n 3. Withdraw Cash \n 4. Deposit Cash \n 5. Show History \n 6. Logout");
            int operationChoice = Integer.parseInt(s.nextLine());
//                         1 - change the PIN of user
            if (operationChoice == 1)
            {
                userActions.changePin(currentUser);
            }
//                        2 - Check balance of user
            else if (operationChoice == 2)
            {
                System.out.println("Your current balance is " + currentUser.getBalance());
            }
//                        3 - Withdraw Cash from ATM
            else if (operationChoice == 3)
            {
                userActions.withdrawCash(currentUser);
            }
//                        4 - Deposit cash to ATM and Account
            else if (operationChoice == 4)
            {
                userActions.depositMoney(currentUser);
            }
//                        5 - View the transactions made by user
            else if (operationChoice == 5)
            {
                userActions.viewTransactions(currentUser);
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


    public static void adminEntry(Account currentAccount)
    {
        Scanner s = new Scanner(System.in);
        AdminActions adminActions = new AdminActions();
        Admin currentAdmin = (Admin) currentAccount;
        System.out.println("Access Granted");
        while (true)
        {
//                    Asking the operations to do ...
            System.out.println("Enter the operation to do... \n 1. Add User \n 2. Delete User \n 3. Deposit Cash in ATM \n 4. Show All Transaction History\n 5. View All Users\n 6. Add Admin\n 7. Logout");
            int operationChoice = Integer.parseInt(s.nextLine());
//                    1 - Adding a new User
            if (operationChoice == 1)
            {
                adminActions.addUser();
            }
//                    2 - Deleting an existing user
            else if (operationChoice == 2)
            {
                adminActions.deleteUser();
            }
//                    3 - Deposit the money into ATM
            else if (operationChoice == 3)
            {
                adminActions.depositMoney(currentAdmin);
            }
//                    4 - View all transactions (all,specific user,admin)
            else if (operationChoice == 4)
            {
                adminActions.viewTransactions(currentAdmin);
            }
//            5 - View all the available users
            else if(operationChoice == 5)
            {
                adminActions.viewUsers();
            }
//            6 - Add admins if needed
            else if(operationChoice == 6)
            {
                adminActions.addAdmin();
            }
//                    7 - Logout of Account
            else if (operationChoice == 7)
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