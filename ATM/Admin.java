package ATM;
import java.util.ArrayList;
public class Admin
{

        private static ArrayList<User> users = new ArrayList<>();
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

//        for users by admins
        public static ArrayList<User> getAvailableUsers()
        {
            return users;
        }

        public static void addUser(String name , double balance)
        {
            users.add(new User(name,111111,balance)); //always default pin is 111111
        }

        public static void addUser(String name)
        {
            users.add(new User(name,111111,1000));
        }

        public static void deleteUser(int index)
        {
            users.remove(index);
        }

        public static void addATMTransactionHistory(String transaction)
        {
            Admin.atmTransactionHistory.add(transaction);
        }

        public static ArrayList<String> getATMTransactionHistory()
        {
            return Admin.atmTransactionHistory;
        }
//for future
//        for ATM by admins
//        public static void addATM()


    }


