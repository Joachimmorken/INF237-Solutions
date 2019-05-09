import java.io.*;
import java.util.*;

public class SimplePolygon {

    public static void main(String[] args) {
        Kattio in = new Kattio(System.in);

        int c = in.getInt();

        while (c-- > 0) {
            int n = in.getInt();
            ArrayList<Point> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int x = in.getInt();
                int y = in.getInt();
                list.add(new Point(x, y, i));
            }

            // Sorting the points based on their x value, breaking ties with their y value
            Collections.sort(list);

            Point leftMost = list.get(0);
            Point rightMost = list.get(n - 1);

            ArrayList<Point> over = new ArrayList<>();
            ArrayList<Point> under = new ArrayList<>();
            ArrayList<Point> colinear = new ArrayList<>();
            // Partitioning the set into points A under the line formed by leftMost+rightMost and points B over the line
            for (int i = 1; i < list.size() - 1; i++) {
                int res = pointToLine(leftMost, rightMost, list.get(i));
                if (res == 1) {
                    over.add(list.get(i));
                }
                else if (res == 0) {
                    under.add(list.get(i));
                    colinear.add(list.get(i));
                } else {
                    under.add(list.get(i));
                }
            }

            Collections.reverse(over);

            ArrayList<Integer> polygon = new ArrayList<>();
            polygon.add(leftMost.index);
            for (Point p : under) {
                polygon.add(p.index);
            }
            polygon.add(rightMost.index);
            for (Point p : over) {
                polygon.add(p.index);
            }
            if (over.isEmpty() || under.isEmpty()) {
                if (!colinear.isEmpty()) {
                    int cnt = 0;
                    for (Point p : colinear) {
                        for (int i = polygon.size() - 1; i >= 0; i--) {
                            cnt++;
                            if (p.index != polygon.get(i)) {
                                Collections.swap(polygon, polygon.size()-1, polygon.size()-1-cnt);
                                break;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < polygon.size(); i++) {
                System.out.print(polygon.get(i) + " ");
            }
            if (c == 0) {
                return;
            }
            System.out.println();
        }
    }

    public static int pointToLine (Point left, Point right, Point p) {
        double res = (right.x - left.x) * (p.y - left.y) - (right.y - left.y) * (p.x - left.x);

         // Point is over the line (or to the left)
        if (res > 0) {
            return 1;
        }
        // Point is under the line (or to the right)
        if (res < 0) {
            return -1;
        }
        // Point is exactly on the line
        else return 0;
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int index;

        public Point(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        @Override
        public int compareTo(Point other) {
            int res = Integer.compare(x, other.x);
            if (res == 0) {
                res = Integer.compare(y, other.y);
            }
            return res;
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
