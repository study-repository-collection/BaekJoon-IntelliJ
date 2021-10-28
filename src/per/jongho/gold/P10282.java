package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P10282 {
    static class Edge {
        final int to;
        final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(input.nextToken());//컴퓨터 개수
            adjacentList = new ArrayList[n + 1];
            int d = Integer.parseInt(input.nextToken());//의존성 개수
            int c = Integer.parseInt(input.nextToken());//해킹당한 컴퓨터

            while (d-- > 0) {
                StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(edgeInfo.nextToken());
                int b = Integer.parseInt(edgeInfo.nextToken());
                int s = Integer.parseInt(edgeInfo.nextToken()); //s초 후 a컴퓨터 감염

                if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
                adjacentList[b].add(new Edge(a, s));
            }
            solution(n, c);
        }
    }

    static final int INF = 987654321;

    static void solution(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> dist[o]));
        priorityQueue.add(start);
        int size = 0;

        while (!priorityQueue.isEmpty()) {
            int index = priorityQueue.poll();
            if (visited[index]) continue;
            visited[index] = true;
            if (null != adjacentList[index]) {
                for (Edge edge : adjacentList[index]) {
                    dist[edge.to] = Math.min(dist[edge.to], dist[index] + edge.weight);
                    priorityQueue.add(edge.to);
                }
            }
        }
        int max = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                size++;
                max = Math.max(max, dist[i]);
            }
        }
        sb.append(size).append(" ").append(max);
        System.out.println(sb);
    }
}
