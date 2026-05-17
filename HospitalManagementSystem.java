import java.io.FileWriter;
import java.util.Scanner;

abstract class HospitalMember {
    abstract void memberType();
}

class InvalidPatientException extends Exception {
    InvalidPatientException(String msg) {
        super(msg);
    }
}

class Patient extends HospitalMember {

    private int patientId;
    private String name;
    private int age;
    protected String disease;

    Patient(int patientId,String name,int age,String disease)
            throws InvalidPatientException {

        if(age <= 0) {
            throw new InvalidPatientException("Invalid patient age");
        }

        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    double calculateBill(double consultation)
            throws InvalidPatientException {

        if(consultation <= 0) {
            throw new InvalidPatientException("Invalid bill amount");
        }

        return consultation;
    }

    double calculateBill(double consultation,double medicine)
            throws InvalidPatientException {

        if(consultation <= 0 || medicine < 0) {
            throw new InvalidPatientException("Invalid bill amount");
        }

        return consultation + medicine;
    }

    String prescription() {

        return "\n===== PRESCRIPTION =====" +
                "\nPatient ID : " + patientId +
                "\nName       : " + name +
                "\nAge        : " + age +
                "\nDisease    : " + disease +
                "\n========================";
    }

    void memberType() {
        System.out.println("General Patient");
    }
}

class InPatient extends Patient {

    int days;

    InPatient(int patientId,
              String name,
              int age,
              String disease,
              int days)
            throws InvalidPatientException {

        super(patientId,name,age,disease);

        this.days = days;
    }

    double calculateRoomCharge(double roomCharge) {
        return days * roomCharge;
    }

    void memberType() {
        System.out.println("In Patient");
    }
}

class BillThread extends Thread {

    Patient p;
    double bill;

    BillThread(Patient p,double bill) {
        this.p = p;
        this.bill = bill;
    }

    public void run() {

        try {

            FileWriter fw =
                    new FileWriter("patients.txt",true);

            fw.write(p.prescription());
            fw.write("\nBill Amount : " + bill);
            fw.write("\n\n");

            fw.close();

            System.out.println("Bill Generated");
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}

class MonitorThread extends Thread {

    Patient p;

    MonitorThread(Patient p) {
        this.p = p;
    }

    public void run() {

        try {

            for(int i = 1; i <= 3; i++) {

                System.out.println(
                        "Monitoring patient with disease: "
                                + p.disease
                );

                Thread.sleep(1000);
            }
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}

public class HospitalManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter number of patients: ");
            int n = sc.nextInt();
            sc.nextLine();

            Patient[] patients =
                    new Patient[n];

            for(int i = 0; i < n; i++) {

                System.out.println(
                        "\nEnter Patient Details"
                );

                System.out.print("Patient ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                System.out.print("Disease: ");
                String disease = sc.nextLine();

                System.out.print(
                        "Is InPatient? (yes/no): "
                );

                String type = sc.nextLine();

                if(type.equalsIgnoreCase("yes")) {

                    System.out.print(
                            "Enter number of days: "
                    );

                    int days = sc.nextInt();
                    sc.nextLine();

                    patients[i] =
                            new InPatient(
                                    id,
                                    name,
                                    age,
                                    disease,
                                    days
                            );
                }

                else {

                    patients[i] =
                            new Patient(
                                    id,
                                    name,
                                    age,
                                    disease
                            );
                }
            }

            for(Patient p : patients) {

                p.memberType();

                System.out.println(
                        p.prescription()
                );

                System.out.print(
                        "Enter consultation fee: "
                );

                double consultation =
                        sc.nextDouble();

                System.out.print(
                        "Enter medicine charge: "
                );

                double medicine =
                        sc.nextDouble();

                double bill =
                        p.calculateBill(
                                consultation,
                                medicine
                        );

                if(p instanceof InPatient) {

                    bill += ((InPatient)p)
                            .calculateRoomCharge(1000);
                }

                System.out.println(
                        "Total Bill: " + bill
                );

                BillThread b =
                        new BillThread(
                                p,
                                bill
                        );

                MonitorThread m =
                        new MonitorThread(p);

                b.start();
                m.start();
            }

            sc.close();
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}
