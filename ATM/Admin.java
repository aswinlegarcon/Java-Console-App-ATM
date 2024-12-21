package ATM;
import java.util.ArrayList;
public class Admin
{

        private static ArrayList<User> users = new ArrayList<>();
        private static String adminName = "admin";
        private static String password = "admin@123";


        public static String getAdminName()
        {
            return adminName;
        }

        public static String getPassword()
        {
            return password;
        }

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


    }


