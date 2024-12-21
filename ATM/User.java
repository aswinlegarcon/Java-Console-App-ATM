package ATM;

public class User
{

        private String userName;
        private int pin;
        private double accBalance;

        public String getUserName()
        {
            return userName;
        }

        public int getPin()
        {
            return pin;
        }

        public User(String userName, int pin, double accBalance)
        {
            this.userName = userName;
            this.pin = pin;
            this.accBalance = accBalance;
        }

        public void changePin(int pin)
        {
            this.pin = pin;
        }


}
