import java.io.*;
import java.util.*;

public class RobotProtection {

    public static void main(String[] args) throws FileNotFoundException {
        Kattio in = new Kattio(System.in);

        int h = in.getInt();

        while (h != 0) {
            Set<Point> points = new HashSet<>();

            for (int i = 0; i < h; i++) {
                int x = in.getInt();
                int y = in.getInt();
                points.add(new Point(x, y));
            }

            // Sorting the points based on their x value, breaking ties with their y value
            List<Point> l = new ArrayList<>(points);
            l.sort(Comparator.comparing(Point::getX)
                    .thenComparing(Point::getY));

            Stack stack = graham(l);


            if (stack != null) {
                System.out.println(getArea(stack, (Point) stack.get(0)));
            } else {
                System.out.println(0.0);
            }

            h = in.getInt();
            if (h == 0) {
                return;
            }

        }

    }

    public static double getArea (Stack stack, Point startingPoint) {
        double sum = 0;

        for (int i = 1; i < stack.size() - 1; i++) {
            Point a = (Point) stack.get(i);
            Point b = (Point) stack.get(i + 1);
            double cross = ((a.x - startingPoint.x) * (b.y - startingPoint.y) - (a.y - startingPoint.y) * (b.x - startingPoint.x));
            sum += cross;
        }
        return Math.abs(sum / 2);
    }

    public static Stack<Point> graham (List<Point> list) {

        if (list.size() < 3) {
            return null;
        }

        Stack<Point> upper = new Stack<>();
        upper.push(list.get(0));
        upper.push(list.get(1));

        for (int i = 2; i < list.size(); i++) {
            upper.push(list.get(i));
            while (upper.size() >= 3 && turn(upper.get(upper.size()-3), upper.get(upper.size()-2), upper.get(upper.size()-1)) >= 0) {
                upper.remove(upper.get(upper.size()-2));
            }
        }

        Stack<Point> lower = new Stack<>();
        lower.push(list.get(list.size()-1));
        lower.push(list.get(list.size()-2));

        for (int i = list.size() - 3; i >= 0; i--) {
            lower.push(list.get(i));
            while (lower.size() >= 3 && turn(lower.get(lower.size()-3), lower.get(lower.size()-2), lower.get(lower.size()-1)) >= 0) {
                lower.remove(lower.get(lower.size()-2));
            }
        }

        upper.pop();
        lower.pop();

        upper.addAll(lower);

        return upper;
    }

    public static double turn(Point p1, Point p2, Point p3) {
        double d = (p2.x - p1.x)*(p3.y-p1.y) - (p2.y - p1.y)*(p3.x - p1.x);
        return d;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point () {

        }

        public int getX () {
            return this.x;
        }

        public int getY () {
            return this.y;
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
