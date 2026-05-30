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
    private double attendance;
    protected String course;

    Student(int studentId,
            String name,
            int marks,
            String course,
            double attendance)
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
        this.attendance = attendance;
    }

    double calculateGradePoint() {
        return marks / 10.0;
    }

    double calculateGradePoint(double bonus) {
        return (marks / 10.0) + bonus;
    }

    String getGrade() {

        if(marks >= 90)
            return "A";
        else if(marks >= 75)
            return "B";
        else if(marks >= 60)
            return "C";
        else if(marks >= 50)
            return "D";
        else
            return "F";
    }

    String getResult() {

        if(marks >= 50 && attendance >= 75)
            return "PASS";
        else
            return "FAIL";
    }

    int getMarks() {
        return marks;
    }

    String studentDetails() {

        return "\n===== STUDENT REPORT =====" +
                "\nStudent ID : " + studentId +
                "\nName       : " + name +
                "\nMarks      : " + marks +
                "\nAttendance : " + attendance + "%" +
                "\nCourse     : " + course +
                "\nGrade      : " + getGrade() +
                "\nResult     : " + getResult() +
                "\n==========================";
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
                       double attendance,
                       double scholarshipAmount)
            throws InvalidMarksException {

        super(studentId,
              name,
              marks,
              course,
              attendance);

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
                    "Student record saved"
            );
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}

class AnalysisThread extends Thread {

    Student s;

    AnalysisThread(Student s) {
        this.s = s;
    }

    public void run() {

        try {

            for(int i = 1; i <= 3; i++) {

                System.out.println(
                        "Analyzing performance of "
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

                System.out.print(
                        "Name: "
                );

                String name =
                        sc.nextLine();

                System.out.print(
                        "Marks: "
                );

                int marks =
                        sc.nextInt();

                sc.nextLine();

                System.out.print(
                        "Course: "
                );

                String course =
                        sc.nextLine();

                System.out.print(
                        "Attendance (%): "
                );

                double attendance =
                        sc.nextDouble();

                sc.nextLine();

                System.out.print(
                        "Scholarship Student? (yes/no): "
                );

                String type =
                        sc.nextLine();

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
                                    attendance,
                                    amount
                            );
                }

                else {

                    students[i] =
                            new Student(
                                    id,
                                    name,
                                    marks,
                                    course,
                                    attendance
                            );
                }
            }

            Student topper =
                    students[0];

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
                        "Grade Point : "
                                + gp
                );

                SaveThread st =
                        new SaveThread(
                                s,
                                gp
                        );

                AnalysisThread at =
                        new AnalysisThread(
                                s
                        );

                st.start();
                at.start();

                if(s.getMarks() >
                        topper.getMarks()) {

                    topper = s;
                }
            }

            FileWriter fw =
                    new FileWriter(
                            "topper.txt"
                    );

            fw.write(
                    "===== TOPPER REPORT =====\n"
            );

            fw.write(
                    topper.studentDetails()
            );

            fw.close();

            System.out.println(
                    "\nTopper report saved successfully."
            );

            sc.close();
        }

        catch(Exception e) {

            System.out.println(
                    "Error: "
                            + e.getMessage()
            );
        }
    }
}