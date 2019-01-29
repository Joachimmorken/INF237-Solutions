import java.util.Scanner;

public class ADifferentProblem {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLong()) {
            long x = scanner.nextLong();
            long y = scanner.nextLong();
            if (x > y) {
                System.out.println(x - y);
            } else {
                System.out.println(y- x);
            }
        }
    }
}
