package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = parse(br.readLine()); // 주사위 개수의 단위, N^3개
        int[] dice = new int[6]; // 각 면에 쓰여있는 숫자
        int[] min = new int[3]; // 마주보고 있는 면 중 더 작은 숫자를 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = parse(st.nextToken());
        }

        // 두 칸 : 맞닿아 있는 모든 면 = 마주보는 면을 제외한 모든 조합
        // 세 칸 : 마주보는 면을 포함하지 않는 붙어있는 면 세 개의 조합
        min[0] = Math.min(dice[0], dice[5]);
        min[1] = Math.min(dice[1], dice[4]);
        min[2] = Math.min(dice[2], dice[3]);
        Arrays.sort(min);

        // 주사위에서 더해야 하는 면의 개수
        // N == 1 일 때 5개
        // N >= 2 일 때는
        // 1면 : 한 면당 가로: N-2, 세로 : N-1개 = 4(N-1)(N-2) + 윗면 (N-2)^2
        // 2면 : 가생이는 4(N-1) 개씩, 맨 위는 4(N-2)개씩 = 8N - 12
        // 3면 : 4개

        if (N == 1) {
            Arrays.sort(dice);
            System.out.println(Arrays.stream(dice, 0, 5).sum());
        } else {
            long sum1 = min[0] * (4L * (N - 1) * (N - 2) + (N - 2L) * (N - 2L));
            int sum2 = (min[0] + min[1]) * (8 * N - 12);
            int sum3 = 4 * (min[0] + min[1] + min[2]);
            System.out.println(sum1 + sum2 + sum3);
        }
    }


    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
