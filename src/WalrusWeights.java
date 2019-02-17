import java.io.*;
import java.util.*;

public class WalrusWeights {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int numOfWeights = kattio.getInt();
        int[] allWeights = new int[numOfWeights+1];

        for (int i = 0; i < numOfWeights; i++) {
            allWeights[i] = kattio.getInt();
        }

        Set<Integer> totalWeights = new HashSet<>();
        totalWeights.add(0);

        int result = 0;
        int bestDifference = Integer.MAX_VALUE;
        for (int i = 0; i < allWeights.length - 1; i++) {
            int[] uniqueWeights = totalWeights.stream().mapToInt(Number::intValue).toArray();
            for (int j = 0; j < uniqueWeights.length; j++) {
                int sum = allWeights[i]+uniqueWeights[j];
                if (sum == 1000) {
                    System.out.println(1000);
                    return;
                }
                int diff = Math.abs(1000 - sum);
                if (sum > 1000) {
                    if (diff <= bestDifference) {
                        bestDifference = diff;
                        result = sum;
                    } else {
                        continue;
                    }
                }
                totalWeights.add(sum);
                if (diff < bestDifference) {
                    bestDifference = diff;
                    result = sum;
                }
            }
        } System.out.println(result);
    }
    static class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
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
