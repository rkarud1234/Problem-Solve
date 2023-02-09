package BOJ;

import java.io.*;
import java.util.*;

public class BOJ17952 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = parse(br.readLine()); // 이번 학기의 분
        Stack<Assignment> stack = new Stack<>(); // 과제 정보
        int ans = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int trigger = parse(st.nextToken());

            if (trigger == 1) {
                // 과제가 주어지는 경우
                stack.push(new Assignment(parse(st.nextToken()), parse(st.nextToken())));
            }

            if(!stack.isEmpty()){
                // 해야 할 과제가 남은 경우
                stack.peek().time--;
                if(stack.peek().time <= 0){
                    ans += stack.peek().score;
                    stack.pop();
                }
            }
        }

        System.out.println(ans);
    }

    private static class Assignment {
        int score, time;

        Assignment(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
