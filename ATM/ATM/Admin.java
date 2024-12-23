package ATM;

import java.util.ArrayList;

public class Admin {


    private static ArrayList<String> atmTransactionHistory = new ArrayList<>();
    //        private static ArrayList<ATMMachine> atmMachines = new ArrayList<>();    for future usage when creating ATM objects
    private static String adminName = "admin";
    private static String password = "admin@123";

    // for admins
    public static String getAdminName()
    {
        return adminName;
    }

    public static String getPassword()
    {
        return password;
    }

    public static void addATMTransactionHistory(String transaction)
    {
        Admin.atmTransactionHistory.add(transaction);
    }

    public static ArrayList<String> getATMTransactionHistory()
    {
        return Admin.atmTransactionHistory;
    }

}
