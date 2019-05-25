import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

// TLE

public class HiddenWordsTLE {
    private static String[][] grid;
    private static int r;
    private static int c;
    private static String[] s;
    private static int stringCounter;
    private static int result;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Kattio in = new Kattio(System.in, System.out);
        r = in.getInt();
        c = in.getInt();
        grid = new String[r][c];
        result = 0;

        HashMap<String, Boolean> map = new HashMap<>();

        for (int i = 0; i < r; i++) {
            String[] s = in.getWord().split("");
            for (int j = 0; j < c; j++) {
                grid[i][j] = s[j];
            }
        }

        int nwords = in.getInt();

        for (int i = 0; i < nwords; i++) {
            String word = in.getWord();
            s = word.split("");
            if (!map.containsKey(word)) {
                if (search()) {
                    result++;
                    map.put(word, true);
                } else {
                    map.put(word, false);
                }
            } else if (map.get(word)){
                result++;
            }
        }
        in.println(result);
        in.close();
    }

    public static boolean search() {
        stringCounter = 0;
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j].equals(s[stringCounter])) {
                    if (dfs(i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean dfs(int row, int column, int stringCounter) {
        stringCounter++;
        if (visited[row][column]) {
            return false;
        }
        visited[row][column] = true;

        if (stringCounter == s.length) {
            return true;
        }

        //up
        if (row - 1 >= 0 && grid[row - 1][column].equals(s[stringCounter]) && !visited[row - 1][column]) {
            if (dfs(row - 1, column, stringCounter)) {
                return true;
            }
        }
        //down
        if (row + 1 < r && grid[row + 1][column].equals(s[stringCounter]) && !visited[row + 1][column]) {
            if (dfs(row + 1, column, stringCounter)) {
                return true;
            }
        }
        //right
        if (column + 1 < c && grid[row][column + 1].equals(s[stringCounter]) && !visited[row][column + 1]) {
            if (dfs(row, column + 1, stringCounter)) {
                return true;
            }
        }
        //left
        if (column - 1 >= 0 && grid[row][column - 1].equals(s[stringCounter]) && !visited[row][column - 1]) {
            if (dfs(row, column - 1, stringCounter)) {
                return true;
            }
        }

        visited[row][column] = false;
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