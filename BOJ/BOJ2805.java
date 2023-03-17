package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2805 {

    private static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = parse(st.nextToken()); // 나무의 수
        int M = parse(st.nextToken()); // 필요한 나무의 길이

        heights = new int[N];
        st = new StringTokenizer(br.readLine());
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            heights[i] = parse(st.nextToken());
            maxHeight = Math.max(maxHeight, heights[i]);
        }

        // 자르는 높이를 기준으로 이분탐색 시작
        int l = 0;
        int r = maxHeight;
        long ans = 0;

        while (l <= r) {
            int mid = (l + r) >> 1; //  = (l + r) / 2

            if (getWoods(mid) >= M) {
                // 목표한 나무 자를 수 있음
                ans = Math.max(ans, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(ans);
    }

    private static long getWoods(int h) {
        long sum = 0;
        for (int i = 0; i < heights.length; i++) {
            sum += Math.max(heights[i] - h, 0);
        }

        return sum;
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
