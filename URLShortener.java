import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class URLShortener {

    static HashMap<String, String> map = new HashMap<>();
    static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static Random rand = new Random();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- URL SHORTENER ---");
            System.out.println("1. Shorten URL");
            System.out.println("2. Retrieve Original URL");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter long URL: ");
                    String longUrl = sc.nextLine();
                    String shortUrl = generateShortURL();
                    map.put(shortUrl, longUrl);
                    System.out.println("Short URL: " + shortUrl);
                    break;

                case 2:
                    System.out.print("Enter short URL: ");
                    String key = sc.nextLine();
                    if (map.containsKey(key)) {
                        System.out.println("Original URL: " + map.get(key));
                    } else {
                        System.out.println("URL not found.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);

        sc.close();
    }

    static String generateShortURL() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }
}