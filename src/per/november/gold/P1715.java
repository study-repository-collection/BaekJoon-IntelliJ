package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import static java.lang.System.in;

public class P1715 {
    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            priorityQueue.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (priorityQueue.size() != 1) {
            int a = priorityQueue.poll();
            if (!priorityQueue.isEmpty()) {
                int b = priorityQueue.poll();
                priorityQueue.add(a + b);
                answer += a + b;
                continue;
            }
            priorityQueue.add(a);
        }
        System.out.println(answer);
    }
}
