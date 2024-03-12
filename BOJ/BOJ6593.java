package BOJ;

import java.io.*;
import java.util.*;

public class BOJ6593 {

    private static final char WALL = '#';
    private static final char ESCAPE = 'E';
    private static final char START = 'S';

    private static int L, R, C;
    private static char[][][] map;
    private static boolean[][][] visit;
    private static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s = br.readLine()).equals("0 0 0")) {
            init(s, br);
            printAnswer(bfs());
        }
    }

    private static int bfs() {
        final int[][] move = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
        int cnt = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point p = queue.poll();

                for (int d = 0; d < 6; d++) {
                    int nl = p.l + move[d][0];
                    int nr = p.r + move[d][1];
                    int nc = p.c + move[d][2];

                    if (!checkIdx(nl, nr, nc) || map[nl][nr][nc] == WALL || visit[nl][nr][nc])
                        continue;
                    if (map[nl][nr][nc] == ESCAPE)
                        return cnt;

                    queue.offer(new Point(nl, nr, nc));
                    visit[nl][nr][nc] = true;
                }
            }
            cnt++;
        }
        return -1;
    }

    private static boolean checkIdx(int l, int r, int c) {
        return l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void printAnswer(int result) {
        if (result < 0)
            System.out.println("Trapped!");
        else
            System.out.println("Escaped in " + result + " minute(s).");
    }

    private static void init(String s, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(s);
        L = parse(st.nextToken());
        R = parse(st.nextToken());
        C = parse(st.nextToken());
        map = new char[L][R][C];
        visit = new boolean[L][R][C];
        queue = new LinkedList<>();

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                map[i][j] = br.readLine().toCharArray();
                for (int k = 0; k < C; k++) {
                    if (map[i][j][k] == START) {
                        queue.offer(new Point(i, j, k));
                        visit[i][j][k] = true;
                    }
                }
            }
            br.readLine();
        }
    }

    private static class Point {
        int l, r, c;

        Point(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
