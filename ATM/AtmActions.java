package ATM;

import ATM.Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AtmActions {

    public static void startAtm(ArrayList<Admin> admins) throws CloneNotSupportedException {
        Scanner s = new Scanner(System.in);
        admins.add(new Admin("admin", "admin"));
        while (true) {
            System.out.println("Enter Your Role : \n 1. Admin \n 2. User \n 3. Exit");
            int roleChoice = Integer.parseInt(s.nextLine());
//            for choice 1
            if (roleChoice == 1) {
                Admin currentAdmin = AdminActions.checkAdmin();
                if(currentAdmin==null)
                {
                    System.out.println("No users found..");
                }
                else if(currentAdmin.getAdminName()==null)
                {
                    System.out.println("Invalid credentials...");
                }
                else
                {
                    AtmActions.adminEntry(currentAdmin);
                }

            }
//            for choice 2
            else if (roleChoice == 2)
            {
                User currentUser = UserActions.checkUser();
                if(currentUser==null)
                {
                    System.out.println("No admins found..");
                }
                else if(currentUser.getUserName()==null)
                {
                    System.out.println("Invalid credentials...");
                }
                else
                {
                    AtmActions.userEntry(currentUser);
                }
            }
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
                            return;
                        }
//                        more operations need to be added here
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
                    System.out.println("Enter the operation to do... \n 1. Add User \n 2. Delete User \n 3. Deposit Cash in ATM \n 4. Show All Transaction History\n 5. Logout");
                    int operationChoice = Integer.parseInt(s.nextLine());
                    if (operationChoice == 1)
                    {
                        AdminActions.addUser();
                    }
                    else if (operationChoice == 2)
                    {
                        AdminActions.deleteUser();
                    }
                    else if (operationChoice == 3)
                    {
                        AdminActions.depositMoney(currentAdmin);
                    }

                    // view all transactions must be added here
                    else if (operationChoice == 4)
                    {

                        AdminActions.viewTransactions(currentAdmin);
                    }
                    else if (operationChoice == 5)
                    {
                        System.out.println("Exitting....");
                        return;
                    }
                    else
                    {
                        System.out.println("Enter the correct option");
                    }
                }
            }





    public static double getBalance(ArrayList<Notes> notesInAtm,int twoThousand,int fiveHundred, int twoHundred, int oneHundred)
    {
        double balance = ATMMachine.getBalance();
        for(Notes notes:notesInAtm)
        {
            long twoThousands = 0;
            long fiveHundreds = 0;
            long twoHundreds = 0;
            long oneHundreds = 0;

            if(notes.getNote().equals("2000"))
            {
                notes.setCount(notes.getCount()+twoThousand);
                twoThousands = 2000 * notes.getCount();
            }
            else if(notes.getNote().equals("500"))
            {
                notes.setCount(notes.getCount()+fiveHundred);
                fiveHundreds = 500 * notes.getCount();
            }
            else if(notes.getNote().equals("200"))
            {
                notes.setCount(notes.getCount()+twoHundred);
                twoHundreds = 200 * notes.getCount();
            }
            else if(notes.getNote().equals("100"))
            {
                notes.setCount(notes.getCount()+oneHundred);
                oneHundreds = 100 * notes.getCount();
            }

            balance += twoThousands + fiveHundreds + twoHundreds + oneHundreds;
        }
        return balance;
    }

    public static long getDepositedBalance(int twoThousand,int fiveHundred, int twoHundred, int oneHundred)
    {
        long balance = 2000*twoThousand + 500*fiveHundred + 200*twoHundred + 100*oneHundred;
        return balance;
    }

}
