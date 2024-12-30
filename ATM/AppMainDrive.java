package ATM;

public class AppMainDrive {
    public static void main(String[] args) throws CloneNotSupportedException {
        ATM.AtmActions.startAtm(ATMMachine.getAvailableAdmins());
    }
}