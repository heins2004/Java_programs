import java.io.FileWriter;
import java.util.Scanner;

abstract class User {
    abstract void userType();
}

class InvalidMarksException extends Exception {

    InvalidMarksException(String msg) {
        super(msg);
    }
}

class Student extends User {

    private int studentId;
    private String name;
    private int marks;
    protected String course;

    Student(int studentId,
            String name,
            int marks,
            String course)
            throws InvalidMarksException {

        if(marks < 0 || marks > 100) {

            throw new InvalidMarksException(
                    "Invalid marks entered"
            );
        }

        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
        this.course = course;
    }

    double calculateGradePoint() {
        return marks / 10.0;
    }

    double calculateGradePoint(double bonus) {
        return (marks / 10.0) + bonus;
    }

    String studentDetails() {

        return "\n===== STUDENT DETAILS =====" +
                "\nStudent ID : " + studentId +
                "\nName       : " + name +
                "\nMarks      : " + marks +
                "\nCourse     : " + course +
                "\n============================";
    }

    void userType() {
        System.out.println("Regular Student");
    }
}

class ScholarshipStudent extends Student {

    double scholarshipAmount;

    ScholarshipStudent(int studentId,
                       String name,
                       int marks,
                       String course,
                       double scholarshipAmount)
            throws InvalidMarksException {

        super(studentId,name,marks,course);

        this.scholarshipAmount =
                scholarshipAmount;
    }

    double scholarshipBonus() {
        return scholarshipAmount / 1000;
    }

    void userType() {
        System.out.println(
                "Scholarship Student"
        );
    }
}

class SaveThread extends Thread {

    Student s;
    double gp;

    SaveThread(Student s,double gp) {
        this.s = s;
        this.gp = gp;
    }

    public void run() {

        try {

            FileWriter fw =
                    new FileWriter(
                            "students.txt",
                            true
                    );

            fw.write(s.studentDetails());

            fw.write(
                    "\nGrade Point : " + gp
            );

            fw.write("\n\n");

            fw.close();

            System.out.println(
                    "Student data saved"
            );
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}

class PerformanceThread extends Thread {

    Student s;

    PerformanceThread(Student s) {
        this.s = s;
    }

    public void run() {

        try {

            for(int i = 1; i <= 3; i++) {

                System.out.println(
                        "Analyzing performance for course: "
                                + s.course
                );

                Thread.sleep(1000);
            }
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}

public class StudentPortalSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print(
                    "Enter number of students: "
            );

            int n = sc.nextInt();
            sc.nextLine();

            Student[] students =
                    new Student[n];

            for(int i = 0; i < n; i++) {

                System.out.println(
                        "\nEnter Student Details"
                );

                System.out.print(
                        "Student ID: "
                );

                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Marks: ");
                int marks = sc.nextInt();
                sc.nextLine();

                System.out.print("Course: ");
                String course = sc.nextLine();

                System.out.print(
                        "Scholarship Student? (yes/no): "
                );

                String type = sc.nextLine();

                if(type.equalsIgnoreCase("yes")) {

                    System.out.print(
                            "Scholarship Amount: "
                    );

                    double amount =
                            sc.nextDouble();

                    sc.nextLine();

                    students[i] =
                            new ScholarshipStudent(
                                    id,
                                    name,
                                    marks,
                                    course,
                                    amount
                            );
                }

                else {

                    students[i] =
                            new Student(
                                    id,
                                    name,
                                    marks,
                                    course
                            );
                }
            }

            for(Student s : students) {

                s.userType();

                System.out.println(
                        s.studentDetails()
                );

                double gp =
                        s.calculateGradePoint();

                if(s instanceof ScholarshipStudent) {

                    gp +=
                            ((ScholarshipStudent)s)
                                    .scholarshipBonus();
                }

                System.out.println(
                        "Final Grade Point : "
                                + gp
                );

                SaveThread st =
                        new SaveThread(
                                s,
                                gp
                        );

                PerformanceThread pt =
                        new PerformanceThread(s);

                st.start();
                pt.start();
            }

            sc.close();
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}