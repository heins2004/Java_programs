import java.io.FileWriter;

abstract class Order {

    abstract void orderType();
}

class InvalidQuantityException extends Exception {

    InvalidQuantityException(String msg) {
        super(msg);
    }
}

class FoodOrder extends Order {

    int orderId;
    String customerName;
    String foodItem;
    int quantity;
    String status;

    FoodOrder(int orderId,
              String customerName,
              String foodItem,
              int quantity) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.status = "Pending";
    }

    double calculateBill(double price) {
        return quantity * price;
    }

    double calculateBill(double price,
                         double extraCharge) {

        return (quantity * price)
                + extraCharge;
    }

    double addGST(double amount) {
        return amount + (amount * 0.05);
    }

    void validateQuantity()
            throws InvalidQuantityException {

        if(quantity <= 0) {

            throw new InvalidQuantityException(
                    "Quantity must be greater than 0"
            );
        }
    }

    String generateInvoice() {

        return "\n===== FOOD INVOICE =====" +
                "\nOrder ID   : " + orderId +
                "\nCustomer   : " + customerName +
                "\nFood Item  : " + foodItem +
                "\nQuantity   : " + quantity +
                "\nStatus     : " + status +
                "\n========================";
    }

    void orderType() {
        System.out.println("General Food Order");
    }
}

class VegOrder extends FoodOrder {

    VegOrder(int id,
             String customer,
             String item,
             int quantity) {

        super(id, customer, item, quantity);
    }

    double calculateBill(double price) {

        double total = quantity * price;

        return total - 20; // discount
    }

    void orderType() {
        System.out.println("Veg Order");
    }
}

class NonVegOrder extends FoodOrder {

    NonVegOrder(int id,
                String customer,
                String item,
                int quantity) {

        super(id, customer, item, quantity);
    }

    double calculateBill(double price) {

        double total = quantity * price;

        return total + 50; // service charge
    }

    void orderType() {
        System.out.println("Non-Veg Order");
    }
}

class CookingThread extends Thread {

    FoodOrder order;

    CookingThread(FoodOrder order) {
        this.order = order;
    }

    public void run() {

        try {

            System.out.println(
                    getName() +
                    " Cooking: " +
                    order.foodItem
            );

            Thread.sleep(2000);

            order.status = "Cooked";

            System.out.println(
                    order.foodItem +
                    " is Ready!"
            );
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}

class DeliveryThread extends Thread {

    FoodOrder order;

    DeliveryThread(FoodOrder order) {
        this.order = order;
    }

    public void run() {

        try {

            Thread.sleep(3000);

            order.status = "Delivered";

            FileWriter fw =
                    new FileWriter(
                            "orders.txt",
                            true
                    );

            fw.write(order.generateInvoice());
            fw.write("\n\n");

            fw.close();

            System.out.println(
                    "Order Delivered: "
                    + order.foodItem
            );
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }
}

public class FoodOrdering{

    public static void main(String[] args) {

        try {

            FoodOrder[] orders =
                    new FoodOrder[2];

            orders[0] = new VegOrder(
                    1,
                    "Rahul",
                    "Paneer Tikka",
                    2
            );

            orders[1] = new NonVegOrder(
                    2,
                    "Aman",
                    "Chicken Biryani",
                    3
            );

            for(FoodOrder order : orders) {

                order.validateQuantity();

                order.orderType();

                double bill =
                        order.calculateBill(200);

                bill = order.addGST(bill);

                System.out.println(
                        order.generateInvoice()
                );

                System.out.println(
                        "Final Bill (with GST): "
                        + bill
                );

                System.out.println();
            }

            CookingThread cook1 =
                    new CookingThread(
                            orders[0]
                    );

            DeliveryThread deliver1 =
                    new DeliveryThread(
                            orders[0]
                    );

            cook1.start();
            deliver1.start();
        }

        catch(Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}