package ATM;

public class AppMainDrive {
    public static void main(String[] args) throws CloneNotSupportedException {
        AtmActions.startAtm(ATMMachine.getAvailableAdmins());
    }
}
