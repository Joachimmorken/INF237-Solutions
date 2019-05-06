import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WhiteWaterRafting {
    public static double min;
    public static void main(String[] args) throws FileNotFoundException {

        Point p11 = new Point(-10, 10);
        Point p22 = new Point(10, 10);

        Point p33 = new Point(5, 5);

        System.out.println(dot(new Point(1, 0), new Point(15, -5)));

        System.out.println(shortestDistance(p11, p22, p33));


        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();

            while (cases-- > 0) {
                min = Double.MAX_VALUE;
                List<Point> innerPoints = new ArrayList<>();
                List<Point> outerPoints = new ArrayList<>();
                List<Line> innerLines = new ArrayList<>();
                List<Line> outerLines = new ArrayList<>();
                int points = in.nextInt();
                for (int i = 0; i < points; i++) {
                    Point p1 = new Point(in.nextInt(), in.nextInt());
                    innerPoints.add(p1);
                    if (i != 0) {
                        Line line = new Line(innerPoints.get(i - 1), p1);
                        innerLines.add(line);
                        if (i == points - 1) {
                            Line aLine = new Line(innerPoints.get(0), p1);
                            innerLines.add(aLine);
                            break;
                        }
                    }
                }
                int points2 = in.nextInt();
                for (int i = 0; i < points2; i++) {
                    Point p1 = new Point(in.nextInt(), in.nextInt());
                    outerPoints.add(p1);
                    if (i != 0) {
                        Line line = new Line(outerPoints.get(i - 1), p1);
                        outerLines.add(line);
                        if (i == points2 - 1) {
                            Line aLine = new Line(outerPoints.get(0), p1);
                            outerLines.add(aLine);
                            break;
                        }
                    }
                }

                for (Point p : innerPoints) {
                    for (Point p2 : outerPoints) {
                        min = Math.min(min, pointDist(p, p2));
                    }
                }

                for (Point p : innerPoints) {
                    for (Line line : outerLines) {
                        //System.out.println(shortestDistance(line.a, line.b, p));
                        min = Math.min(min, shortestDistance(line.a, line.b, p));
                    }
                }

                for (Point p : outerPoints) {
                    for (Line line : innerLines) {
                        min = Math.min(min, shortestDistance(line.a, line.b, p));
                    }
                }

                BigDecimal b = new BigDecimal(min / 2);
                System.out.println(b)git ;
            }
    }

    public static double pointDist(Point p1, Point p2) {
        double distance = (Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2)));
        return distance;
    }


    public static double shortestDistance(Point p1, Point p2, Point single) {
        //P2 - P1
        Point direction = new Point(p2.x - p1.x, p2.y - p1.y); //DIRECTION VECTOR AB

        double a = direction.x / Math.sqrt(Math.pow(direction.x,2) + Math.pow(direction.y, 2));
        double b = direction.y / Math.sqrt(Math.pow(direction.x,2) + Math.pow(direction.y, 2));
        Point v = new Point(a, b); // THIS WILL BE THE UNIT VECTOR

        double dot1 = dot(p1, v);
        double dot2 = dot(single, v);
        double d = dot2 - dot1;

        if (d < 0) {
            return pointDist(p1, single);
        }

        if (d > pointDist(p1, p2)) {
            return pointDist(p2, single);
        }

        double e1 = p1.x + d * v.x;
        double e2 = p1.y + d * v.y;

        double diff1 = single.x - e1;
        double diff2 = single.y - e2;

        double res = magnitude(diff1, diff2);


        return res;
    }

    public static double magnitude (double a, double b) {
        double A = 0;
        A = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        return A;
    }

    public static double dot(Point p1, Point p2) {
        double dot = p1.x * p2.x + p1.y*p2.y;
        return dot;
    }

        static class Point {
            double x;
            double y;
            public Point(double x, double y) {
                this.x = x;
                this.y = y;
            }
        }
        static class Line {
            Point a;
            Point b;

            public Line(Point a, Point b) {
                this.a = a;
                this.b = b;
            }
        }
}

