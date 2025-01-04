package ATM;

import java.util.ArrayList;

public class Account {
    private String userName;
    private String password;
    private ArrayList<Transactions> transactions = new ArrayList<>();

    public Account(String userName,String password)
    {
        this.userName = userName;
        this.password = password;
    }
    public String getUserName()
    {
        return userName;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public ArrayList<Transactions> getAvailableTransactions() {
        return transactions;
    }
}
