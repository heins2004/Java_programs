import java.util.*;
public class BillingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] id = {1,2,3,4,5,6,7,8,9,10};
        String[] pname = {"Computer","Pen","Book","Box","Ring","Rubber","Scale","Sharpner","Bat","Football"};
        int[] price = {25000,5,50,100,400,5,10,5,3000,500};
        List<Integer> billId = new ArrayList<>();
        List<String> billName = new ArrayList<>();
        List<Integer> billPrice = new ArrayList<>();
        List<Integer> billQty = new ArrayList<>();
        List<Integer> billTotal = new ArrayList<>();
        int grandTotal = 0;
        while (true) {
            System.out.print("Enter Product ID: ");
            int pid = sc.nextInt();
            boolean found = false;
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pid) {
                    found = true;
                    System.out.println("Product Name: " + pname[i]);
                    System.out.println("Price: " + price[i]);
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    int total = price[i] * qty;
                    grandTotal += total;
                    billId.add(id[i]);
                    billName.add(pname[i]);
                    billPrice.add(price[i]);
                    billQty.add(qty);
                    billTotal.add(total);
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid Product ID");
            }
            System.out.print("Add more? (yes/no): ");
            String ch = sc.next();
            if (ch.equalsIgnoreCase("no")) {
                break;
            }
        }
        System.out.println("\n========== BILL DETAILS ==========");
        System.out.printf("%-5s %-12s %-8s %-5s %-8s%n",
                "ID", "Product", "Price", "Qty", "Total");
        for (int i = 0; i < billId.size(); i++) {
            System.out.printf("%-5d %-12s %-8d %-5d %-8d%n",
                    billId.get(i),
                    billName.get(i),
                    billPrice.get(i),
                    billQty.get(i),
                    billTotal.get(i));
        }
        System.out.println("-----------------------------------");
        System.out.println("Grand Total: " + grandTotal);
    }
}