import java.util.Scanner;
public class PasswordStrengthChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } 
            else if (Character.isLowerCase(ch)) {
                hasLower = true;
            } 
            else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
        }
        if (password.length() >= 8 && hasUpper && hasLower && hasDigit) {
            System.out.println("Strong Password");
        } else {
            System.out.println("Weak Password");
        }
        sc.close();
    }
}