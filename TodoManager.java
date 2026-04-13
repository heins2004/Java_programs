import java.io.*;
import java.util.*;

public class TodoManager {

    static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- TO-DO MANAGER ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete All Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter task: ");
                    String task = sc.nextLine();
                    addTask(task);
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    clearTasks();
                    System.out.println("All tasks deleted.");
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

    static void addTask(String task) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(task + "\n");
            System.out.println("Task added!");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    static void viewTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int count = 1;

            System.out.println("\nYour Tasks:");
            while ((line = br.readLine()) != null) {
                System.out.println(count++ + ". " + line);
            }

        } catch (IOException e) {
            System.out.println("No tasks found.");
        }
    }

    static void clearTasks() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write("");
        } catch (IOException e) {
            System.out.println("Error clearing file.");
        }
    }
}