import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class LRUCacheDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter cache capacity: ");
        int cap = sc.nextInt();

        LRUCache<Integer, String> cache = new LRUCache<>(cap);

        int choice;

        do {
            System.out.println("\n--- LRU CACHE ---");
            System.out.println("1. Put (Add)");
            System.out.println("2. Get");
            System.out.println("3. Display Cache");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter key: ");
                    int key = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter value: ");
                    String value = sc.nextLine();

                    cache.put(key, value);
                    System.out.println("Added!");
                    break;

                case 2:
                    System.out.print("Enter key: ");
                    int k = sc.nextInt();

                    if (cache.containsKey(k)) {
                        System.out.println("Value: " + cache.get(k));
                    } else {
                        System.out.println("Key not found.");
                    }
                    break;

                case 3:
                    System.out.println("Cache: " + cache);
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
}