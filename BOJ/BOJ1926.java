package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1926 {

    private static int n, m;
    private static int[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parse(st.nextToken());
        m = parse(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        // init
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int cnt = 0, maxSize = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    // 방문한 이력이 없는 그림이면 탐색 시작
                    cnt++;
                    maxSize = Math.max(maxSize, getSize(i, j));
                }
            }
        }

        System.out.println(cnt);
        System.out.println(maxSize);
    }

    private static int getSize(int i, int j) {
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int size = 0;
        Queue<Point> queue = new LinkedList<>();

        // 시작하는 점의 위치
        visit[i][j] = true;
        queue.add(new Point(i, j));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            size++;

            for (int d = 0; d < 4; d++) {
                int ni = p.i + move[d][0];
                int nj = p.j + move[d][1];

                if (ni >= 0 && nj >= 0 && ni < n && nj < m && map[ni][nj] == 1 && !visit[ni][nj]) {
                    visit[ni][nj] = true;
                    queue.add(new Point(ni, nj));
                }
            }

        }

        return size;
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
