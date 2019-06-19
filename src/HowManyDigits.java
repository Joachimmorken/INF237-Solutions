import java.io.*;
import java.util.StringTokenizer;

public class HowManyDigits {

    public static void main(String[] args) {
        Kattio in = new Kattio(System.in);

        double[] digArr = new double[1000001];
        digArr[0] = 1;

        for (int i = 1; i < digArr.length; i++) {
            digArr[i] = digArr[i - 1] + Math.log10(i);
        }

        while (in.hasMoreTokens()) {
            in.println(((int) Math.floor(digArr[in.getInt()])));
            in.flush();
        }
        in.close();
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
