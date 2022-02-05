package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P19538 {

    static int[] degree;
    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        adjacentList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adjacentList[i] = new ArrayList<>();
        degree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            while (true) {
                int number = Integer.parseInt(input.nextToken());
                if (number == 0) break;
                degree[i]++;
                adjacentList[i].add(number);
            }
        }
        for (int i = 1; i <= N; i++) {
            if ((degree[i] & 1) == 1) {
                degree[i] = degree[i] / 2 + 1;
            } else {
                degree[i] = degree[i] / 2;
            }
        }
        int M = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        StringTokenizer input = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int number = Integer.parseInt(input.nextToken());
            degree[number] = -1;
            queue.add(number);
        }
        solution(N, queue);

    }

    static void solution(int N, Queue<Integer> queue) {
        int time = 0;
        StringBuilder sb = new StringBuilder();
        int[] times = new int[N + 1];
        Arrays.fill(times, -1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                times[temp] = time;
                for (int next : adjacentList[temp]) {
                    degree[next]--;
                    if (degree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
            time++;
        }
        for (int i = 1; i <= N; i++) {
            sb.append(times[i]).append(" ");
        }
        System.out.print(sb);
    }

}
