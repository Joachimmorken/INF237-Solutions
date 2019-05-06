import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CitrusIntern {
    private static Kattio io;
    private static long[] in;
    private static long[] outDown;
    private static long[] outUp;
    private static Node[] employees;

    public static void main(String[] args) throws FileNotFoundException {
        io = new Kattio(System.in);
        int n = io.getInt();
        employees = new Node[n];

        in = new long[n];
        outDown = new long[n];
        outUp = new long[n];
        Node rootNode = readInput(n, employees); //rootNode will be the supreme leader without any superiors

        findMin(rootNode);
        System.out.println(Math.min(in[rootNode.id], outDown[rootNode.id]));

    }

    public static void findMin(Node node) {

        if (node.numChildren == 0) { //we hit a leaf
            outDown[node.id] = Long.MAX_VALUE;
            outUp[node.id] = 0;
            return;
        }

        long delta = Long.MAX_VALUE;

        
        for (int i = 0; i < node.numChildren; i++) {
            findMin(employees[node.childsIds[i]]); //dfs until we hit a child
            in[node.id] += outUp[node.childsIds[i]];
            outDown[node.id] += Math.min(in[node.childsIds[i]], outDown[node.childsIds[i]]);
            outUp[node.id] += Math.min(in[node.childsIds[i]], outDown[node.childsIds[i]]);
            delta = Math.min(Math.max((in[node.childsIds[i]] - outDown[node.childsIds[i]]), 0), delta);
        }
        outDown[node.id] += delta;

    }

    private static Node readInput(int n, Node[] employees) {
        int root = 1337;
        int[] rootArr = new int[n];

        for (int i = 0; i < n; i++) {
            Node node = new Node(i, io.getInt(), io.getInt());
            in[node.id] = node.cost;
            if (node.numChildren > 0) {
                int[] ids = new int[node.numChildren];
                for (int j = 0; j < ids.length; j++) {
                    ids[j] = io.getInt();
                    rootArr[ids[j]] = -1;
                }
                node.setChildsIds(ids);
            }
            employees[i] = node;
        }
        for (int i = 0; i < n; i++) {
            if (rootArr[i] == 0) {
                root = i;
                return employees[root];
            }
        }
        return employees[root];
    }


    static class Node {
        private int id;
        private int[] childsIds;
        private long cost;
        private int numChildren;


        public Node(int id, long cost, int children) {
            this.id = id;
            this.cost = cost;
            this.numChildren = children;
        }

        public void setChildsIds(int[] ids) {
            this.childsIds = ids;
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
