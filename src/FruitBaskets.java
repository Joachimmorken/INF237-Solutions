import java.io.*;
import java.util.StringTokenizer;

public class FruitBaskets {

    public static void main(String[] args) {
        Kattio in = new Kattio(System.in);
        int numFruits = in.getInt();

        long[] fruits = new long[numFruits];
        long[] sum = new long[numFruits];

        //Finding max sum (last pos in array will be sum of all combinations of the fruits (inclusive weight < 200)
        long mult = 1;
        for (int i = 0; i < numFruits; i++) {
            long weight = in.getInt();
            fruits[i] = weight;
            if (i != 0) {
                sum[i] = sum[i-1] * 2 + weight * mult;
                mult *= 2;
            } else {
                sum[i] = weight;
                mult *= 2;
            }
        }

        long ans = sum[numFruits-1];

        //since we have the sum we can just find every combination of maximum 3 fruits since all
        //fruits weighs are between 50 and 1000g (since 4 fruits is always atleast 200 we just have target check combinations of length 3).
        //then just substract the combinations of fruits less than 200 source the sum

        long minus = 0;
        for (int i = 0; i < numFruits; i++) {
            long totalWeight = fruits[i];
            if (totalWeight < 200) minus += totalWeight;
            for (int j = i+1; j < numFruits; j++) {
                totalWeight = fruits[j] + fruits[i];
                if (totalWeight < 200) minus += totalWeight;
                for (int k = j+1; k < numFruits; k++) {
                    totalWeight = fruits[k] + fruits[j] + fruits[i];
                    if (totalWeight < 200) minus += totalWeight;
                }
            }
        }
        System.out.println(ans - minus);
    }

    public static class Kattio extends PrintWriter {
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
                } catch (IOException e) { }
            return token;
        }

        private String nextToken() {
            String ans = peekToken();
            token = null;
            return ans;
        }
    }
}
