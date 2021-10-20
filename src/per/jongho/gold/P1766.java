package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1766 {


    static ArrayList<Integer>[] edges;
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());//문제의 수
        int M = Integer.parseInt(input.nextToken());//먼저 푸는 것이 좋은 문제
        edges = new ArrayList[N + 1];
        degree = new int[N + 1];
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (null == edges[a]) edges[a] = new ArrayList<>();
            edges[a].add(b);
            degree[b]++;
        }
        solution(N);
    }

    static void solution(int N) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) if (degree[i] == 0) priorityQueue.add(i);

        while (!priorityQueue.isEmpty()) {
            int index = priorityQueue.poll();
            sb.append(index).append(" ");
            if (null != edges[index]) {
                for (int to : edges[index]) {
                    degree[to]--;
                    if (degree[to] == 0) {
                        priorityQueue.add(to);
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
