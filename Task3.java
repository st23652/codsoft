import java.util.Scanner;

public class Task3 {
    public static class BankAccount {
        private double balance;

        public BankAccount(double initialValue) {
            this.balance = initialValue;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("deposited: £" + amount);
            } else {
                System.out.println("enter valid amount");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("withdrawn: £" + amount);
            } else if (amount > balance) {
                System.out.println("insufficient balance");
            } else {
                System.out.println("invalid withdrawal amount");
            }
        }
    }

    public static class atm {
        private BankAccount account;
        private Scanner reader;

        public atm(BankAccount account) {
            this.account = account;
            this.reader = new Scanner(System.in);
        }

        public void start() {
            while (true) {
                showMenu();
                int options = reader.nextInt();
                switch (options) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("thank you, have a nice day.");
                        return;
                    default:
                        System.out.println("try again");
                }
            }
        }

        private void showMenu() {
            System.out.println("/nATM menu:");
            System.out.println("1: balance check");
            System.out.println("2: deposit");
            System.out.println("3: withdraw");
            System.out.println("4: exit");
            System.out.print("choose an option: ");
        }

        private void checkBalance() {
            System.out.println("your current balance is: £" + account.getBalance());
        }

        private void deposit() {
            System.out.print("enter amount to deposit: ");
            double amount = reader.nextDouble();
            account.deposit(amount);
        }

        private void withdraw() {
            System.out.print("enter amount to withdraw: ");
            double amount = reader.nextDouble();
            account.withdraw(amount);
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        atm ATM = new atm(account);
        ATM.start();
    }
}
