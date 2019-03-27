import java.io.*;
import java.util.StringTokenizer;

public class PointsOfSnow {

    private static long[] segTree;
    private static long[] lazy;
    private static int[] lineland;
    private static long n;
    private long ans;

    public static void main(String[] args) throws FileNotFoundException {
        PointsOfSnow ps = new PointsOfSnow();
        ps.go();
    }

        public void go() throws FileNotFoundException {

        Kattio in = new Kattio(System.in);
        n = in.getLong();
        long k = in.getLong();
        long q = in.getLong();


        int height = (int) (Math.log(n) / Math.log(2) + 1);
        int nodes = (int) (Math.pow(2, height + 1)) - 1;

        //Arrays should be big enough now
        segTree = new long[10*(int)n - 1];
        lazy = new long[(int) n * 4];
        lineland = new int[(int)n];

        for (int i = 0; i < n; i++) {
            lineland[i] = 0;
        }


        for (int i = 0; i < k + q; i++) {
            String symbol = in.getWord();
            if (symbol.equals("!")) {
                long from = in.getLong();
                long to = in.getLong();
                long value = in.getLong();
                updateHelper(from, to - 1, value);
                //update
            } else {
                ans = 0;
                int x = in.getInt();
                long quer = queryHelper(x-1);
                System.out.println(quer);
            }
        }
    }

    public static long build(int[] arr, int pos, int left, int right) {
        if (left == right) return segTree[pos] = arr[left];

        int mid = (left + right) / 2;

        long leftChild = build(arr, 2 * pos + 1, left, mid);
        long rightChild = build(arr, 2 * pos + 2, mid + 1, right);
        //Improvements
        segTree[pos] = leftChild + rightChild;
        return segTree[pos];
    }

    public void updateHelper(long i, long j, long value) {
        update(0, 0, lineland.length - 1, i, j, value);
    }

    public void update(int node, long left, long right, long qleft, long qright, long value) {
        // The segment does not lie within range [qleft, qright]
        if (left > right || left > qright || right < qleft) {
            return;
        }

        if (left == right) {
            segTree[node] += value;
            return;
        }

        if (left >= qleft && right <= qright) {
            segTree[node] += value;
            return;
        }
        long mid = (left + right) / 2;
        update(node * 2 + 1, left, mid, qleft, qright, value);
        update(node * 2 + 2, mid + 1, right, qleft, qright, value);
        return;
    }

    public long queryHelper(int i) {
       return query(0, 0, lineland.length-1, i, i);
    }

    public long query(int node, long left, long right, int p, int p1) {

//        int leafNode = (int) ((n-1)+p);
//        ans += segTree[leafNode];
//
//        while (segTree[node % 2])

        if(left > right || left > p || right < p1) {
            return 0;
        }

        if (left == right) {
            ans += segTree[node];
            return ans;
        }

        ans += segTree[node];

        long mid = (left + right) / 2;
        query(node * 2 + 1, left, mid, p, p1);
        query(node * 2 + 2, mid + 1, right, p, p1);
        return ans;
    }



    //node is the node to be updated
    //left is the start of the array
    //right is the end of the array
    //qleft is the start index of the query
    //qright is the end index of the query

//    public static void updateRange(int node, long left, long right, long qleft, long qright, long value) {
//
//        //Node already have pending updates, and we need to do these update FIRST
//        // if lazy[node] == 0 then the subtree is updated, i.e. if lazy[node] == 0, then the whole tree is updated
//        if (lazy[node] != 0) {
//            long lo = (right - left + 1) * value;
//            segTree[node] += lo;
//
//            //Checking if its a leaf node
//            if (left != right) {
//                // Marking the childs as lazy (not updated)
//                lazy[node * 2 + 1] += lazy[node]; //Adding the value of parent to lazy children
//                lazy[node * 2 + 2] += lazy[node];
//            }
//            // Resetting the parent node to updated
//            lazy[node] = 0;
//        }
//
//        // The segment does not lie within range [qleft, qright]
//        if (left > right || left > qright || right < qleft) {
//            return;
//        }
//        // Total overlap, so do lazy steps:
//        if (left >= qleft && right <= qright) {
//            // 1. set the parent node to the value of itself + child
//            segTree[node] += (right - left + 1) * value;
//
//            // mark the children as lazy with the accumulated value so far
//            if (left != right) {
//                lazy[node * 2 + 1] += value;
//                lazy[node * 2 + 2] += value;
//            }
//            return;
//        }
//
//        //Partial overlap so we need to recurse on more children
//        long mid = (left + right) / 2;
//        updateRange(node * 2 + 1, left, mid, qleft, qright, value); //updating leftchild
//        updateRange(node * 2 + 2, mid + 1, right, qleft, qright, value); //updating rightchild
//        segTree[node] = segTree[2 * node + 1] + segTree[2 * node + 2]; //updating parent of l + r
//
//    }
//
//    public static long query(int node, long left, long right, long qleft, long qright) {
//        if(left > right || left > qright || right < qleft) {
//            return 0;
//        }
//        if (lazy[node] != 0) {
//            long lo = (right - left + 1) * lazy[node];
//            segTree[node] += lo;
//
//
//            if (left != right) {
//                lazy[2 * node + 1] += lazy[node];
//                lazy[2 * node + 2] += lazy[node];
//            }
//            lazy[node] = 0;
//        }
//
//        if (left >= qleft && right <= qright) {
//            return segTree[node];
//        }
//        long mid = (left + right) / 2;
//        long s1 = query(node * 2 + 1, left, mid, qleft, qright);
//        long s2 = query(node * 2 + 2, mid + 1, right, qleft, qright);
//        return (s1+s2);
//    }
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
