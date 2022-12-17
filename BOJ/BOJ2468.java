package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2468 {

    private static int N;
    private static int[][] area;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = parse(br.readLine());
        area = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = parse(st.nextToken());
            }
        } // end Input

        int ans = 1;

        // 수위(i) 높여가면서 비교
        for (int i = 1; i <= 100; i++) {
            ans = Math.max(ans, findAreaCount(i));
        }

        System.out.println(ans);
    }

    // 분리된 영역의 개수를 구하는 함수
    private static int findAreaCount(int h) {
        int cnt = 0;
        boolean[][] visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 방문한 적 없고 기준 수위보다 지대가 높은 경우
                if (!visit[i][j] && area[i][j] > h) {
                    cnt++;
                    // 탐색 시작
                    visit[i][j] = true;
                    bfs(visit, i, j, h);
                }
            }
        }

        return cnt;
    }

    private static int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static void bfs(boolean[][] visit, int si, int sj, int h) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(si, sj));

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < 4; d++) {
                int ni = p.i + move[d][0];
                int nj = p.j + move[d][1];

                if (!checkIdx(ni, nj) || visit[ni][nj] || area[ni][nj] <= h) {
                    continue;
                }

                queue.offer(new Point(ni, nj));
                visit[ni][nj] = true;
            }
        }
    }

    private static boolean checkIdx(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < N;
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
