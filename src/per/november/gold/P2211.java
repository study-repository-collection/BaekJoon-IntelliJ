package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P2211 {

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
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        adjacentList = new ArrayList[N + 1];
        while (M-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            int weight = Integer.parseInt(edgeInfo.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
            adjacentList[a].add(new Edge(b, weight));
            adjacentList[b].add(new Edge(a, weight));
        }
        dijkstra(N, 1);
    }

    static final int INF = 987654321;

    static void dijkstra(int N, int start) {
        int[] distance = new int[N + 1];
        int[] route = new int[N + 1];
        Arrays.fill(distance, INF);
        boolean[] visited = new boolean[N + 1];
        distance[start] = 0;
        route[start] = start;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> distance[o]));
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            int index = priorityQueue.poll();
            if (visited[index]) continue;
            visited[index] = true;
            if (null != adjacentList[index]) {
                for (Edge edge : adjacentList[index]) {
                    if (distance[edge.to] > distance[index] + edge.weight) {
                        distance[edge.to] = distance[index] + edge.weight;
                        route[edge.to] = index;
                        if (!visited[edge.to]) {
                            priorityQueue.add(edge.to);
                        }
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(N - 1).append("\n");
        for (int i = 2; i <= N; i++) {
            stringBuilder.append(i).append(" ").append(route[i]).append("\n");
        }
        System.out.println(stringBuilder);

    }
}
