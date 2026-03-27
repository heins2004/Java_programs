import java.io.*;
import java.util.Scanner;
public class MenuDriven {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int c;

        do {
            System.out.println("\n** Menu Driven Program **");
            System.out.println("1. Create File\n2. Read File\n3. Update File\n4. Delete File\n5. Exit");
            System.out.print("Enter your choice: ");
            c = s.nextInt();
            s.nextLine(); 
            switch (c) {
                case 1: 
                    System.out.print("Enter file name: ");
                    String cName = s.nextLine();
                    try {
                        File f = new File(cName);
                        if (f.createNewFile()) {
                            System.out.println("File created: " + f.getName());
                        } else {
                            System.out.println("File already exists.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error creating file.");
                    }
                    break;
                case 2: 
                    System.out.print("Enter file name: ");
                    String rName = s.nextLine();
                    try {
                        File f = new File(rName);
                        Scanner r = new Scanner(f);
                        while (r.hasNextLine()) {
                            System.out.println(r.nextLine());
                        }
                        r.close();
                    } catch (Exception e) {
                        System.out.println("Error reading file.");
                    }
                    break;
                case 3: 
                    System.out.print("Enter file name: ");
                    String uName = s.nextLine();
                    try {
                        FileWriter writer = new FileWriter(uName, true);
                        System.out.print("Enter text to add: ");
                        String text = s.nextLine();
                        writer.write(text + "\n");
                        writer.close();
                        System.out.println("File updated successfully.");
                    } catch (IOException e) {
                        System.out.println("Error updating file.");
                    }
                    break;
                case 4: 
                    System.out.print("Enter file name: ");
                    String dName = s.nextLine();
                    File f = new File(dName);
                    if (f.delete()) {
                        System.out.println("File deleted successfully.");
                    } else {
                        System.out.println("Failed to delete file.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (c != 5);
    }
}