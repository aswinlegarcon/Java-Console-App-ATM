package ATM;



/*For now this is  static
 * in future multiple machines can be
 * added here by creating objects*/

import ATM.Notes.*;

import java.util.ArrayList;
import java.util.Scanner;


public class ATMMachine
{
    private static ArrayList<User> users = new ArrayList<>();
//    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Notes> notesInAtm = new ArrayList<Notes>();
    private static double balance;

    public static ArrayList<User> getAvailableUsers()
    {
        return users;
    }

    public static double getBalance()
    {
        return ATMMachine.balance;
    }

    public static void setBalance(int twoThousand,int fiveHundred, int twoHundred, int oneHundred)
    {
       notesInAtm.add(0,new TwoThousand()); notesInAtm.get(0).setNote("2000"); notesInAtm.get(0).setCount(twoThousand);
       notesInAtm.add(1,new FiveHundred()); notesInAtm.get(1).setNote("500"); notesInAtm.get(1).setCount(fiveHundred);
       notesInAtm.add(2,new TwoHundred());  notesInAtm.get(2).setNote("200"); notesInAtm.get(2).setCount(twoHundred);
       notesInAtm.add(3,new OneHundred());  notesInAtm.get(3).setNote("100"); notesInAtm.get(3).setCount(oneHundred);
       for(Notes notes:notesInAtm)
       {
           balance += Integer.parseInt(notes.getNote()) * notes.getCount();
       }
    }

    public static void setBalance(double balance)
    {
        ATMMachine.balance = balance;

    }

    public static ArrayList<Notes> getNotesInAtm()
    {
        return notesInAtm;
    }

    public static long getDepositedBalance(int twoThousand,int fiveHundred, int twoHundred, int oneHundred)
    {
        long balance = 2000*twoThousand + 500*fiveHundred + 200*twoHundred + 100*oneHundred;
        return balance;
    }



    public static void startAtm() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Your Role : \n 1. Admin \n 2. User \n 3. Exit");
            int roleChoice = s.nextInt();
//            for choice 1
            if (roleChoice == 1) {
                ATMMachine.adminEntry();
            }
//            for choice 2
            else if (roleChoice == 2) {
                ATMMachine.userEntry();

            }
            else if(roleChoice==3)
            {
                System.exit(1);
            }
            else {
                System.out.println("Enter correct option...");
            }

        }

    }

    private static void adminEntry() {
        adminPassEnter:
        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter the Admin name: ");
            String adminName = s.nextLine();
            System.out.print("Enter the Password: ");
            String password = s.nextLine();
            if (ATMMachine.checkAdmin(adminName, password)) {
                System.out.println("Access Granted");
                while (true) {
                    System.out.println("Enter the operation to do... \n 1. Add User \n 2. Delete User \n 3. Deposit Cash in ATM \n 4. Show All Transaction History\n 5. Logout");
                    int operationChoice = Integer.parseInt(s.nextLine());
                    if (operationChoice == 1)
                    {
                        AdminActions.addUser(s);
                    }
                    else if (operationChoice == 2)
                    {
                        AdminActions.deleteUser(s);
                    }
                    else if (operationChoice == 3)
                    {
//                                need to add more here in future(for now this is it)
                        AdminActions.depositMoney(s);
                    }

                    // view all transactions must be added here
                    else if (operationChoice == 4)
                    {

                        AdminActions.viewTransactions();
                    }

//                            more options need to be added here
                    else if (operationChoice == 5)
                    {
                        System.out.println("Exitting....");
                        break adminPassEnter;
                    }
                    else
                    {
                        System.out.println("Enter the correct option");
                    }
                }
            }
            else
            {
                System.out.println("The entered credentials are wrong..\n Enter again.");
            }
        }

    }


    private static void userEntry()
    {
        Scanner s = new Scanner(System.in);
        if (!ATMMachine.getAvailableUsers().isEmpty()) {
            userEnter:
            while (true) {
                System.out.print("Enter the User name: ");
                String userName = s.nextLine();
                System.out.print("Enter the Pin (default is 111111) : ");
                int pinNo = Integer.parseInt(s.nextLine());

                if (ATMMachine.checkUser(userName, pinNo)) {
                    User currentUser = ATMMachine.getUser(userName);
                    System.out.println("User Login Success..");
                    while (true) {
                        System.out.println("Enter the Operation to do..");
                        System.out.println(" 1. Change Pin \n 2. Check Balance \n 3. Withdraw Cash \n 4. Deposit Cash \n 5. Show History \n 6. Logout");
                        int operationChoice = Integer.parseInt(s.nextLine());
                        if (operationChoice == 1)
                        {
                            UserActions.changePin(s,currentUser);
                        }
                        else if (operationChoice == 2)
                        {
                            System.out.println("Your current balance is " + currentUser.getBalance());
                        }
                        else if (operationChoice == 3)
                        {
                            UserActions.withdrawCash(s,currentUser);

                        }
                        else if (operationChoice == 4)
                        {

                            UserActions.depositCash(s,currentUser);
                        }
                        else if (operationChoice == 5)
                        {
                            UserActions.viewTransactions(currentUser);
                        }
                        else if (operationChoice == 6)
                        {
                            System.out.println("Exittingg...");
                            break userEnter;
                        }
//                        more operations need to be added here
                        else
                        {
                            System.out.println("Enter the correct option");
                        }

                    }
                }
                else
                {
                    System.out.println("The entered credentials are wrong...Try Again");
                }
            }
        }
        else
        {
            System.out.println("No User found...Try contacting the Admin");
        }
    }

// External methods for various purposes

        private static boolean checkAdmin(String name, String pass)
        {
            if(Admin.getAdminName().equals(name) && Admin.getPassword().equals(pass))
            {
                return true;
            }
            return false;
        }

        private static boolean checkUser(String uname, int pin)
        {
            ArrayList<User> usersAvailable = ATMMachine.getAvailableUsers();
            for(User individualUser:usersAvailable)
            {
                if (individualUser.getUserName().equals(uname) && individualUser.getPin() == pin)
                {
                    return true;
                }
            }
            return false;
        }
        private static User getUser(String uname)
        {
            ArrayList<User> usersAvailable =ATMMachine.getAvailableUsers();
            for(User individualUser:usersAvailable)
            {
                if (individualUser.getUserName().equals(uname))
                {
                    return individualUser;
                }
            }
            return null;
        }


}