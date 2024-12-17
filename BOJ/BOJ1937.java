package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1937 {
    private static int n;
    private static int[][] forest;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = parse(br.readLine());
        int ans = 0;
        forest = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            forest[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(BOJ1937::parse)
                    .toArray();
        } // init

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(dfs(i, j), ans);
            }
        }

        System.out.println(ans);
    }

    private static final int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int dfs(int i, int j) {
        if (dp[i][j] > 0)
            return dp[i][j];
        int m = 0;

        for (int[] d : dir) {
            int ni = i + d[0];
            int nj = j + d[1];

            if (!isIndexValid(ni, nj) || forest[ni][nj] <= forest[i][j])
                continue;

            m = Math.max(dfs(ni, nj), m);
        }

        dp[i][j] = m + 1;
        return dp[i][j];
    }

    private static boolean isIndexValid(int i, int j) {
        return i >= 0 && j >= 0 && i < n && j < n;
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
