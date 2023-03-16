package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16938 {

    private static int N, L, R, X, ans;
    private static boolean[] visit;
    private static int[] difficulty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parse(st.nextToken()); // 문제의 개수
        L = parse(st.nextToken()); // 문제 난이도의 합의 최소
        R = parse(st.nextToken()); // 문제 난이도의 합의 최대
        X = parse(st.nextToken()); // 문제 난이도 최대 편차
        visit = new boolean[N];

        difficulty = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            difficulty[i] = parse(st.nextToken());
        }

        for (int i = 2; i <= N; i++) {//조합의 개수
            go(i, 0, 0);
        }

        System.out.println(ans);
    }

    private static void go(int select, int count, int idx) {
        if (select <= count) {
            // 모두 선택 완료
            if (isSatisfied()) ans++;
        } else {
            // 선택 안됐으면 재귀 진행
            for (int i = idx; i < N; i++) {
                visit[i] = true;
                go(select, count + 1, i + 1);
                visit[i] = false;
            }
        }
    }

    private static boolean isSatisfied() {
        int min = 1000000, max = 0, sum = 0;
        for (int i = 0; i < N; i++) {
            if (visit[i]) {
                min = Math.min(min, difficulty[i]);
                max = Math.max(max, difficulty[i]);
                sum += difficulty[i];
            }
        }

        return max - min >= X && sum >= L && sum <= R;
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }

}
