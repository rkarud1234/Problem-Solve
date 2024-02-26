package BOJ;

import java.io.*;

public class BOJ25501 {

    private static int cnt;

    public static int recursion(String s, int l, int r) {
        cnt++;
        if (l >= r) return 1;
        else if (s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l + 1, r - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            cnt = 0;
            String S = br.readLine();
            System.out.println(recursion(S, 0, S.length() - 1) + " " + cnt);
        }
    }
}
