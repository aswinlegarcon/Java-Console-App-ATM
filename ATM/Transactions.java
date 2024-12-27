package ATM;

public class Transactions {
    private Object user;
    private String type;
    private double amount;

    public Transactions(String type , double amount, Object user)
    {
        this.type = type;
        this.amount = amount;
        this.user = user;
    }

    public String getType()
    {
        return type;
    }

    public String getUser()
    {
        return user.toString();
    }

    public double getAmount()
    {
        return amount;
    }
}
