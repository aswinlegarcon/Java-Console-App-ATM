package ATM;

import java.util.ArrayList;
import java.util.Scanner;

public class AppTestDrive {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter Your Role : \n 1. Admin \n 2. User \n 3. Exit");
            int roleChoice = s.nextInt();
//            for choice 1
            if(roleChoice==1)
            {
                adminPassEnter: while (true)
                {
                    s.nextLine();
                    System.out.print("Enter the Admin name: ");
                    String adminName = s.nextLine();
                    System.out.print("Enter the Password: ");
                    String password = s.nextLine();
                    if(TotalAppHelper.checkAdmin(adminName,password))
                    {
                        System.out.println("Access Granted");
                        while (true)
                        {
                        System.out.println("Enter the operation to do... \n 1. Add User \n 2. Delete User \n 3. Deposit Cash in ATM \n 4. Show All Transaction History\n 5. Logout");
                        int operationChoice = s.nextInt();
                            if(operationChoice==1)
                            {
                                s.nextLine();
                                System.out.print("Enter the User's Name to Add :");
                                String userName = s.nextLine();
                                Admin.addUser(userName);
                                System.out.println("User Added Successfully..");
                            }
                            else if(operationChoice==2)
                            {
                                ArrayList<User> usersToRemove = Admin.getAvailableUsers();
                                if(!usersToRemove.isEmpty())
                                {
                                    System.out.println("The Available Users are...");
                                    int count = 0;
                                    for(User temp:usersToRemove)
                                    {
                                        System.out.println(count + " - " + temp.getUserName());
                                        count++;
                                    }
                                    System.out.print("Enter the Id no of the User to remove :");
                                    int removeChoice = s.nextInt();
                                    Admin.deleteUser(removeChoice);
                                    System.out.println("User Successfully Removed...");
                                }
                                else
                                {
                                    System.out.println("No users available..Add user and try again..");
                                }
                            }
                            else if(operationChoice==3)
                            {
//                                need to add more here in future(for now this is it)
                                System.out.print("Enter the amount to deposit in ATM :");
                                long amountToBeAdded = s.nextLong();
                                double totalBalance = ATMMachine.getBalance() + amountToBeAdded;
                                ATMMachine.setBalance(totalBalance);
                                Admin.addATMTransactionHistory("Admin has deposited Rs."+amountToBeAdded+" --- ATM Balance : "+ ATMMachine.getBalance());
                                System.out.println("The amount of Rs."+amountToBeAdded+" is added successfully");
                                System.out.println("The current balance in ATM is "+ATMMachine.getBalance());
                            }

                                // view all transactions must be added here
                            else if(operationChoice==4)
                            {

                                ArrayList<String> atmHistory = Admin.getATMTransactionHistory();
                                if(!atmHistory.isEmpty())
                                {
                                    System.out.println("Transactions made by Users...\n");
                                    for(String history:atmHistory)
                                    {
                                        System.out.println(history);
                                    }
                                }
                                else
                                {
                                    System.out.println("There are no Transactions..");
                                }
                            }

//                            more options need to be added here
                            else if(operationChoice==5)
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
//            for choice 2
            else if(roleChoice==2)
            {
                if(!Admin.getAvailableUsers().isEmpty())
                {
                    userEnter: while (true)
                    {
                        s.nextLine();
                        System.out.print("Enter the User name: ");
                        String userName = s.nextLine();
                        System.out.print("Enter the Pin (default is 111111) : ");
                        int pinNo = s.nextInt();

                        if(TotalAppHelper.checkUser(userName,pinNo))
                        {
                            User currentUser = TotalAppHelper.getUser(userName);
                            System.out.println("User Login Success..");
                            while (true)
                            {
                                System.out.println("Enter the Operation to do..");
                                System.out.println(" 1. Change Pin \n 2. Check Balance \n 3. Withdraw Cash \n 4. Deposit Cash \n 5. Show History \n 6. Logout");
                                int operationChoice = s.nextInt();
                                if(operationChoice==1)
                                {
                                    System.out.print("Enter the Pin to change :");
                                    int pin = s.nextInt();
                                    currentUser.changePin(pin);
                                    System.out.println("Pin changed");
                                }
                                else if(operationChoice==2)
                                {
                                    System.out.println("Your current balance is "+currentUser.getBalance());
                                }
                                else if(operationChoice==3)
                                {
                                    System.out.print("Enter the amount to withdraw : ");
                                    long amountToWithdraw = s.nextLong();
                                    if(amountToWithdraw <= ATMMachine.getBalance())
                                    {
                                        if(amountToWithdraw <= currentUser.getBalance())
                                        {
                                            System.out.println("Withdrawl amount of Rs."+amountToWithdraw+ " is successful");
                                            double currentBalanceInAcc = currentUser.getBalance() - amountToWithdraw;
                                            double currentBalanceInATM = ATMMachine.getBalance() - amountToWithdraw;
                                            currentUser.setBalance(currentBalanceInAcc);
                                            ATMMachine.setBalance(currentBalanceInATM);
                                            currentUser.addUserTransactionHistory("Your account is debited with Rs."+amountToWithdraw+ "--- Balance :"+currentUser.getBalance());
                                            Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is debited with Rs."+amountToWithdraw+ "--- User Balance : "+currentUser.getBalance()+ "--- ATM Balance : "+ ATMMachine.getBalance());
                                            System.out.println("Your current balance is "+currentUser.getBalance());
                                        }
                                        else
                                        {
                                            System.out.println("The amount in your account is not sufficent to withdraw");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Sorryy....There is no amount in the ATM. Come back Later");

                                    }

                                }
                                else if(operationChoice==4)
                                {
                                    System.out.print("Enter the amount to deposit : ");
                                    long amountToDeposit = s.nextLong();
                                    double currentBalance = currentUser.getBalance() + amountToDeposit;
                                    double balanceInAtm = ATMMachine.getBalance() + amountToDeposit;
                                    currentUser.setBalance(currentBalance);
                                    ATMMachine.setBalance(balanceInAtm);
                                    currentUser.addUserTransactionHistory("Your account is credited with Rs."+amountToDeposit+ "--- Balance :"+currentUser.getBalance());
                                    Admin.addATMTransactionHistory(currentUser.getUserName() + "'s account is credited with Rs."+amountToDeposit+ "--- User Balance : "+currentUser.getBalance()+ "--- ATM Balance : "+ ATMMachine.getBalance());
                                    System.out.println("The deposit of Rs."+amountToDeposit+" is added successfully");
                                    System.out.println("Your current balance is "+currentUser.getBalance());
                                }
                                else if(operationChoice==5)
                                {

                                    ArrayList<String> userHistory = currentUser.getUserTransactionHistory();
                                    if(!userHistory.isEmpty())
                                    {
                                        System.out.println("The Transactions you have made...\n");
                                        for(String history:userHistory)
                                        {
                                            System.out.println(history);
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("There are no Transactions..");
                                    }

                                }
                                else if(operationChoice==6)
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


            else
            {
                System.out.println("Enter correct option...");
            }

        }

    }
}
