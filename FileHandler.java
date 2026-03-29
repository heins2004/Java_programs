import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    public static void main(String[] args) {

        try {
            File file = new File("data.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("Hello, this is a Java file handling program.\n");
            writer.write("You can store and read data easily.");
            writer.close();

            System.out.println("Data written to file.");
            Scanner reader = new Scanner(file);
            System.out.println("\nReading from file:");

            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}