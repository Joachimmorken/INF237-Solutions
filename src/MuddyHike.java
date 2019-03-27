import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class MuddyHike {
    private static int rows;
    private static int columns;
    //a given node in cost will represent the smallest cost of getting to that node
    private static int[][] cost;
    // represents the input as a 2d array
    private static int[][] grid;


    public static void main(String[] args) throws FileNotFoundException {
        Kattio in = new Kattio(System.in);
        rows = in.getInt();
        columns = in.getInt();
        cost = new int[rows][columns];
        grid = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Initially setting the cost of reaching a node to "infinity" for all nodes
                cost[i][j] = Integer.MAX_VALUE;
                grid[i][j] = in.getInt();
            }
        }
        prim();
    }

    public static void prim() {
        Queue<Edge> q = new PriorityQueue<>();
        // Finding the "arbitrary" vertex to start from
        // Candidates for this are all the vertices starting in the leftmost column
        for (int i = 0; i < rows;i++) {
            Edge edge = new Edge(i, 0, grid[i][0]);
            cost[i][0] = grid[i][0];
            q.add(edge);
        }

        int ans = Integer.MAX_VALUE;
        //int c = 0;
        while (!q.isEmpty()) {
            //popping the edge with the lowest weight
            Edge e = q.remove();
            int v1 = e.v1;
            int v2 = e.v2;
            int weight = e.weight;

            //if we reached the last column (i.e. the goal), update the answer if its less than current answer
            if (v2 == columns-1) {
                ans = Math.min(ans, cost[v1][v2]);
                break;
            }
            //System.out.println("From: " + v1 + ", To: " + v2 + ", Weight: " + weight + " Iteration: " + c++);


            //north
            if (v1 - 1 >= 0) {
                //if the max value of current nodes weight and nextnodes weight is less than the current cost..
                //of getting to the nextnode; update cost of getting there
                int nextNodesCost = Math.max(grid[v1-1][v2], weight);
                if (nextNodesCost < cost[v1 - 1][v2]) {
                    cost[v1 - 1][v2] = nextNodesCost;
                    q.add(new Edge(v1 - 1, v2, nextNodesCost));
                }
            }
            //south
            if (v1 + 1 < rows) {
                int nextNodesCost = Math.max(grid[v1+1][v2], weight);
                if (nextNodesCost < cost[v1 + 1][v2]) {
                    cost[v1 + 1][v2] = nextNodesCost;
                    q.add(new Edge(v1 + 1, v2, nextNodesCost));
                }
            }
            //west
            if (v2 - 1 >= 0) {
                int nextNodesCost = Math.max(grid[v1][v2 - 1], weight);
                if (nextNodesCost < cost[v1][v2 - 1]) {
                    cost[v1][v2 - 1] = nextNodesCost;
                    q.add(new Edge(v1, v2 - 1, nextNodesCost));
                }
            }
            //east
            if (v2 + 1 < columns) {
                int nextNodesCost = Math.max(grid[v1][v2+1], weight);
                if (nextNodesCost < cost[v1][v2 + 1]) {
                    cost[v1][v2 + 1] = nextNodesCost;
                    q.add(new Edge(v1, v2 + 1, nextNodesCost));
                }
            }
        }
        System.out.println(ans);
    }

    private static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int weight;

        private Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
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