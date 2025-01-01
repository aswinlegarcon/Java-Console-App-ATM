package ATM;

import java.util.ArrayList;

public class User {
    private String userName;
    private int pin;
    private double accBalance;


    public User(String userName, int pin, double accBalance)
    {
        this.setUserName(userName);
        this.setPin(pin);
        this.setBalance(accBalance);
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getPin()
    {
        return pin;
    }
    public void setPin(int pin)
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

//    overriding toString method to display name of user if the object is printed
    @Override
    public String toString()
    {
        return this.userName;
    }

}