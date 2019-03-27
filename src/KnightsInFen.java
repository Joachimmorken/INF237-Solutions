import java.io.*;
import java.util.*;

public class KnightsInFen {
    private static int[][] board;
    private static int[][] goal;
    private static BufferedReader in;
    private static Queue<Node> frontier;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());
        frontier = new PriorityQueue<>();

        //Goal state
        goal = new int[][]{        {1, 1, 1, 1, 1},
                                   {0, 1, 1, 1, 1},
                                   {0, 0, 2 , 1, 1},
                                   {0, 0, 0, 0, 1},
                                   {0, 0, 0, 0, 0}
        };


        while (cases-- > 0) {
            board = new int[5][5];
            frontier.clear();
            board = getBoard();
            Pair blankPosition = new Pair();
            blankPosition = findBlank(board);
            int ans = astar(blankPosition.x, blankPosition.y);
            if (ans < 11) {
                System.out.println("Solvable in " + ans + " move(s).");
            } else {
                System.out.println("Unsolvable in less than 11 move(s).");
            }
        }
    }

    public static Pair findBlank(int[][] b) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (b[i][j] == 2) return new Pair(i, j);
            }
        }return null;
    }

    public static int astar(int i, int j) {
        if (!frontier.isEmpty()) {
            Node firstNode = frontier.peek();
            firstNode.setF(heuristic(board));
        }
        Set<Node> expanded = new HashSet<>();

        while (!frontier.isEmpty()) {
            Node node = frontier.peek(); frontier.remove();
            int incorrect = heuristic(node.getState().getBoard());
            if (incorrect == 0 || node.moves > 10) {
                return node.moves;
            }
            if (!expanded.contains(node)) {
                expanded.add(node);
                List<Pair> list = new ArrayList<>();
                list = getPossibleMoves(node);

                State currState = node.getState();
                int[][] currboard = currState.getBoard();
                for (Pair nextPosition : list) {
                    Node blankSpace = new Node(nextPosition.x, nextPosition.y); //this will be the new blankspace
                    int[][] b = new int[5][5];
                    b = deepCopy(currboard);
                    int two = b[node.r][node.c];
                    b[node.r][node.c] = b[nextPosition.x][nextPosition.y];
                    b[nextPosition.x][nextPosition.y] = two;
                    int n = node.getMoves();
                    blankSpace.setF(n+1 + heuristic(b));
                    blankSpace.setMoves(node.getMoves() + 1);
                    blankSpace.setState(new State(b));
                    frontier.add(blankSpace);
                }
            }
        }
        return 1000;
    }

    public static int[][] deepCopy(int[][] b) {
        int[][] newBoard = new int[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                newBoard[r][c] = b[r][c];
            }
        }
        return newBoard;
    }

    // heuristic == number of wrongly placed knights
    public static int heuristic(int[][] b) {
        int inPosition = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j< 5; j++) {
                if (b[i][j] == goal[i][j]) inPosition++;
            }
        }
        return 25 - inPosition;
    }

    public static int[][] getBoard() throws IOException {
        Pair pair = new Pair();
        int[][] b = new int[5][5];
        for (int i = 0; i < 5; i++) {
            String row = in.readLine();
            for (int j = 0; j < 5; j++) {
                char symb = row.charAt(j);
                if (j == 3 && row.length() == 4) {
                    if (symb == '0') b[i][j] = 0;
                    if (symb == '1') b[i][j] = 1;
                    pair = new Pair(i, 4);
                    b[i][4] = 2;
                    break;
                }
                if (symb == '0') b[i][j] = 0;
                if (symb == '1') b[i][j] = 1;
                if (symb == ' ') {
                    pair = new Pair(i, j);
                    b[i][j] = 2;
                }
            }
        }
        Node node = new Node(pair.x, pair.y);
        node.setState(new State(b));
        frontier.add(node);
        return b;
    }

    // gets possible moves to a given blank square
    public static List<Pair> getPossibleMoves(Node node) {
        //northwest
        List<Pair> list = new ArrayList<>();
        int i = node.r;
        int j = node.c;
        if ((i - 2) >= 0 && (j - 1) >= 0) {
            list.add(new Pair(i-2, j-1));
        }
        //northeast
        if (((i - 2) >= 0 && (j + 1) < 5)) {
            list.add(new Pair(i-2, j+1));
        }
        //westnorth
        if (((i - 1) >= 0 && (j - 2) >= 0)) {
            list.add(new Pair(i-1, j-2));

        }
        //eastnorth
        if (((i - 1) >= 0 && (j + 2) < 5)) {
            list.add(new Pair(i-1, j+2));

        }
        //southwest
        if (((i + 2) < 5 && (j - 1) >= 0)) {
            list.add(new Pair(i+2, j-1));

        }
        //southeast
        if (((i + 2) < 5 && (j + 1) < 5)) {
            list.add(new Pair(i+2, j+1));

        }
        //westsouth
        if (((i + 1) < 5 && (j - 2) >= 0)) {
            list.add(new Pair(i+1, j-2));

        }
        //eastsouth
        if (((i + 1) < 5 && (j + 2) < 5)) {
            list.add(new Pair(i+1, j+2));
        }
        return list;
    }

    static class Node implements Comparable<Node> {
        int moves;
        State state;
        int r;
        int c;
        int g; // cost (depth)
        int h; // heuristic (num of correct pieces, could be improved)
        int f = g + h; // magic formula of a*

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getMoves() {
            return moves;
        }

        public void setMoves(int moves) {
            this.moves = moves;
        }

        public int getF() {
            return f;
        }

        public void setF(int f) {
            this.f = f;
        }
        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        @Override
        public int compareTo(Node o) {
            return this.f - o.f;
        }
    }

    static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Pair(){}
    }
    static class State {
        int[][] board;

        public State(int[][] board) {
            this.board = board;
        }

        public int[][] getBoard() {
            return board;
        }

        public void setBoard(int[][] board) {
            this.board = board;
        }
    }
}
