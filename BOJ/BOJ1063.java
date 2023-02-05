package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1063 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        Point p1 = getCoordinate(st.nextToken()); // 킹의 위치
        Point p2 = getCoordinate(st.nextToken()); // 돌의 위치
        int n = parse(st.nextToken()); // 이동 횟수

        while (n-- > 0) {
            String cmd = br.readLine();

            Point np1 = move(cmd, p1);

            if (checkIdx(np1.i, np1.j)) {
                if (np1.i == p2.i && np1.j == p2.j) {
                    Point np2 = move(cmd, p2);
                    if (checkIdx(np2.i, np2.j)) {
                        p2 = np2;
                        p1 = np1;
                    }
                } else {
                    p1 = np1;
                }
            }
        }

        System.out.println(getPosition(p1));
        System.out.println(getPosition(p2));

    }

    private static Point move(String cmd, Point p) {
        Point np = new Point(p.i, p.j);
        if (cmd.equals("R")) {
            np.i += 1;
        } else if (cmd.equals("L")) {
            np.i -= 1;
        } else if (cmd.equals("B")) {
            np.j += 1;
        } else if (cmd.equals("T")) {
            np.j -= 1;
        } else if (cmd.equals("RT")) {
            np.i += 1;
            np.j -= 1;
        } else if (cmd.equals("LT")) {
            np.i -= 1;
            np.j -= 1;
        } else if (cmd.equals("RB")) {
            np.i += 1;
            np.j += 1;
        } else if (cmd.equals("LB")) {
            np.i -= 1;
            np.j += 1;
        }

        return np;
    }

    private static Point getCoordinate(String p) {
        int i = p.charAt(0) - 'A';
        int j = '8' - p.charAt(1);

        return new Point(i, j);
    }

    private static String getPosition(Point p) {
        String row = String.valueOf((char)(p.i + 'A'));
        String col = String.valueOf(8 - p.j);
        return  row + col;
    }

    private static boolean checkIdx(int i, int j) {
        return i >= 0 && j >= 0 && i < 8 && j < 8;
    }

    private static class Point {
        int i, j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
