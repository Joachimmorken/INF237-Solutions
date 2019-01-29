import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int minNum = 1;
        int maxNum = 1000;

        for(;;) {
            int mid = (minNum + maxNum) / 2; // 500 i utgangspunktet
            System.out.println(mid);
            String feedback = scanner.nextLine();
            if (feedback.equals("correct")) {
                break;
            }
            else if (feedback.equals("lower")) {
                maxNum = mid - 1;
                // Her vil vi ha tall fra 1 - 499, også tall fra 249 - 1 osv
            }
            else if (feedback.equals("higher")) {
                minNum = mid + 1;
                // Her vil vi ha tall fra 501 til 1000, også tall fra 751 - 1000 osv
            }
        }
    }
}
