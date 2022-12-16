package BOJ;

import java.io.*;
import java.util.*;

public class BOJ7569 {

    private static Queue<Point> queue = new LinkedList<>();
    private static int M, N, H, tot;
    private static int[][][] box;
    private static int[][] move = {{0, 0, 1}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = parse(st.nextToken()); // 가로
        N = parse(st.nextToken()); // 세로
        H = parse(st.nextToken()); // 높이

        box = new int[M][N][H]; // 토마토 상자

        tot = M * N * H;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    box[k][j][i] = parse(st.nextToken());
                    if (box[k][j][i] != 0) {
                        tot--;
                    }
                    if (box[k][j][i] == 1) {
                        queue.offer(new Point(k, j, i));
                    }
                }
            }
        } // end Input

        int day = -1;
        // BFS를 통해 토마토 넓히기
        while (!queue.isEmpty()) {
            day++;
            bfs();
        }

        System.out.println(tot == 0 ? day : -1);

    }

    private static void bfs() {
        int size = queue.size();

        while (size-- > 0) {
            Point p = queue.poll();

            // xyz 세 축의 근접한 토마토칸을 체크(상하좌우, 위아래)
            for (int d = 0; d < 6; d++) {
                int nm = p.m + move[d][0];
                int nn = p.n + move[d][1];
                int nh = p.h + move[d][2];

                // 인덱스를 초과하거나 안 익은 토마토가 아닌 경우
                if (!checkIdx(nm, nn, nh) || box[nm][nn][nh] != 0) {
                    continue;
                }

                queue.offer(new Point(nm, nn, nh));
                box[nm][nn][nh] = 1;
                tot--;
            }
        }
    }

    private static boolean checkIdx(int m, int n, int h) {
        return m >= 0 && n >= 0 && h >= 0 && m < M && n < N && h < H;
    }

    private static class Point {
        int m, n, h;

        Point(int m, int n, int h) {
            this.m = m;
            this.n = n;
            this.h = h;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
