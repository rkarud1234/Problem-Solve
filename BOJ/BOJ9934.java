package BOJ;

import java.io.*;
import java.util.*;

public class BOJ9934 {

    private static int[] arr;
    private static StringBuilder[] builders;
    private static int K;

    public static void main(String[] args) throws IOException {
        init();

        // 루트 노드 인덱스 : 1 << (K - 1)
        // 시작 높이 : 1 (최상단)
        go(K == 1 ? 1 : (1 << (K - 1)), 1);

        print();
    }

    private static void go(int n, int h) {
        if (K < h) return;
        builders[h - 1].append(arr[n]).append(" ");

        go(n - (1 << (K - h - 1)), h + 1);
        go(n + (1 << (K - h - 1)), h + 1);
    }

    private static void print() {
        for (int i = 0; i < K; i++) {
            System.out.println(builders[i].toString().trim());
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = parse(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[1 << K];
        builders = new StringBuilder[K];
        for (int i = 0; i < K; i++) {
            builders[i] = new StringBuilder();
        }
        for (int i = 1; i < arr.length; i++) {
            arr[i] = parse(st.nextToken());
        } // end init
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
