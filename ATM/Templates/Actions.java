package ATM.Templates;

import ATM.Account;

// Created common interface with common methods
interface Actions {

    public Account login();
    public void depositMoney(Account currentAccount);
    public void viewTransactions(Account currentAccount);

}
