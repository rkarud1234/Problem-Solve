package BOJ;

import java.io.*;

public class BOJ9625 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = parse(br.readLine());

        int[][] count = new int[k + 1][2];
        count[0][0] = 1;
        count[0][1] = 0;
        count[1][0] = 0;
        count[1][1] = 1;

        for (int i = 2; i <= k; i++) {
            count[i][0] = count[i - 2][0] + count[i - 1][0];
            count[i][1] = count[i - 2][1] + count[i - 1][1];
        }

        System.out.println(count[k][0] + " " + count[k][1]);
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
