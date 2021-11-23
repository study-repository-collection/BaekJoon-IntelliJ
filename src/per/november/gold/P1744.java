package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.System.in;

public class P1744 {
    static PriorityQueue<Integer> positiveQueue = new PriorityQueue<>(Comparator.reverseOrder());
    static PriorityQueue<Integer> negativeQueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a > 0) {
                positiveQueue.add(a);
            } else {
                negativeQueue.add(a);
            }
        }
        System.out.println(solution());
    }

    static int solution() {
        int answer = 0;
        while (!negativeQueue.isEmpty()) {
            int a = negativeQueue.poll();
            if (!negativeQueue.isEmpty()) {
                answer += a * negativeQueue.poll();
            } else {
                answer += a;
            }
        }
        while (!positiveQueue.isEmpty()) {
            int a = positiveQueue.poll();
            if (!positiveQueue.isEmpty()) {
                int b = positiveQueue.poll();
                answer += Math.max(a * b, a + b);
            } else {
                answer += a;
            }
        }
        return answer;
    }
}
