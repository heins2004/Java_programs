import java.io.*;
import java.util.Scanner;
public class FileOperations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- File Operations Menu ---\n1. Create/Add a file\n2. Write data to a file\n3. Read data from a file\n4. Append data to a file\n5. Delete a file\n6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createFile(sc);
                    break;
                case 2:
                    writeFile(sc);
                    break;
                case 3:
                    readFile(sc);
                    break;
                case 4:
                    appendFile(sc);
                    break;
                case 5:
                    deleteFile(sc);
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);

        sc.close();
    }

    public static void createFile(Scanner sc) {
        try {
            System.out.print("Enter file name: ");
            String fileName = sc.nextLine();

            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public static void writeFile(Scanner sc) {
        try {
            System.out.print("Enter file name: ");
            String fileName = sc.nextLine();

            FileWriter writer = new FileWriter(fileName);
            System.out.println("Enter data to write:");
            String data = sc.nextLine();

            writer.write(data);
            writer.close();

            System.out.println("Data written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void readFile(Scanner sc) {
        try {
            System.out.print("Enter file name: ");
            String fileName = sc.nextLine();

            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);

            String line;
            System.out.println("File contents:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void appendFile(Scanner sc) {
        try {
            System.out.print("Enter file name: ");
            String fileName = sc.nextLine();

            FileWriter writer = new FileWriter(fileName, true);
            System.out.println("Enter data to append:");
            String data = sc.nextLine();

            writer.write("\n" + data);
            writer.close();

            System.out.println("Data appended successfully.");
        } catch (IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }
    }

    public static void deleteFile(Scanner sc) {
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();

        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("Failed to delete file (may not exist).");
        }
    }
}