package BOJ;

import java.io.*;

public class BOJ5554 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tot = 0;
        for (int i = 0; i < 4; i++) {
            tot += Integer.parseInt(br.readLine());
        }
        System.out.println(tot / 60);
        System.out.println(tot % 60);
    }
}
