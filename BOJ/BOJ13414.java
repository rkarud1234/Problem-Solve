package BOJ;

import java.io.*;
import java.util.*;

public class BOJ13414 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = parse(st.nextToken()); // 수강 가능 인원
        int L = parse(st.nextToken()); // 대기목록 길이

        Set<String> set = new LinkedHashSet<>();

        while (L-- > 0) {
            String STU_ID = br.readLine();
            if(set.contains(STU_ID)){
                set.remove(STU_ID);
            }
            set.add(STU_ID);
        }

        Iterator<String> iterator = set.iterator();

        while(iterator.hasNext() && K-- > 0){
            System.out.println(iterator.next());
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
