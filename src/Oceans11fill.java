import java.io.*;
import java.util.StringTokenizer;

public class Oceans11fill {


    private static int[] arr = new int[10003];

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int numOfTests = kattio.getInt();

        for (int i = 1; i < 10001;i++) {
            fibFill(i);
        }
        for (int i = 0; i < numOfTests; i++) {
            System.out.println(arr[kattio.getInt()]);
        }
    }
    public static int fibFill(int n) {
        if (n < 2) {
            arr[n] = n+1;
        }
        if (arr[n] > 0 ) {
            return arr[n];
        }
        int result = fibFill(n-1) + fibFill(n-2);
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
