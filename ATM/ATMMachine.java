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
    private static ArrayList<Account> accounts = new ArrayList<>(); // To store both admin and user accounts
    //    To store notes available in ATM - for now initialising name alone with count as 0
    private static Notes<Note> notesInAtm = new Notes<>();
    static {
        notesInAtm.add(new TwoThousand("2000",0));
        notesInAtm.add(new FiveHundred("500",0));
        notesInAtm.add(new TwoHundred("200",0));
        notesInAtm.add(new OneHundred("100",0));
    }
    //private static ArrayList<Note> notesInAtm = new ArrayList<Note>(Arrays.asList(new TwoThousand("2000", 0), new FiveHundred("500", 0), new TwoHundred("200", 0),new OneHundred("100", 0)));
    private static double balance; // ATM's Balance

    public static ArrayList<Account> getAvailableAccounts() {
        return accounts;
    }

    public static double getBalance() {
        return ATMMachine.balance;
    }

//    public static ArrayList<Note> getNotesInAtm() {
//        return notesInAtm;
//    }


    public static Notes<Note> getNotesInAtm() {
        return notesInAtm;
    }


    public static void setBalance(double balance) {
        ATMMachine.balance = balance;
    }

//    public static void setNotesInAtm(ArrayList<Note> notes) {
//        ATMMachine.notesInAtm = notes;
//    }


}