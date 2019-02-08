import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Islands {
    private static char[][] matrix;
    private static boolean[][] visited;
    private static int rows = 0;
    private static int columns = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        rows = scanner.nextInt();
        columns = scanner.nextInt();
        visited = new boolean[rows][columns];
        matrix = new char[rows][columns];

        // Filling the matrix
        // Makes for easier traversing
        for (int i = 0; i < rows; i++) {
            String s = scanner.next();
            for (int y = 0; y < columns; y++) {
                matrix[i][y] = s.charAt(y);
            }
        }
        int total = findIslands(matrix);
        System.out.println(total);
    }

    public static int findIslands(char[][] matrix) {
        int numOfIslands = 0;
        for (int i = 0; i < rows; i++) {
            for (int y = 0; y < columns; y++) {
                if (matrix[i][y] == 'L' && !visited[i][y]) {
                    numOfIslands++;
                    dfs(matrix, i, y, visited);
                }
            }
        }
        return numOfIslands;
    }

    public static void dfs(char[][] matrix, int row, int column, boolean[][] visited) {

        if (!visited[row][column]) {
            visited[row][column] = true;

            //CheckUp
            if (row - 1 >= 0 && (matrix[row - 1][column] == 'L' || matrix[row - 1][column] == 'C')) {
                dfs(matrix, row - 1, column, visited);
            }
            //CheckDown
            if (row + 1 < rows && (matrix[row + 1][column] == 'L' || matrix[row + 1][column] == 'C')) {
                dfs(matrix, row + 1, column, visited);
            }
            //CheckLeft
            if (column - 1 >= 0 && (matrix[row][column - 1] == 'L' || matrix[row][column - 1] == 'C')) {
                dfs(matrix, row, column - 1, visited);
            }
            //CheckRight
            if (column + 1 < columns && (matrix[row][column + 1] == 'L' || matrix[row][column + 1] == 'C')) {
                dfs(matrix, row, column + 1, visited);
            }
        }
    }
}
