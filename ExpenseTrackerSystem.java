import java.io.FileWriter;
import java.util.Scanner;

abstract class Transaction {
    abstract void transactionType();
}

class InvalidExpenseException extends Exception {

    InvalidExpenseException(String msg) {
        super(msg);
    }
}

class Expense extends Transaction {

    private int expenseId;
    private String title;
    private double amount;
    protected String category;

    Expense(int expenseId,
            String title,
            double amount,
            String category)
            throws InvalidExpenseException {

        if(amount <= 0) {
            throw new InvalidExpenseException(
                    "Invalid expense amount"
            );
        }

        this.expenseId = expenseId;
        this.title = title;
        this.amount = amount;
        this.category = category;
    }

    double calculateTotal(double tax) {
        return amount + tax;
    }

    double calculateTotal(double tax,
                          double serviceCharge) {

        return amount + tax + serviceCharge;
    }

    String expenseDetails() {

        return "\n===== EXPENSE DETAILS =====" +
                "\nExpense ID : " + expenseId +
                "\nTitle      : " + title +
                "\nAmount     : " + amount +
                "\nCategory   : " + category +
                "\n============================";
    }

    void transactionType() {
        System.out.println("Normal Expense");
    }
}

class BusinessExpense extends Expense {

    int employees;

    BusinessExpense(int expenseId,
                    String title,
                    double amount,
                    String category,
                    int employees)
            throws InvalidExpenseException {

        super(expenseId,title,amount,category);

        this.employees = employees;
    }

    double employeeCost() {
        return employees * 100;
    }

    void transactionType() {
        System.out.println("Business Expense");
    }
}

class SaveThread extends Thread {

    Expense e;
    double total;

    SaveThread(Expense e,double total) {
        this.e = e;
        this.total = total;
    }

    public void run() {

        try {

            FileWriter fw =
                    new FileWriter(
                            "expenses.txt",
                            true
                    );

            fw.write(e.expenseDetails());
            fw.write(
                    "\nFinal Total : "
                            + total
            );

            fw.write("\n\n");

            fw.close();

            System.out.println(
                    "Expense saved successfully"
            );
        }

        catch(Exception ex) {
            System.out.println(ex);
        }
    }
}

class AnalyticsThread extends Thread {

    Expense e;

    AnalyticsThread(Expense e) {
        this.e = e;
    }

    public void run() {

        try {

            for(int i = 1; i <= 3; i++) {

                System.out.println(
                        "Analyzing expense category: "
                                + e.category
                );

                Thread.sleep(1000);
            }
        }

        catch(Exception ex) {
            System.out.println(ex);
        }
    }
}

public class ExpenseTrackerSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print(
                    "Enter number of expenses: "
            );

            int n = sc.nextInt();
            sc.nextLine();

            Expense[] expenses =
                    new Expense[n];

            for(int i = 0; i < n; i++) {

                System.out.println(
                        "\nEnter Expense Details"
                );

                System.out.print("Expense ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Title: ");
                String title = sc.nextLine();

                System.out.print("Amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();

                System.out.print("Category: ");
                String category = sc.nextLine();

                System.out.print(
                        "Business Expense? (yes/no): "
                );

                String type = sc.nextLine();

                if(type.equalsIgnoreCase("yes")) {

                    System.out.print(
                            "Enter number of employees: "
                    );

                    int employees = sc.nextInt();
                    sc.nextLine();

                    expenses[i] =
                            new BusinessExpense(
                                    id,
                                    title,
                                    amount,
                                    category,
                                    employees
                            );
                }

                else {

                    expenses[i] =
                            new Expense(
                                    id,
                                    title,
                                    amount,
                                    category
                            );
                }
            }

            for(Expense e : expenses) {

                e.transactionType();

                System.out.println(
                        e.expenseDetails()
                );

                System.out.print(
                        "Enter tax amount: "
                );

                double tax = sc.nextDouble();

                double total =
                        e.calculateTotal(tax);

                if(e instanceof BusinessExpense) {

                    total +=
                            ((BusinessExpense)e)
                                    .employeeCost();
                }

                System.out.println(
                        "Final Total : " + total
                );

                SaveThread st =
                        new SaveThread(
                                e,
                                total
                        );

                AnalyticsThread at =
                        new AnalyticsThread(e);

                st.start();
                at.start();
            }

            sc.close();
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}
