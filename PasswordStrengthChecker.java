import java.util.Scanner;

public class PasswordStrengthChecker {

    public static int calculateStrength(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;

        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++; 
        if (password.matches(".*\\d.*")) score++;
        if (password.matches(".*[@#$%^&+=!].*")) score++;

        return score;
    }

    public static String getStrengthLevel(int score) {
        if (score <= 2) return "Very Weak";
        else if (score == 3) return "Weak";
        else if (score == 4) return "Moderate";
        else if (score == 5) return "Strong";
        else return "Very Strong";
    }

    public static void suggestImprovements(String password) {
        if (password.length() < 8)
            System.out.println("- Use at least 8 characters");

        if (!password.matches(".*[A-Z].*"))
            System.out.println("- Add uppercase letters");

        if (!password.matches(".*[a-z].*"))
            System.out.println("- Add lowercase letters");

        if (!password.matches(".*\\d.*"))
            System.out.println("- Include numbers");

        if (!password.matches(".*[@#$%^&+=!].*"))
            System.out.println("- Add special characters (@#$%^&+=!)");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        int score = calculateStrength(password);
        String strength = getStrengthLevel(score);

        System.out.println("Password Strength: " + strength);

        if (score < 5) {
            System.out.println("Suggestions to improve:");
            suggestImprovements(password);
        }

        sc.close();
    }
}