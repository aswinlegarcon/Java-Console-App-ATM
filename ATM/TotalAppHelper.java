package ATM;

import java.util.ArrayList;

public abstract class TotalAppHelper
{
    public static boolean checkAdmin(String name, String pass)
    {
        if(Admin.getAdminName().equals(name) && Admin.getPassword().equals(pass))
        {
            return true;
        }
        return false;
    }

    public static boolean checkUser(String uname, int pin)
    {
        ArrayList<User> usersAvailable = Admin.getAvailableUsers();
        for(User individualUser:usersAvailable)
        {
            if (individualUser.getUserName().equals(uname) && individualUser.getPin() == pin)
            {
                return true;
            }
        }
        return false;
    }
    public static User getUser(String uname)
    {
        ArrayList<User> usersAvailable = Admin.getAvailableUsers();
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
