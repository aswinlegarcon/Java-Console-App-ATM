package ATM;

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
                        operationChoiceLoop: while (true)
                        {
                        System.out.println("Enter the operation to do... \n 1. Add User \n 2. Logout");
                        int operationChoice = s.nextInt();
                            if(operationChoice==1)
                            {
                                s.nextLine();
                                System.out.print("Enter the User's Name to Add :");
                                String userName = s.nextLine();
                                Admin.addUser(userName);
                                System.out.println("User Added Successfully..");
                                continue;
                            }
//                            more options need to be added here
                            else if(operationChoice==2)
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
                        while (true)
                        {
                            System.out.println("User Login Success..\n Enter the Operation to do");
                            System.out.println("1. Change Pin \n 2. Exit");
                            int operationChoice = s.nextInt();
                            if(operationChoice==1)
                            {
                                System.out.print("Enter the Pin to change :");
                                int pin = s.nextInt();
                                currentUser.changePin(pin);
                                System.out.println("Pin changed");
                                continue ;
                            }
                            else if(operationChoice==2)
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
                }
            }
            else
            {
                System.out.println("Enter correct option...");
            }

        }

    }
}
