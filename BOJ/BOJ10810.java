package BOJ;

import java.io.*;
import java.util.*;

public class BOJ10810 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 바구니 개수
        int M = Integer.parseInt(st.nextToken()); // 공의 개수

        int[] baskets = new int[N]; // 바구니 배열

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = parse(st.nextToken()) - 1;
            int j = parse(st.nextToken()) - 1;
            int k = parse(st.nextToken());

            for (; i <= j; i++)
                baskets[i] = k;
        }

        for (int b : baskets)
            System.out.print(b + " ");
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
