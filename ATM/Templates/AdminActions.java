package ATM.Templates;

import ATM.Admin;

// Extending Action interface to get common method and declaring specific methods here
public interface AdminActions extends Actions{

    public void addUser();
    public void deleteUser();
}
