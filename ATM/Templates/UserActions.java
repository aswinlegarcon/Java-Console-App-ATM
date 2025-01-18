package ATM.Templates;

import ATM.Account;
import ATM.User;

// Extending Action interface to get common method and declaring specific methods here
public interface UserActions extends Actions
{
    public void changePin(User currentUser);
    public void withdrawCash(User currentUser) throws CloneNotSupportedException;
}
