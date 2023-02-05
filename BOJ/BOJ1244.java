package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1244 {

    private static int n;
    private static boolean[] isOn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = parse(br.readLine()); // 스위치의 개수
        isOn = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            isOn[i] = parse(st.nextToken()) == 1;
        }

        int m = parse(br.readLine()); // 학생 수
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int gender = parse(st.nextToken());
            int idx = parse(st.nextToken());
            if (gender == 1) {
                // 남학생
                for (int i = idx; i <= n; i += idx) {
                    isOn[i] = !isOn[i];
                }
            } else {
                // 여학생
                for (int i = 0; idx - i > 0 && idx + i <= n; i++) {
                    if (isOn[idx + i] == isOn[idx - i]) {
                        isOn[idx + i] = !isOn[idx + i];
                        if (i != 0)
                            isOn[idx - i] = !isOn[idx - i];
                    } else {
                        break;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print((isOn[i] ? 1 : 0) + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
