import java.io.*;
import java.util.StringTokenizer;

public class InverseFactorial {
    public static void main(String[] args) {
        Kattio in = new Kattio(System.in, System.out);

        String n = in.getWord();
        int ln = String.valueOf(n).length();

        if (ln < 4) {
            int small
                    = (n.equals("0") || n.equals("1")) ? 1
                    : (n.equals("2")) ? 2
                    : (n.equals("6")) ? 3
                    : (n.equals("24")) ? 4
                    : (n.equals("120")) ? 5 : 6;
            System.out.println(small);
        }
        else {
            double sum = Math.log10(720) + 1; //first factorial is always length 4 or bigger (i.e first possible number is 5040)
            int factorial = 6;
            while (true) {
                factorial++;
                sum += Math.log10(factorial);
                if (Math.floor(sum) == ln) break;
            }
            in.write(String.valueOf(factorial));
        }
        in.flush();
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
