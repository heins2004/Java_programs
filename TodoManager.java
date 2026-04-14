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
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Exit");
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
                    viewTasks();
                    System.out.print("Enter task number to delete: ");
                    int deleteIndex = sc.nextInt();
                    deleteTask(deleteIndex);
                    break;

                case 4:
                    viewTasks();
                    System.out.print("Enter task number to mark complete: ");
                    int completeIndex = sc.nextInt();
                    markComplete(completeIndex);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    static void addTask(String task) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write("[ ] " + task + "\n");
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

    static void deleteTask(int index) {
        List<String> tasks = readTasks();

        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
            writeTasks(tasks);
            System.out.println("Task deleted!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    static void markComplete(int index) {
        List<String> tasks = readTasks();

        if (index > 0 && index <= tasks.size()) {
            String task = tasks.get(index - 1);
            task = task.replace("[ ]", "[✔]");
            tasks.set(index - 1, task);
            writeTasks(tasks);
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    static List<String> readTasks() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // ignore
        }
        return tasks;
    }

    static void writeTasks(List<String> tasks) {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (String t : tasks) {
                fw.write(t + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating file.");
        }
    }
}