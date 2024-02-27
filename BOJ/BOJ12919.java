package BOJ;

import java.util.*;

public class BOJ12919 {
    private static String S;
    private static boolean flag;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextLine();
        String T = sc.nextLine();
        go(T);
        System.out.println(flag ? 1 : 0);
    }

    private static void go(String T) {
        if (T.length() < S.length() || flag) return;
        flag = S.equals(T);

        int tl = T.length();
        if (T.lastIndexOf('A') == tl - 1) {
            go(T.substring(0, tl - 1));
        }
        if (T.charAt(0) == 'B') {
            go(reverseString(T.substring(1, tl)));
        }
    }

    private static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
