import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Jabuke {

    public static void main(String[] args) throws FileNotFoundException{
        List<Vector> list = new ArrayList<>();
        Kattio in = new Kattio(System.in);

        for (int i = 0; i < 3; i++) {
            int x = in.getInt();
            int y = in.getInt();
            list.add(new Vector(x, y));
        }
        Vector a = list.get(0);
        Vector b = list.get(1);
        Vector c = list.get(2);

        //ABC (Antes land)
        double land = Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y))/2.0;
        System.out.println(land);

        int treesInside = 0;
        int ntrees = in.getInt();

        //ntrees
        for (int i = 0; i < ntrees; i++) {
            Vector p = new Vector(in.getInt(), in.getInt());
            //PBC
            double PBC = Math.abs(p.x * (b.y - c.y) + b.x * (c.y - p.y) + c.x * (p.y - b.y)) / 2.0;
            //APC
            double APC = Math.abs(a.x * (p.y - c.y) + p.x * (c.y - a.y) + c.x * (a.y - p.y)) / 2.0;
            //ABP
            double ABP = Math.abs(a.x * (b.y - p.y) + b.x * (p.y - a.y) + p.x * (a.y - b.y)) / 2.0;
            //for the point to be inside, the area of PBC, APC and ABP hase to equal ABC
            if (PBC + APC + ABP == land) treesInside++;
        }
        System.out.println(treesInside);
    }

    static class Vector {
        int x;
        int y;

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
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
