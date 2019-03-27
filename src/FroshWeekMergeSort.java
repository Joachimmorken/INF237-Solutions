import java.io.*;
import java.util.StringTokenizer;

public class FroshWeekMergeSort {

    public static void main(String[] args) throws FileNotFoundException {
        Kattio in = new Kattio(System.in);
        int students = in.getInt();
        int[] arr = new int[students];
        for (int i = 0; i < students; i++) {
            if (in.hasMoreTokens()) {
                arr[i] = in.getInt();
            }
        }
        long ans = mergeSort(arr, students);
        System.out.println(ans);
    }

    public static long mergeSort(int[] arr, int n) {
        //Base case, elements are already singular and cannot be split further
        if (n == 1) {
            //ready for merge
            return 0;
        }
        //Pivot
        int mid = n / 2;

        int[] left = new int[mid];
        int[] right = new int[n - mid];

        //Splitting input in two arrays
        //final step is to merge these two together AFTER it has been sorted (and counted for inverisons)
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }
        //recursivly splitting the lists and merging those
        long a = mergeSort(left, mid); //we first find the number of inversions in the left subarray
        long b = mergeSort(right, n - mid); //then we find the number of inversions in the right subarray
        long c = merge(arr, left, right, mid, n - mid); //then merge left and right subarray and find number of inversions

        return a + b + c;
    }

    //left will be length of leftArr and right will be length of rightArr
    private static long merge(int[] arr, int[] leftArr, int[] rightArr, int left, int right) {
        //pointer for left array
        int i = 0;
        //pointer for right array
        int j = 0;
        //pointer for "parentarray"
        int p = 0;
        //inversion count in this merge step
        long cnt = 0;

        //if there are still elements in both subarrays, then compare them
        while (i < left && j < right) {
            //if element at i´th pos in leftArr is smaller than rightArr, then update original parentarray
            if (leftArr[i] <= rightArr[j]) {
                arr[p] = leftArr[i];
                p++;
                i++;
            } else {
                // If we jump in here, it means that the current element (i) in the left array is greater than the
                // current element (j) in the right array.
                // From this, its easy to conclude that the elements to the right of i is also going to be greater than
                // j, since the subarrays are already sorted. Thus we can find the num of inversions by taking the length
                // of the left array, and subtracting i, which is how "far" we have reached in the array before encountering
                // an inversion
                arr[p] = rightArr[j];
                cnt += leftArr.length - i;
                p++;
                j++;
            }
        }
        //since right subarray is empty, just copy the remaining elements from rightArr to parentarray
        while (i < left) {
            arr[p] = leftArr[i];
            p++;
            i++;
        }
        //since left subarray is empty, just copy the remaining elements from rightArr to parentarray
        while (j < right) {
            arr[p] = rightArr[j];
            p++;
            j++;
        }
        return cnt;
    }

    private static class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public Kattio(InputStream i, OutputStream o) {
            super(new BufferedOutputStream(o));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public boolean hasMoreTokens() {
            return peekToken() != null;
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
        }

        public double getDouble() {
            return Double.parseDouble(nextToken());
        }

        public long getLong() {
            return Long.parseLong(nextToken());
        }

        public String getWord() {
            return nextToken();
        }


        private BufferedReader r;
        private String line;
        private StringTokenizer st;
        private String token;

        private String peekToken() {
            if (token == null)
                try {
                    while (st == null || !st.hasMoreTokens()) {
                        line = r.readLine();
                        if (line == null) return null;
                        st = new StringTokenizer(line);
                    }
                    token = st.nextToken();
                } catch (IOException e) {
                }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}
