package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1826 {

    static int[] fuel = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken()); //시작 위치에서 주유소 까지의 거리
            int b = Integer.parseInt(input.nextToken()); //주유소에서 채울 수 있는 연료의 양
            fuel[a] = b;
        }
        StringTokenizer lastInput = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(lastInput.nextToken()); //성경이의 위치에서 마을까지의 거리
        int P = Integer.parseInt(lastInput.nextToken()); //원래 있던 연료의 양

        //1KM 이동 시 1L 사라짐

        System.out.println(solution(L, P));
    }

    static int solution(int L, int P) {
        int ret = 0;
        PriorityQueue<Integer> fuels = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < L; i++, P--) {
            //현재 위치에 주유소가 있으면 후보 주유소에 넣는다
            if (fuel[i] != 0) {
                fuels.add(fuel[i]);
            }
            //이동하기 전 연료가 0이면, 현재까지 지나온 주유소에서 연료를 채움 (제일 큰 거)
            if (P == 0) {
                //연료가 없는데 찾은 주유소도 없으면, 도착 못 함
                if (!fuels.isEmpty()) {
                    P += fuels.poll();
                    ret++;
                } else {
                    return -1;
                }
            }

        }
        return ret;
    }
}
