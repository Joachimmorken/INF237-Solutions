import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Sheba {
    private static int rows;
    private static int columns;

    public static void main(String[] args) throws FileNotFoundException {
        Kattio kattio = new Kattio(System.in);
        rows = kattio.getInt();
        columns = kattio.getInt();
        char[][] matrix = new char[rows][columns];
        boolean[][] visited = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            String s = kattio.getWord();
            for (int y = 0; y < columns; y++) {
                matrix[i][y] = s.charAt(y);
            }
        }

        int numOfAmoebas = 0;
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (matrix[i][j] == '#' && !visited[i][j]) {
                    numOfAmoebas++;
                    dfs(matrix, i, j, visited);
                }
            }
        }
        System.out.println(numOfAmoebas);
    }
    private static void dfs(char[][] matrix, int i, int j, boolean[][] visited) {

        if (!(visited[i][j])) {
            visited[i][j] = true;
            //up
            if (i - 1 >= 0 && matrix[i - 1][j] == '#') {
                dfs(matrix, i - 1, j, visited);
            }

            //Down
            if (i + 1 < rows && matrix[i + 1][j] == '#') {
                dfs(matrix, i + 1, j, visited);
            }
            //left
            if (j - 1 >= 0 && matrix[i][j - 1] == '#') {
                dfs(matrix, i, j - 1, visited);
            }
            //right
            if (j + 1 < columns && matrix[i][j + 1] == '#') {
                dfs(matrix, i, j + 1, visited);
            }
            //upleft
            if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1][j - 1] == '#') {
                dfs(matrix, i - 1, j - 1, visited);
            }
            //upright
            if (i - 1 >= 0 && j + 1 < columns && matrix[i - 1][j + 1] == '#') {
                dfs(matrix, i - 1, j + 1, visited);
            }
            //downleft
            if (i + 1 < rows && j - 1 >= 0 && matrix[i + 1][j - 1] == '#') {
                dfs(matrix, i + 1, j - 1, visited);
            }
            //downright
            if (i + 1 < rows && j + 1 < columns && matrix[i + 1][j + 1] == '#') {
                dfs(matrix, i + 1, j + 1, visited);
            }
        }
    }
    static class Kattio extends PrintWriter {
        public Kattio(InputStream i) {
            super(new BufferedOutputStream(System.out));
            r = new BufferedReader(new InputStreamReader(i));
        }

        public int getInt() {
            return Integer.parseInt(nextToken());
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
