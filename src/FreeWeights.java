import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FreeWeights {
    private static Scanner scanner;
    private static List<Integer> row1;
    private static List<Integer> row2;
    private static List<Integer> unique;

    public static void main(String[] args) throws FileNotFoundException {
        scanner = new Scanner(new File("src/1.in"));
        int numOfPairs = Integer.parseInt(scanner.nextLine());

        //Creates a list for each row and a set containing sorted unique weights
        handleInput();
        System.out.println(binarySearch());
    }
    private static boolean works(int n) {
        List<Integer> row1test = row1.stream().filter(i -> i > n).collect(Collectors.toList());
        List<Integer> row2test = row2.stream().filter(i -> i > n).collect(Collectors.toList());

        if (paired(row1test) && paired(row2test)) {
            return true;
        }
        return false;
    }

    private static boolean paired (List<Integer> row) {

        if (row.size() % 2 != 0) {
            return false;
        }
        for (int i = 0 ; i < row.size(); i+=2) {
            if (!Objects.equals(row.get(i), row.get(i+1))) {
                return false;
            }
        }
        return true;
    }

    private static int binarySearch() {
        if(works(0)) {
            System.out.println("0");
            System.exit(0);
        }
        int low = 0;
        // Finds the maximum weight in the unique list
        int high = unique.size();
        while (low < high) {
            int mid = (low + high) / 2;
            int num = unique.get(mid);
            if (works(num)) {
                //Trying lower value
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return unique.get(low);
    }

    private static void handleInput() {
        row1 = new ArrayList<>();
        row2 = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        String r1 = scanner.nextLine();
        String[] nums1 = r1.trim().split("\\s+");
        for (int i = 0; i<nums1.length; i++) {
            int weight = Integer.parseInt(nums1[i]);
            row1.add(weight);
            set.add(weight);
        }
        String r2 = scanner.nextLine();
        String[] nums2 = r2.trim().split("\\s+");
        for (int i = 0; i<nums2.length; i++) {
            int weight = Integer.parseInt(nums2[i]);
            row2.add(weight);
            set.add(weight);
        }
        unique = new ArrayList<>(set);
        Collections.sort(unique);
    }
}
