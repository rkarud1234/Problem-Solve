package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2072 {

    // 점검할 방향
    // 가로, 세로, 위대각선(/), 아래대각선(\) 순의 방향
    private static int[][] move = {{0, 1}, {1, 0}, {1, -1}, {1, 1}};
    private static int[][] map = new int[20][20]; // 바둑판

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parse(br.readLine()); // 돌의 개수

        for (int idx = 1; idx <= n; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int i = parse(st.nextToken());
            int j = parse(st.nextToken());

            map[i][j] = (idx % 2 == 1) ? 1 : 2; // 1: 흑, 2: 백

            // 돌을 놓은 곳부터 오목이 됐는지 체크
            if (go(i, j)) {
                System.out.println(idx);
                return;
            }
        }
        System.out.println("-1");
    }

    private static boolean go(int i, int j) {
        // 방향별로 체크
        for (int d = 0; d < 4; d++) {
            // 몇 개가 이어져 있는지
            int cnt = 1;
            cnt += continuous(i, j, move[d][0], move[d][1]);
            cnt += continuous(i, j, move[d][0] * -1, move[d][1] * -1);

            if (cnt == 5) {
                return true;
            }
        }
        return false;
    }

    private static int continuous(int i, int j, int di, int dj) {
        int ni = i + di;
        int nj = j + dj;

        int cnt = 0;

        while (checkIdx(ni, nj) && map[ni][nj] == map[i][j]) {
            cnt++;
            ni += di;
            nj += dj;
        }

        return cnt;
    }

    private static boolean checkIdx(int i, int j) {
        return i >= 1 && j >= 1 && i <= 19 && j <= 19;
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
