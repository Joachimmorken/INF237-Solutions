import java.io.*;
import java.util.StringTokenizer;

public class Oceans11 {
    private static int[] arr = new int[10003];

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int numOfTests = kattio.getInt();
        for (int i = 0; i <numOfTests; i++) {
            System.out.println(fib(kattio.getInt() + 2));
        }
    }
    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        if (arr[n] > 0) {
            return arr[n];
        }
        int result = fib(n-1) + fib(n-2);
        arr[n] = result % 1000000007;
        return arr[n];
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

