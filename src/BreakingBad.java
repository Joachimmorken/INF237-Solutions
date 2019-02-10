import java.io.*;
import java.util.*;

public class BreakingBad {
    private static Map<String, HashSet<String>> adjacencyList;
    private static List<String> listOfItems;
    private static Set<String> walterInventory = new HashSet<>();
    private static Set<String> jesseInventory = new HashSet<>();
    private static int numOfItems;
    private static boolean done;

    public static void main(String[] args) throws FileNotFoundException {
        Kattio kattio = new Kattio(System.in);
        listOfItems = new ArrayList<>();
        adjacencyList = new HashMap<>();
        // Find all items
        numOfItems = kattio.getInt();
        for (int i = 0; i < numOfItems; i++) {
            String item = kattio.getWord();
            listOfItems.add(item);
            //Creating adjacencyList
            adjacencyList.put(item, new HashSet<>());
        }
        //Filling the adj.list
        int numOfPairs = kattio.getInt();
        for (int i = 0; i < numOfPairs; i++) {
            addEdge(kattio.getWord(), kattio.getWord());
        }
        //Running search
        done = true;
        for (int i = 0; i < numOfItems; i++) {
            if (walterInventory.size()+jesseInventory.size()==numOfItems) {
                break;
            } else {
                search(listOfItems.get(i));
            }
        }
        if (done) {
            print();
        } else {
            System.out.println("impossible");
        }
    }

    private static void search(String item1) {
        if (!walterInventory.contains(item1) && !jesseInventory.contains(item1)) {
            walterInventory.add(item1);
        }
        for (String adjacencyItem : adjacencyList.get(item1)) {
            if (!walterInventory.contains(adjacencyItem) && walterInventory.contains(item1) && !jesseInventory.contains(adjacencyItem)) {
                jesseInventory.add(adjacencyItem);
                search(adjacencyItem);
            } else if (!walterInventory.contains(adjacencyItem) && !jesseInventory.contains(adjacencyItem) && jesseInventory.contains(item1)) {
                walterInventory.add(adjacencyItem);
                search(adjacencyItem);
            } else if ((walterInventory.contains(item1) && walterInventory.contains(adjacencyItem)) || (jesseInventory.contains(item1) && jesseInventory.contains(adjacencyItem))) {
                done = false;
                break;
            }
        }
    }

    private static void print() {
        for (String s : walterInventory) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : jesseInventory) {
            System.out.print(s + " ");
        }
    }

    private static void addEdge (String s1, String s2) {
        adjacencyList.get(s1).add(s2);
        adjacencyList.get(s2).add(s1);
    }

    private static class Kattio extends PrintWriter {
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