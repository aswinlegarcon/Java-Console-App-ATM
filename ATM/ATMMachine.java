package ATM;

import ATM.ListOfNotes.FiveHundred;
import ATM.ListOfNotes.OneHundred;
import ATM.ListOfNotes.TwoHundred;
import ATM.ListOfNotes.TwoThousand;
import ATM.Notes.*;

import java.util.ArrayList;
import java.util.Arrays;

/*For now this is  static
 * in future multiple machines can be
 * added here by creating objects*/

public class ATMMachine {
//    private static ArrayList<User> users = new ArrayList<>(); // To store list of users
//    private static ArrayList<Admin> admins = new ArrayList<>(); // To store list of admins
    private static ArrayList<Account> accounts = new ArrayList<>(); // To store both admin and user accounts
    //    To store notes available in ATM - for now initialising name alone with count as 0
    private static ArrayList<Notes> notesInAtm = new ArrayList<Notes>(Arrays.asList(new TwoThousand("2000", 0), new FiveHundred("500", 0), new TwoHundred("200", 0),new OneHundred("100", 0)));
    private static double balance; // ATM's Balance

    public static ArrayList<Account> getAvailableAccounts() {
        return accounts;
    }

    public static double getBalance() {
        return ATMMachine.balance;
    }

    public static ArrayList<Notes> getNotesInAtm() {
        return notesInAtm;
    }

    public static void setBalance(double balance) {
        ATMMachine.balance = balance;
    }

    public static void setNotesInAtm(ArrayList<Notes> notes) {
        ATMMachine.notesInAtm = notes;
    }


}