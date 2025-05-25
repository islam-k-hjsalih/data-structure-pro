
    import java.util.Scanner;

    public class proTest {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            System.out.print(" First Equation: ");
            String input1 = sc.nextLine();

            System.out.print(" Second Equation: ");
            String input2 = sc.nextLine();

            System.out.print("enter  (+ - * /): ");
            String operation = sc.nextLine();

            polynomial p1 = parsePolynomial(input1);
            polynomial p2 = parsePolynomial(input2);

            polynomial result = null;

            switch (operation) {
                case "+":
                    result = p1.add(p2);
                    break;
                case "-":
                    result = p1.subtract(p2);
                    break;
                case "*":
                    result = p1.multiply(p2);
                    break;
                case "//":
                    try {
                        result = p1.divide(p2);
                    } catch (ArithmeticException e) {
                        System.out.println("error: " + e.getMessage());
                        return;
                    }
                    break;
                default:
                    System.out.println("error - _-!");
                    return;
            }

            System.out.print("RESULT: ");
            printPolynomial(result);

            System.out.println("chose:");
            System.out.println("1. Infix Notation ");
            System.out.println("2. Postfix Notation ");
            System.out.println("3. Prefix Notation ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Infix Notation:");
                    printPolynomial( result);
                    break;
                case 2:
                    System.out.println("Postfix Notation:");
                    result.displayPostfix();
                    break;
                case 3:
                    System.out.println("Prefix Notation:");
                    result.displayPrefix();
                    break;
                default:
                    System.out.println("choice !");
            }

            System.out.print("Enter the value of x : ");
            int xValue = sc.nextInt();

            int evalResult = result.evaluate(xValue);
            System.out.println("The value of the polynomial at x = " + xValue + " is: " + evalResult);


        }


        public static polynomial parsePolynomial(String input) {
            polynomial p = new polynomial();

            input = input.toLowerCase().replace("-", "+-").replace(" ", "");
            String[] parts = input.split("\\+");

            for (String part : parts) {
                if (part.isEmpty()) continue;

                int coeff = 0;
                int power = 0;

                if (part.contains("x")) {
                    int xIndex = part.indexOf("x");

                    // المعامل
                    String coeffStr = part.substring(0, xIndex);
                    if (coeffStr.isEmpty() || coeffStr.equals("+")) {
                        coeff = 1;
                    } else if (coeffStr.equals("-")) {
                        coeff = -1;
                    } else {
                        coeff = Integer.parseInt(coeffStr);
                    }

                    // الأس
                    if (part.contains("^")) {
                        power = Integer.parseInt(part.substring(part.indexOf("^") + 1));
                    } else {
                        power = 1;
                    }
                } else {
                    coeff = Integer.parseInt(part);
                    power = 0;
                }

                p.addTerm(coeff, power);
            }

            return p;
        }



        public static void printPolynomial(polynomial p) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < p.islam.size(); i++) {
                term t = (term) p.islam.getIndex(i);
                if (t.c == 0) continue;

                if (sb.length() > 0 && t.c > 0) sb.append(" + ");
                else if (t.c < 0) sb.append(" - ");

                int absCoeff = Math.abs(t.c);
                if (t.p == 0) sb.append(absCoeff);
                else if (t.p == 1) sb.append(absCoeff + "x");
                else sb.append(absCoeff + "x^" + t.p);
            }

            if (sb.length() == 0) sb.append("0");

            System.out.println(sb.toString());
        }




    }
