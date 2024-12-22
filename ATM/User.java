package ATM;

import java.util.ArrayList;

public class User
{

        private String userName;
        private int pin;
        private double accBalance;
        private ArrayList<String> userHistory = new ArrayList<String>();


        public User(String userName, int pin, double accBalance)
        {
            this.userName = userName;
            this.pin = pin;
            this.accBalance = accBalance;
        }
        public String getUserName()
        {
            return userName;
        }

        public int getPin()
        {
            return pin;
        }

        public void changePin(int pin)
        {
            this.pin = pin;
        }

        public double getBalance()
        {
            return accBalance;
        }
        public void setBalance(double balance)
        {
            this.accBalance = balance;
        }

        public void addUserTransactionHistory(String transaction)
        {
            this.userHistory.add(transaction);
        }

        public ArrayList<String> getUserTransactionHistory()
        {
            return this.userHistory;
        }




}
