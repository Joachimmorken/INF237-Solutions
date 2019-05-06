import java.io.*;
import java.util.StringTokenizer;

public class Reseto {

    public static void main(String[] args) {
        Kattio in = new Kattio(System.in);

        int n = in.getInt();
        int k = in.getInt();

        if (k == 1) {
            System.out.println(2);
            return;
        }

        boolean[] bool = new boolean[n + 2];

        int smallest = 0;
        int counter = 0;
        for (int i = 2; i <= n; i++) {
            if (!bool[i]) {
                smallest = i;
                bool[i] = true;
                counter++;
                if (counter == k) {
                    System.out.println(i);
                    return;
                }
            }
            for (int j = i + i; j <= n; j += i) {
                if (j % smallest == 0 && !bool[j]) {
                    bool[j] = true;
                    counter++;
                    if (counter == k) {
                        System.out.println(j);
                        return;
                    }
                }
            }
        }
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
