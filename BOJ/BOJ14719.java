package BOJ;

import java.io.*;
import java.util.*;

public class BOJ14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = parse(st.nextToken());
        int W = parse(st.nextToken());
        int[] rain = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            rain[i] = parse(st.nextToken());
        } // init

        int sum = 0;
        for (int i = 0; i < H; i++) {
            int left = -1;
            for (int j = 0; j < W; j++) {
                if (rain[j] > i) { // 빗물을 받을만큼 높은 벽이 있는 경우
                    if (left != -1) { // 바구니 영역 완성
                        sum += j - left - 1;
                    }
                    left = j;
                }
            }
        }
        System.out.println(sum);
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
