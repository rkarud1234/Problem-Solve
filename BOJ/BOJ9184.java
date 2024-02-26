package BOJ;

import java.io.*;
import java.util.*;

public class BOJ9184 {
    private static int[][][] arr = new int[101][101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String tmp;
        while (!(tmp = br.readLine()).equals("-1 -1 -1")) {
            StringTokenizer st = new StringTokenizer(tmp);
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());
            int c = parse(st.nextToken());
            System.out.println("w(" + a + ", " + b + ", " + c + ") = " + w(a + 50, b + 50, c + 50));
        }
    }

    private static int w(int a, int b, int c) {
        if (a <= 50 || b <= 50 || c <= 50) {
            return arr[a][b][c] = 1;
        }

        if (a > 70 || b > 70 || c > 70) {
            return arr[70][70][70] = w(70, 70, 70);
        }

        if (arr[a][b][c] != 0) {
            return arr[a][b][c];
        }

        if (a < b && b < c)
            return arr[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        return arr[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
