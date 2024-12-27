package ATM;

import java.util.ArrayList;

public class Admin {


    //        private static ArrayList<ATMMachine> atmMachines = new ArrayList<>();    for future usage when creating ATM objects
    private  String adminName;
    private  String password;

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


    @Override
    public String toString()
    {
        return this.adminName;
    }

}