import java.io.*;
import java.util.*;

public class BookClub {

    private static int interests;
    private static int people;
    private static boolean[] seen;
    private static int[] edges;
    private static HashMap<Integer, ArrayList<Integer>> map;

    public static void main(String[] args) {
        Kattio in = new Kattio(System.in);
        people = in.getInt();
        interests = in.getInt();

        edges = new int[people];
        Arrays.fill(edges, -1);

        map = new HashMap<>();

        for (int i = 0; i < interests; i++) {
            int id = in.getInt();
            int bookWanted = in.getInt();

            if (map.containsKey(id)) {
                map.get(id).add(bookWanted);
            } else {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(bookWanted);
                map.put(id, l);
            }
        }
        int res = 0;
        for (int i = 0; i < interests; i++) {
            seen = new boolean[people];

            if (dfs(i, seen)) {
                res++;
            }
        }
        if (res == people) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }


    private static boolean dfs(int interestedReader, boolean[] seen) {
        List<Integer> list = map.get(interestedReader); //books reader is interested in

        if (list == null) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            int book = list.get(i); //current book we are trying

            if (!seen[book]) {
                seen[book] = true;

                if (edges[book] == -1 || dfs(edges[book], seen)) {
                    edges[book] = interestedReader;
                    return true;
                }
            }
        }
        return false;
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
