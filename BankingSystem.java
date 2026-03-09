class BankAccount {
    private int balance = 1000;
    public synchronized void withdraw(String user, int amount) {
        System.out.println(user + " trying to withdraw " + amount);
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println(user + " withdrawal successful. Balance: " + balance);
        } else {
            System.out.println(user + " cannot withdraw. Insufficient balance: " + balance);
        }
    }
}
class User extends Thread {
    BankAccount account;
    String name;
    int amount;
    User(BankAccount account, String name, int amount) {
        this.account = account;
        this.name = name;
        this.amount = amount;
    }
    public void run() {
        account.withdraw(name, amount);
    }
}
public class BankingSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        User u1 = new User(account, "Alice", 700);
        User u2 = new User(account, "Bob", 700);
        u1.start();
        u2.start();
    }
}