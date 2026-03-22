import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String correctUsername = "admin";
        String correctPassword = "1234";

        int attempts = 3;
        boolean success = false;

        while (attempts > 0) {

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("Login Successful!");
                success = true;
                break;
            } else {
                attempts--;
                System.out.println("Invalid credentials! Attempts left: " + attempts);
            }
        }

        if (!success) {
            System.out.println("Account locked. Too many failed attempts.");
        }

        sc.close();
    }
}
