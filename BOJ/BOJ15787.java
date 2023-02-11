package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15787 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = parse(st.nextToken()); // 기차의 수
        int M = parse(st.nextToken()); // 명령의 수

        int[] train = new int[N + 1]; // 기차에 탄 승객 상태

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int cmd = parse(st.nextToken());
            int trainNum = parse(st.nextToken());

            if (cmd == 1) {
                int seatNum = parse(st.nextToken()) - 1;
                train[trainNum] |= (1 << seatNum);
            } else if (cmd == 2) {
                int seatNum = parse(st.nextToken()) - 1;

                train[trainNum] &= ~(1 << seatNum);
            } else if (cmd == 3) {
                train[trainNum] <<= 1; // 한 칸씩 뒤로가기
                train[trainNum] &= ~(1 << 20); // 마지막 승객은 하차
            } else {
                train[trainNum] >>= 1; // 한 칸씩 앞으로
            }
        }

        Set<Integer> map = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            map.add(train[i]);
        }

        System.out.println(map.size());
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }

}
