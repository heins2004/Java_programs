import java.util.*;

public class URLShortener {

    static HashMap<String, String> shortToLong = new HashMap<>();
    static HashMap<String, String> longToShort = new HashMap<>();
    static HashMap<String, Integer> clickCount = new HashMap<>();

    static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static Random rand = new Random();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- URL SHORTENER ---");
            System.out.println("1. Shorten URL");
            System.out.println("2. Retrieve Original URL");
            System.out.println("3. View Analytics");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter long URL: ");
                    String longUrl = sc.nextLine();

                    System.out.print("Custom alias? (yes/no): ");
                    String option = sc.nextLine();

                    String shortUrl;

                    if (longToShort.containsKey(longUrl)) {
                        shortUrl = longToShort.get(longUrl);
                        System.out.println("Already shortened: " + shortUrl);
                    } 
                    else {
                        if (option.equalsIgnoreCase("yes")) {
                            System.out.print("Enter custom alias: ");
                            shortUrl = sc.nextLine();

                            if (shortToLong.containsKey(shortUrl)) {
                                System.out.println("Alias already taken!");
                                break;
                            }
                        } 
                        else {
                            shortUrl = generateShortURL();
                            while (shortToLong.containsKey(shortUrl)) {
                                shortUrl = generateShortURL();
                            }
                        }

                        shortToLong.put(shortUrl, longUrl);
                        longToShort.put(longUrl, shortUrl);
                        clickCount.put(shortUrl, 0);

                        System.out.println("Short URL: " + shortUrl);
                    }
                    break;

                case 2:
                    System.out.print("Enter short URL: ");
                    String key = sc.nextLine();

                    if (shortToLong.containsKey(key)) {
                        System.out.println("Original URL: " + shortToLong.get(key));

                        // increase click count
                        clickCount.put(key, clickCount.get(key) + 1);
                    } else {
                        System.out.println("URL not found.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Analytics ---");
                    for (String k : shortToLong.keySet()) {
                        System.out.println(
                            "Short: " + k +
                            " | Clicks: " + clickCount.get(k)
                        );
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

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