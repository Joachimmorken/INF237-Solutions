import java.io.*;
import java.util.StringTokenizer;

import static java.lang.StrictMath.min;

public class MartianDNA {

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);
        int n = kattio.getInt(); //Length of Martian DNA
        int k = kattio.getInt(); //length of alphabet
        int r = kattio.getInt(); //num of requested nucleoclasses

        int counts[] = new int[k];
        int dnaString[] = new int[n];
        for (int i = 0; i < n; i++) {
            int dna = kattio.getInt();
            dnaString[i] = dna;
            counts[dna]++;
        }
        int lengthOfString = 0;

        int[] gotten = new int[k];
        int[] needed = new int[k];
        for (int i = 0; i < r; i++) {
            int nucleobase = kattio.getInt();
            int quantity = kattio.getInt();
            if (counts[nucleobase] < quantity) {
                System.out.println("impossible");
                return;
            }
            lengthOfString += quantity;
            needed[nucleobase] = quantity;
        }

        int j = 0;
        int i = 0;
        int res = Integer.MAX_VALUE;
        int count = 0;

        while (j < n || count >= lengthOfString) {
            if (count >= lengthOfString) {
                res = min(res, j - i);
                if (res == lengthOfString) {
                    System.out.println(res);
                    return;
                }
                while (gotten[dnaString[i]] > needed[dnaString[i]]) {
                    gotten[dnaString[i]]--;
                    i++;
                }
                res = min(res, j- i);
                if (gotten[dnaString[i]] > 0 && n != j) {
                    gotten[dnaString[i]]--;
                }
                count -= needed[dnaString[i++]];
            } else {
                gotten[dnaString[j]]++;
                if (gotten[dnaString[j]] == needed[dnaString[j]]) {
                    count += gotten[dnaString[j++]];
                } else {
                    j++;
                }
            }
        }
        System.out.println(res);
    }
         static class Kattio extends PrintWriter {
            public Kattio(InputStream i) {
                super(new BufferedOutputStream(System.out));
                r = new BufferedReader(new InputStreamReader(i));
            }

            public int getInt() {
                return Integer.parseInt(nextToken());
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
