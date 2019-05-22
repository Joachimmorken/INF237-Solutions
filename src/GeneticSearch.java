import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class GeneticSearch {

    public static void main(String[] args) {
        Kattio in = new Kattio(System.in);

        String s1 = in.getWord();
        while (!s1.equals("0")) {
            String s2 = in.getWord();

            int type1 = type1(s1, s2);
            int type2 = type2(s1, s2);
            int type3 = type3(s1, s2);

            // s = A G C T
            // 1. s without any changes
            // 2. unique strings by deleting one character from s
            // 3. unique strings by inserting one character (A, G, C or T) to s
            in.println(type1 + " " + type2 + " " + type3);
            s1 = in.getWord();
        }
        in.close();
    }

    public static int type1(String s1, String s2) {
        //return kmp(s1, s2);
        int counter = 0;
        for (int i = 0; i < s2.length() - s1.length() + 1; i++) {
            if (s1.equals(s2.substring(i, i + s1.length()))) {
                counter++;
            }
        }
        return counter;
    }

    public static int type2(String s1, String s2) {
        int counter = 0;
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < s1.length(); i++) {
            String minusOne = s1.substring(0, i) + s1.substring(i + 1);
            set.add(minusOne);
        }

        for (String s : set) {
            counter += type1(s, s2);
        }
        return counter;
    }

    public static int type3(String s1, String s2) {
        int counter = 0;
        HashSet<String> set = new HashSet<>();

        String[] arr = new String[]{"A", "G", "C", "T"};

        for (String c : arr) {
            for (int i = 0; i < s1.length() + 1; i++) {
                String s = s1.substring(0, i) + c + s1.substring(i);
                set.add(s);
            }
        }
        for (String s : set) {
            counter += type1(s, s2);
        }
        return counter;
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
