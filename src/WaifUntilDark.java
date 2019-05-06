import java.io.*;
import java.util.*;

public class WaifUntilDark {
    static List<Edge>[] graph;
    static Kattio in;

    public static void main(String[] args) {
        in = new Kattio(System.in);
        int children = in.getInt(); // 1 indexed by input
        int toys = in.getInt(); // 1 indexed by input
        int categories = in.getInt();

        int n = children + toys + categories + 2;
        int s = 0;
        int t = n-1;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        fillGraph(children, toys, categories, t);
        System.out.println(solve(n, s, t));

    }

    static boolean[] visited;
    static Edge[] path;

    // Edmonds-Karp
    public static int solve(int n, int s, int t) {
        int maxFlow = 0;

        while (bfs(n, s, t)) {
            int bottleNeck = Integer.MAX_VALUE;
            for (int i = t; i != s; i = path[i].source) {
                bottleNeck = Math.min(bottleNeck, path[i].getRemainingCapacity());
            }
            for (int i = t; i != s; i = path[i].source) {
                path[i].flow += bottleNeck;
                path[i].reverse.flow -= bottleNeck;
            }
            maxFlow += bottleNeck;
        }
        return maxFlow;
    }


    public static boolean bfs(int n, int s, int t) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        visited = new boolean[n];
        path = new Edge[n];

        while (!q.isEmpty()) {
            int vertex = q.poll();
            if (vertex == t) {
                break;
            }
            for (Edge edge : graph[vertex]) {
                if (edge.getRemainingCapacity() > 0 && !visited[edge.target]) {
                    visited[edge.target] = true;
                    path[edge.target] = edge;
                    q.add(edge.target);
                }
            }
        }
        return visited[t];
    }

    public static void fillGraph(int n, int m, int p, int t) {
        for (int i = 1; i <= n; i++) {
            addEdge(i, 0, 1);
            int toys = in.getInt();
            for (int j = 1; j <= toys; j++) {
                int toy = in.getInt();
                addEdge(n+toy, i, 1);
            }
        }

        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= p; i++) {
            int toys = in.getInt();
            int idx = n+m+i;

            for (int j = 1; j <= toys; j++) {
                int category = in.getInt();
                set.add(category);
                addEdge(idx, n+category, 1);
            }
            addEdge(t, idx, in.getInt());
        }

        for (int i = 1; i <= m; i++) {
            if (!set.contains(i)) {
                addEdge(t, n+i, 1);
            }
        }
    }

    public static void addEdge(int to, int from, int capacity) {
        Edge forward = new Edge(to, from, capacity);
        Edge backward = new Edge(from, to, 0);
        forward.setReverse(backward); backward.setReverse(forward);
        graph[from].add(forward); graph[to].add(backward);
    }

    static class Edge {
        int target;
        int source;
        int flow;
        int capacity;
        Edge reverse;

        public Edge (int target, int source, int capacity) {
            this.target = target;
            this.source = source;
            this.capacity = capacity;
        }

        public int getRemainingCapacity() {
            return this.capacity - this.flow;
        }

        public void setReverse(Edge edge) {
            this.reverse = edge;
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
