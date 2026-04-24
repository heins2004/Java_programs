import java.util.*;

class CacheEntry<V> {
    V value;
    long expiryTime; // in millis

    CacheEntry(V value, long ttlMillis) {
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

class LRUCache<K, V> extends LinkedHashMap<K, CacheEntry<V>> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // access order ON
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, CacheEntry<V>> eldest) {
        return size() > capacity;
    }

    public void putEntry(K key, V value, long ttlMillis) {
        super.put(key, new CacheEntry<>(value, ttlMillis));
    }

    public V getEntry(K key) {
        CacheEntry<V> entry = super.get(key);

        if (entry == null) return null;

        if (entry.isExpired()) {
            super.remove(key);
            return null;
        }

        return entry.value;
    }

    public void cleanExpired() {
        Iterator<Map.Entry<K, CacheEntry<V>>> it = super.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<K, CacheEntry<V>> e = it.next();
            if (e.getValue().isExpired()) {
                it.remove();
            }
        }
    }

    public void display() {
        cleanExpired();

        if (isEmpty()) {
            System.out.println("Cache is empty.");
            return;
        }

        for (Map.Entry<K, CacheEntry<V>> e : super.entrySet()) {
            long remaining = e.getValue().expiryTime - System.currentTimeMillis();
            System.out.println(
                "Key: " + e.getKey() +
                ", Value: " + e.getValue().value +
                ", Expires in: " + (remaining / 1000) + "s"
            );
        }
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
            System.out.println("\n--- LRU CACHE (WITH TTL) ---");
            System.out.println("1. Put (with expiry seconds)");
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

                    System.out.print("Enter expiry time (seconds): ");
                    long ttl = sc.nextLong();

                    cache.putEntry(key, value, ttl * 1000);
                    System.out.println("Added with expiry!");
                    break;

                case 2:
                    System.out.print("Enter key: ");
                    int k = sc.nextInt();

                    String result = cache.getEntry(k);

                    if (result != null) {
                        System.out.println("Value: " + result);
                    } else {
                        System.out.println("Not found or expired.");
                    }
                    break;

                case 3:
                    cache.display();
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