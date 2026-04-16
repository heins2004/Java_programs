import java.util.Stack;
import java.util.Scanner;

public class ExpressionEvaluator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter expression (e.g., 3+5*2): ");
        String exp = sc.nextLine();

        System.out.println("Result: " + evaluate(exp));
        sc.close();
    }

    static int evaluate(String exp) {

        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {

            char ch = exp.charAt(i);

            if (Character.isDigit(ch)) {
                values.push(ch - '0');
            }

            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
                    int b = values.pop();
                    int a = values.pop();
                    char op = ops.pop();
                    values.push(applyOp(a, b, op));
                }

                ops.push(ch);
            }
        }

        while (!ops.isEmpty()) {
            int b = values.pop();
            int a = values.pop();
            char op = ops.pop();
            values.push(applyOp(a, b, op));
        }

        return values.pop();
    }

    static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    static int applyOp(int a, int b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return b != 0 ? a / b : 0;
        }
        return 0;
    }
}