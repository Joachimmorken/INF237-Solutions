import java.io.*;
import java.util.StringTokenizer;

public class HiddenWords {
    static Node root;
    static boolean[][] visited;
    static String[][] grid;
    static int rows;
    static int cols;
    static int nwords;
    static String w;

    public static void main(String[] args) {
        Kattio in = new Kattio(System.in, System.out);
        rows = in.getInt();
        cols = in.getInt();
        root = new Node();
        grid = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] word = in.getWord().split("");
            for (int j = 0; j < cols; j++) {
                grid[i][j] = word[j];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited = new boolean[rows][cols];
                addToTrie(root, grid[i][j], i, j);
            }
        }

        nwords = in.getInt();
        int result = 0;
        for (int i = 0; i < nwords; i++) {
            w = in.getWord();
            if (search(root, w)) {
                result++;
            }
        }
        in.println(result);
        in.close();
    }

    private static void addToTrie(Node current, String c, int row, int col) {
        current = insert(current, c.charAt(0));
        if (current.depth == 10) {
            return;
        }
        visited[row][col] = true;
        //up
        if (row - 1 >= 0 && !visited[row - 1][col]) {
            addToTrie(current, grid[row - 1][col], row-1, col);
        }
        //down
        if (row + 1 < rows && !visited[row + 1][col]) {
            addToTrie(current, grid[row + 1][col], row + 1, col);
        }
        //right
        if (col + 1 < cols && !visited[row][col + 1]) {
            addToTrie(current, grid[row][col + 1], row, col +1);
        }
        if (col - 1 >= 0 && !visited[row][col - 1]) {
            addToTrie(current, grid[row][col - 1], row, col - 1);
        }
        visited[row][col] = false;
    }

    static boolean search (Node current, String word) {
        if (word.isEmpty()) {
            return false;
        }
        int index = word.charAt(0) - 'A';
        if (current.children[index] == null) {
            return false;
        }
        if (current.children[index].depth == w.length()) {
            return true;
        } else
        return search(current.children[index], word.substring(1));
    }

    static Node insert (Node current, char c) {
        int index = c - 'A';
        if (current.children[index] == null) {
            return current.children[index] = new Node(current.depth + 1);
        } else {
            return current.children[index];
        }
    }

    static class Node {
        Node[] children;
        int depth = 0;

        Node (int depth) {
            this.depth = depth;
            this.children = new Node[26];
        }

        Node() {
            this.children = new Node[26];
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