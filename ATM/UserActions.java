package ATM;

import ATM.Notes.Notes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserActions
{
    // function to check user and return objects for verification
    public static Account checkUser()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the User name: ");
        String uname = s.nextLine();
        System.out.print("Enter the Password : ");
        String password = s.nextLine();
        ArrayList<Account> usersAvailable = ATMMachine.getAvailableAccounts();
        for(Account individualUser:usersAvailable) // loop user arraylist for checking the availability of user
        {
            if(individualUser instanceof User)
            {
                if (individualUser.getUserName().equals(uname) && individualUser.getPassword().equals(password))// if username and password matches - return user object
                {
                    return individualUser;
                }
                else if(individualUser.getUserName().equals(uname) && !individualUser.getPassword().equals(password))// if username matches but pin does not match - return new user with null as username
                {
                    return new User(null,null,0);
                }
            }

        }
        return null;// if no users found in the array list then return null
    }

    //    function to change the pin
    public static void changePin(Scanner s,User currentUser)
    {
        System.out.print("Enter the current pin : ");// get the current pin
        String currentPassword = s.nextLine();
        if(!currentUser.getPassword().equals(currentPassword)) // if current pin doesnot match the user's pin
        {
            System.out.println("The current pin is wrong..Try again"); // print error statement
            return;
        }
        System.out.print("Enter the Pin to change :"); // If the current pin is right then get the new pin
        String password = s.nextLine();
        currentUser.setPassword(password); // set the password
        System.out.println("Password changed"); // print password has been changed
    }

    //    Helper function for user-withdraw, Calculate notes and give notes to user and reduce amount every time
    private static double performWithdraw(double useramount, Notes note, ArrayList<String> denominationsList) {
        long count = (long) (useramount/Integer.parseInt(note.getNote())); // calculate how much note needed for the withdraw amount
        if(Long.parseLong(note.getNote()) <= useramount && note.getCount() > 0) // the withdraw amount should be greater than note and the count of note must be greater than 0
        {
            if(count <= note.getCount()) // if the count less than available count of notes in ATM
            {
                useramount = useramount - (count * Integer.parseInt(note.getNote())); // reduce the useramount by multiplying the note and count
                note.setCount((note.getCount() - count));// set the note's count by reducing the given count of note to user
                denominationsList.add("You got " + note.getNote() + " count " + count);// add a string with how much notes has been supplied
            }
            else
            {
                useramount = useramount - (note.getCount() * Integer.parseInt(note.getNote()));   // reduce the useramount by multiplying the note and available count of notes in ATM
                denominationsList.add("You got " + note.getNote() + " count " + note.getCount()); // add a string with how much notes has been supplied (the count of note will be set to 0 after this so we are adding string here itself)
                note.setCount((0));// setting the note count to 0 as all notes have been supplied
            }

            return useramount; // return user amount after calculation
        }
        return useramount; // false if condition - return the amount as it is
    }

    public static void withdrawCash (Scanner s, User currentUser) throws CloneNotSupportedException {
        ArrayList<String> notesTransaction = new ArrayList<>(); // String array to store the notes that has been supplied to user
        ArrayList<Notes> notesDuplicate = new ArrayList<>(); // A duplicate notes arraylist to clone and store same notes object in order to unchange the original list everytime

        System.out.print("Enter the amount to withdraw :"); // asking amount to withdraw
        long amountToWithdraw = Long.parseLong(s.nextLine());
        if(amountToWithdraw<=currentUser.getBalance()) // amount to withdraw must be lesser than user's balance
        {
            if(amountToWithdraw<=ATMMachine.getBalance()) //  amount to withdraw must be lesser than ATM's balance
            {
                long finalAmountForCalculations = amountToWithdraw; // storing the original amount in another variable as we are going to change the original amount
                for(Notes notesInAtm:ATMMachine.getNotesInAtm()) // loop to cloning the objects
                {
                    notesDuplicate.add((Notes)notesInAtm.clone());// clone the object and add in duplicate arraylist of notes
                }
                if(amountToWithdraw!=0) // if amount not zero
                {
                    for(Notes notesInDuplicate:notesDuplicate) { // loop the duplicate note objects
                        String noteType = notesInDuplicate.getNote(); // store the incoming note object's name in a variable
                        switch (noteType) {
                            case "2000", "500", "200", "100": // if any of these four then the below has to execute
                                amountToWithdraw = (long) UserActions.performWithdraw(amountToWithdraw, notesInDuplicate, notesTransaction);// calling helper method and it returns the amount and we are reassigning the amountToWithdraw
                                break;
                        }
                    } // loop of objects end

                    if(amountToWithdraw == 0)  // if amount comes to zero
                    {
                        ATMMachine.setNotesInAtm(notesDuplicate);// set duplicate notes list to original
                        currentUser.setBalance(currentUser.getBalance()-finalAmountForCalculations); // set current user's balance
                        for(String notesGiven: notesTransaction) // loop to display supplied notes
                        {
                            System.out.println("*"+notesGiven);
                        }
//                        for(Notes notes:ATMMachine.getNotesInAtm()) // loop to display notes available after supply for checking purpose
//                        {
//                            System.out.println("Note: "+ notes.getNote()+" Count : "+notes.getCount() );
//                        }
                        currentUser.getAvailableTransactions().add(new Transactions("Withdrawed",finalAmountForCalculations,currentUser.getUserName()));// adding transaction as objects
                        return;
                    }
                    else // after all the loop if amount not comes to zero then print this
                    {
                        System.out.println("There are no denominations...Enter another amount");
                        return;
                    }

                }
            }
//            if amount not sufficient in ATM
            System.out.println("Insufficient amount in ATM ....");
            return;
        }
//        if amount not sufficient in user's account
        System.out.println("Insufficient amount in your Account ...");
    }



    //function to deposit the cash in ATM and their account
    public static void depositCash(Scanner s,User currentUser)
    {
        System.out.print("Enter the Amount :");//asking the amount
        long firstAmountToDeposit = Long.parseLong(s.nextLine());
//        asking the notes one by one
        System.out.println("Enter the no of amount of notes to deposit as User :");
        System.out.print("Enter the no of 2000 notes : ");
        int twoThousandNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 500 notes : ");
        int fiveHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 200 notes : ");
        int twoHundredNotes = Integer.parseInt(s.nextLine());
        System.out.print("Enter the no of 100 notes : ");
        int oneHundredNotes = Integer.parseInt(s.nextLine());
//      calculating the notes entered as amount
        long amountToDeposit = AtmActions.getDepositedBalance(twoThousandNotes, fiveHundredNotes, twoHundredNotes, oneHundredNotes);
        if(firstAmountToDeposit==amountToDeposit)// if entered amount and notes entered(calculated as amount) equals
        {
            // get the notes arraylist
            for(Notes note:ATMMachine.getNotesInAtm()) // loop the notes objects
            {
                String noteType = note.getNote();// get the object name
                switch (noteType) {
                    case "2000": // if 2000 then set count of two thousand
                        note.setCount(note.getCount()+twoThousandNotes);
                        break;
                    case "500": // if 500 then set count of five hundred
                        note.setCount(note.getCount()+fiveHundredNotes);
                        break;
                    case "200": // if 200 then set count of two hundred
                        note.setCount(note.getCount()+twoHundredNotes);
                        break;
                    case "100": // if 100 then set count of one hundred
                        note.setCount(note.getCount()+oneHundredNotes);
                        break;
                }

            }
            currentUser.getAvailableTransactions().add(new Transactions("Deposited",amountToDeposit,currentUser.getUserName())); // adding transaction as objects
            for(Notes notes:ATMMachine.getNotesInAtm())//loop for printing notes available after deposit
            {
                System.out.println("Note: "+ notes.getNote()+" Count : "+notes.getCount());
            }
            double currentBalance = currentUser.getBalance() + amountToDeposit;
            double balanceInAtm = ATMMachine.getBalance() + amountToDeposit;
            currentUser.setBalance(currentBalance); // set user's balance
            ATMMachine.setBalance(balanceInAtm); // set ATM's balance
            System.out.println("The deposit of Rs." + amountToDeposit + " is added successfully");
            System.out.println("Your current balance is " + currentUser.getBalance());
            System.out.println("ATM balance "+ ATMMachine.getBalance());
        }
        else// if entered amount and calculated amount does not match
        {
            System.out.println("The amount you entered in notes does not match..Enter correct amount");
        }

    }

    //    function to view all the transaction done by the user
    public static void viewTransactions(User currentUser)
    {
        ArrayList<Transactions> userHistory = currentUser.getAvailableTransactions(); // get the transaction
        if (!userHistory.isEmpty()) // if not empty then print the transaction
        {
            System.out.println("The Transactions you have made...");
            for (Transactions history : userHistory) {
                    System.out.println("You "+ history.getType() + " Rs "+history.getAmount());
            }
//            if transaction is empty then print no transactions
        }
        else
        {
            System.out.println("There are no Transactions..");
        }
    }

}