import java.io.*;
import java.util.*;

public class EatingOut {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int items = kattio.getInt();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(kattio.getInt());
        list.add(kattio.getInt());
        list.add(kattio.getInt());
        Collections.sort(list, Collections.reverseOrder());

        while (items > 0) {
            int i = 0;
            int max = list.get(i);
            max = max - 1;
            list.set(i, max);
            i++;
            int secMax = list.get(i);
            secMax = secMax - 1;
            list.set(i, secMax);
            items--;
            Collections.sort(list, Collections.reverseOrder());
        }
        if (list.get(0) <= 0 && list.get(1) <= 0 && list.get(2) <= 0) {
            System.out.println("possible");
        } else System.out.println("impossible");
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
