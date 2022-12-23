package BOJ;


import java.io.*;

public class BOJ5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = parse(br.readLine());

        TC:
        while (TC-- > 0) {
            String p = br.readLine(); // 실행할 함수
            int n = parse(br.readLine()); // 배열 크기
            String[] arr = br.readLine().replace("[", "").replace("]", "").split(",");
            // end init

            boolean order = true;
            int first = 0;
            int last = n - 1;

            // 명령어 실행
            for (int i = 0; i < p.length(); i++) {
                char cmd = p.charAt(i);

                if (cmd == 'R') {
                    // 순서 뒤집기
                    order = !order;
                } else if (cmd == 'D') {
                    // 하나 제거하기
                    if (last < first) {
                        System.out.println("error");
                        continue TC;
                    } else if (order) {
                        // 앞부터 제거
                        first++;
                    } else {
                        // 뒤부터 제거
                        last--;
                    }
                }
            }

            // 배열 출력
            StringBuilder sb = new StringBuilder();
            if (order) {
                for (int i = first; i <= last; i++) {
                    sb.append(arr[i]);
                    if (i != last) {
                        sb.append(",");
                    }
                }
            } else {
                for (int i = last; i >= first; i--) {
                    sb.append(arr[i]);
                    if (i != first) {
                        sb.append(",");
                    }
                }
            }
            System.out.println("[" + sb + "]");
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
