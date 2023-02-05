package BOJ;

import java.io.*;
import java.util.*;

public class BOJ5464 {

    private static int[] fees, weights;
    private static PriorityQueue<Integer> emptyParkingLot;
    private static Queue<Integer> car;
    private static Map<Integer, Integer> hm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = parse(st.nextToken()); // 주차공간 수
        int m = parse(st.nextToken()); // 오늘 주차할 차량의 수
        int ans = 0;

        fees = new int[n]; // 단위무게당 주차요금
        weights = new int[m + 1]; // 자동차 무게
        emptyParkingLot = new PriorityQueue<>(); // 빈 주차공간
        car = new LinkedList<>(); // 대기중인 차
        hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            emptyParkingLot.offer(i);
            fees[i] = parse(br.readLine());
        }

        for (int i = 1; i <= m; i++) {
            weights[i] = parse(br.readLine());
        }

        // 주차 시작
        for (int i = 0; i < 2 * m; i++) {
            int carNum = parse(br.readLine());
            if (carNum > 0) {
                // 들어오는 차
                if (emptyParkingLot.isEmpty()) {
                    // 남은 주차공간이 없는 경우
                    car.offer(carNum);
                } else {
                    // 주차공간이 있는 경우
                    ans += parking(carNum);
                }
            } else {
                // 나가는 차
                carNum *= -1;
                int parkingNum = hm.get(carNum);
                hm.remove(carNum);
                emptyParkingLot.offer(parkingNum);

                if(!car.isEmpty()){
                    // 대기중인 차가 있는 경우 주차시킴
                    int waitingCar = car.poll();
                    ans += parking(waitingCar);
                }
            }
        }

        System.out.println(ans);
    }

    private static int parking(int carNum) {
        int parkingNum = emptyParkingLot.poll();
        hm.put(carNum, parkingNum);
        return weights[carNum] * fees[parkingNum];
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }
}
