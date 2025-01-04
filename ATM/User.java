package ATM;

import java.util.ArrayList;

public class User extends Account
{
    private double accBalance;

    public User(String userName, String password, double accBalance)
    {
        super(userName,password);
        this.accBalance = accBalance;
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
//    @Override
//    public String toString()
//    {
//        return this.userName;
//    }

}