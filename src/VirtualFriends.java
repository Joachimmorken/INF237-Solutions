import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class VirtualFriends {

    private static int[] size;
    private static int[] parent;
    private static Kattio kattio;

    public static void main(String[] args) throws IOException {
        kattio = new Kattio(System.in, System.out);

        int cases = kattio.getInt();
        while (cases-- > 0) {
            HashMap<String, Integer> map = new HashMap<>();
            int friendships = kattio.getInt();
            size = new int[100001];
            parent = new int[100001];

            for (int i = 0; i <= friendships;i++) {
                parent[i] = i;
                size[i] = 1;
            }
            for (int i=0; i < friendships;i++) {
                String s1 = kattio.getWord();
                String s2 = kattio.getWord();
                if (s1.equals("")) {
                    break;
                }

                if (!map.containsKey(s1)) {
                    map.put(s1, map.size());
                }
                if (!map.containsKey(s2)) {
                    map.put(s2, map.size());
                }
                union(map.get(s1), map.get(s2));
            }
        }
        kattio.flush();
        kattio.close();
    }

    public static int findRoot(int i) {
        //find the root from a given node
        while (parent[i] != i) {
            //compressing along the way to the root
            return parent[i] = findRoot(parent[i]);
        }
        return i;
    }

    public static void union (int i, int j) throws IOException {
        int root_i = findRoot(i);
        int root_j = findRoot(j);

        //same root
        if (root_i==root_j) {
            kattio.write(String.valueOf(size[root_i]));
            kattio.write("\n");
            return;
        }
        //Adding to the biggest component
        if (size[root_i]>size[root_j]) {
            parent[root_j] = root_i;
            size[root_i] += size[root_j];
            int output = size[root_i];
            kattio.write(String.valueOf(output));
            kattio.write("\n");
        }
        else {
            parent[root_i] = root_j;
            size[root_j] += size[root_i];
            int output = size[root_j];
            kattio.write(String.valueOf(output));
            kattio.write("\n");
        }
    }
    static class Kattio extends PrintWriter {
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
