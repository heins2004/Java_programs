import java.io.FileWriter;
abstract class HospitalMember{
    abstract int roomCharge();
}
class InvalidPatientException extends Exception{
    InvalidPatientException(String msg){
        super(msg);
    }
}
class Patient extends  HospitalMember{
    int id;
    String name;
    int age;
    String disease;
    Patient(int id, String name, int age, String disease) throws InvalidPatientException{
        if(age<=0){
            throw new InvalidPatientException("Invalid patient age");
        }
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public int roomCharge(){
        return 0;
    }
    double calculateBill(double consultation) throws InvalidPatientException{
        if(consultation<=0){
            throw new InvalidPatientException("Invalid bill amount");
        }
        return consultation;
    }
    double calculateBill(double consultation, double medicine) throws InvalidPatientException{
        if(consultation<=0 || medicine<0){
            throw new InvalidPatientException("Invalid bill amount");
        }
        return consultation + medicine;
    }
}
class InPatient extends Patient{
    int days;
    InPatient(int id, String name, int age, String disease, int days) throws InvalidPatientException{
        super(id, name, age, disease);
        this.days = days;
    }
    public int roomCharge(){
        return days*1000;
    }
}
class CalculateBill extends Thread{
     public void  run(){
        System.out.println("Calculating bill...");
    }
}
class PatientRecord extends Thread{
    public void run(){
        System.out.println("Saving patient record...");
    }
}
public class HospitalSystem {
    public static void main(String[] args) {
        try {
        Patient p[] =new Patient[2];
        CalculateBill billThread = new CalculateBill();
        PatientRecord recordThread = new PatientRecord();
        p[0] = new Patient(1,"John",30,"Flu");
        p[1] = new InPatient(2,"Alice",25,"Cold",5);
                System.out.println("Patient ID: " + p[0].id);
                System.out.println("Name: " + p[0].name);
                System.out.println("Age: " + p[0].age);
                System.out.println("Disease: " + p[0].disease);
                System.out.println("Room Charge: " + p[0].roomCharge());
                System.out.println("\nPatient ID: " + p[1].id);
                System.out.println("Name: " + p[1].name);
                System.out.println("Age: " + p[1].age);
                System.out.println("Disease: " + p[1].disease);
                System.out.println("Room Charge: " + p[1].roomCharge());
                billThread.start();
                recordThread.start();
                FileWriter writer = new FileWriter("patient_records.txt");
                for(Patient patient : p) {
                    writer.write("Patient ID: " + patient.id + "\n");
                    writer.write("Name: " + patient.name + "\n");
                    writer.write("Age: " + patient.age + "\n");
                    writer.write("Disease: " + patient.disease + "\n");
                    writer.write("Room Charge: " + patient.roomCharge() + "\n\n");
                }
                writer.close();
                System.out.println();
                System.out.println(
                    "Patient Data Saved");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
