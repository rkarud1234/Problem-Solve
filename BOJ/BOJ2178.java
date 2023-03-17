package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2178 {
    private static int N, M;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parse(st.nextToken());
        M = parse(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<Point> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];

        q.offer(new Point(0, 0));
        visit[0][0] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;

            while (size-- > 0) {
                Point p = q.poll();

                if (p.i == N - 1 && p.j == M - 1) {
                    return cnt;
                }

                for (int d = 0; d < 4; d++) {
                    int ni = p.i + move[d][0];
                    int nj = p.j + move[d][1];

                    if (checkIdx(ni, nj) && map[ni][nj] == '1' && !visit[ni][nj]) {
                        visit[ni][nj] = true;
                        q.offer(new Point(ni, nj));
                    }
                }
            }
        }

        return -1;
    }

    private static boolean checkIdx(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M;
    }

    private static class Point {
        int i, j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
