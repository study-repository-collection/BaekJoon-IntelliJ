package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1781 {
    static LinkedList<Integer>[] deadLines = new LinkedList[200001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(input.nextToken());
            int cupRamen = Integer.parseInt(input.nextToken());
            if (null == deadLines[deadLine]) {
                deadLines[deadLine] = new LinkedList<>();
            }
            deadLines[deadLine].add(cupRamen);
        }
        System.out.println(solution(N));
    }

    static int solution(int N) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            while (null != deadLines[i] && !deadLines[i].isEmpty()) {
                queue.add(deadLines[i].poll());
            }

            while (queue.size() > i) {
                queue.poll();
            }
        }
        int ret = 0;
        while (!queue.isEmpty()) {
            ret += queue.poll();
        }
        return ret;
    }
}
