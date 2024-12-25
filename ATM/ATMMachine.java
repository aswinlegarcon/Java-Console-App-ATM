package ATM;



/*For now this is  static
 * in future multiple machines can be
 * added here by creating objects*/

import ATM.Notes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ATMMachine {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Notes> notesInAtm = new ArrayList<Notes>(); //(Arrays.asList(new TwoThousand("2000", 0), new FiveHundred("500", 0), new TwoHundred("200", 0),new OneHundred("100", 0)));
    private static double balance;

    public static ArrayList<User> getAvailableUsers() {
        return ATMMachine.users;
    }

    public static ArrayList<Admin> getAvailableAdmins() {
        return ATMMachine.admins;
    }

    public static double getBalance() {
        return ATMMachine.balance;
    }

    public static ArrayList<Notes> getNotesInAtm() {
        return notesInAtm;
    }

    public static void setBalance(int twoThousand, int fiveHundred, int twoHundred, int oneHundred) {
        ATMMachine.balance = AtmActions.getBalance(notesInAtm, twoThousand, fiveHundred, twoHundred, oneHundred);
    }

    public static void setBalance(double balance) {
        ATMMachine.balance = balance;
    }

    public static void setNotesInAtm(ArrayList<Notes> notes) {
        ATMMachine.notesInAtm = notes;
    }


}





//    public static void updateCash(String whatToDo, ArrayList<Notes> notesToAdded , int twoThousandNotes, int fiveHundredNotes, int twoHundredNotes, int oneHundredNotes)
//    {
//        if(whatToDo.equals("Deposit"))
//        {
//            for(Notes notesToadd:notesToAdded)
//            {
//                if(notesToadd.getNote().equals("2000"))
//                {
//                    notesToadd.setCount(notesToadd.getCount() + twoThousandNotes);
//                }
//                else if(notesToadd.getNote().equals("500"))
//                {
//                    notesToadd.setCount(notesToadd.getCount() + fiveHundredNotes);
//                }
//                else if(notesToadd.getNote().equals("200"))
//                {
//                    notesToadd.setCount(notesToadd.getCount() + twoHundredNotes);
//                }
//                else if(notesToadd.getNote().equals("100"))
//                {
//                    notesToadd.setCount(notesToadd.getCount() + oneHundredNotes);
//                }
//            }
//        }
//        else if(whatToDo.equals("Withdraw"))
//        {
//            for(Notes notesToadd:notesToAdded)
//            {
//                if(notesToadd.getNote().equals("2000"))
//                {
//                    notesToadd.setCount(notesToadd.getCount() - twoThousandNotes);
//                }
//                else if(notesToadd.getNote().equals("500"))
//                {
//                    notesToadd.setCount(notesToadd.getCount() - fiveHundredNotes);
//                }
//                else if(notesToadd.getNote().equals("200"))
//                {
//                    notesToadd.setCount(notesToadd.getCount() - twoHundredNotes);
//                }
//                else if(notesToadd.getNote().equals("100"))
//                {
//                    notesToadd.setCount(notesToadd.getCount() - oneHundredNotes);
//                }
//            }
//        }
//
//    }
// External methods for various purposes
