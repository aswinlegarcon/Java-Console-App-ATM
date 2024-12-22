package ATM;



/*For now this is  static
* in future multiple machines can be
* added here by creating objects*/

public class ATMMachine
{
    private static double balance;

    public static double getBalance()
    {
        return ATMMachine.balance;
    }

    public static void setBalance(double balance)
    {
        ATMMachine.balance = balance;
    }
}
