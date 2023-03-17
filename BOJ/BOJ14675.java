package BOJ;

import java.io.*;
import java.util.*;

public class BOJ14675 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = parse(br.readLine()); // 정점의 개수
        int[] adjCount = new int[N + 1];
        for (int i = 1; i <= N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());

            adjCount[a]++;
            adjCount[b]++;
        } // 인접 리스트 정보 입력 완료

        int q = parse(br.readLine()); // 질의의 개수
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = parse(st.nextToken());
            int i = parse(st.nextToken());

            if (cmd == 2 || adjCount[i] >= 2) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
