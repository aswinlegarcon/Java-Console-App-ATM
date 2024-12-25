package ATM;

import java.util.ArrayList;

public class Admin {


    private ArrayList<String> atmTransactionHistory = new ArrayList<>();
    //        private static ArrayList<ATMMachine> atmMachines = new ArrayList<>();    for future usage when creating ATM objects
    private  String adminName = "admin";
    private  String password = "admin@123";

    public Admin(String adminName,String password)
    {
        this.adminName = adminName;
        this.password = password;
    }
    // for admins
    public  String getAdminName()
    {
        return adminName;
    }

    public  String getPassword()
    {
        return password;
    }

    public void addATMTransactionHistory(String transaction)
    {
        this.atmTransactionHistory.add(transaction);
    }

    public  ArrayList<String> getATMTransactionHistory()
    {
        return this.atmTransactionHistory;
    }

}