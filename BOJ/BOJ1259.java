package BOJ;

import java.io.*;

public class BOJ1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;

        while(!(s = br.readLine()).equals("0")){
            System.out.println(isPalindrome(s));
        }
    }

    private static String isPalindrome(String s){
        int l = 0;
        int r = s.length() - 1;

        while (l <= r) {
            if(s.charAt(l) != s.charAt(r)){
                return "no";
            }
            l++;
            r--;
        }
        return "yes";
    }
}
