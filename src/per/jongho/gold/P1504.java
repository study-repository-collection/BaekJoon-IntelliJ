package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P1504 {
    static ArrayList<Edge>[] adjacentList;
    static int V1; //반드시 거쳐야 할 정점1
    static int V2; //반드시 거쳐야 할 정점2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//정점의 개수
        int E = Integer.parseInt(st.nextToken());//간선의 개수

        adjacentList = new ArrayList[N + 1];
        while (E-- > 0) {
            StringTokenizer edge = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(edge.nextToken());
            int end = Integer.parseInt(edge.nextToken());
            int length = Integer.parseInt(edge.nextToken());
            if (null == adjacentList[start]) adjacentList[start] = new ArrayList<>();
            if (null == adjacentList[end]) adjacentList[end] = new ArrayList<>();

            adjacentList[start].add(new Edge(end, length));
            adjacentList[end].add(new Edge(start, length));
        }

        StringTokenizer input = new StringTokenizer(br.readLine());

        V1 = Integer.parseInt(input.nextToken());
        V2 = Integer.parseInt(input.nextToken());
        int length = 0;
        length += dijkstra(N, 1, V1);
        length += dijkstra(N, V1, V2);
        length += dijkstra(N, V2, N);
        int length2 = 0;
        length2 += dijkstra(N, 1, V2);
        length2 += dijkstra(N, V2, V1);
        length2 += dijkstra(N, V1, N);
        System.out.println(Math.min(length, length2) >= INF ? -1 : Math.min(length, length2));
    }



    static final int INF = 100000000;

    static int dijkstra(int N, int start, int end) {
        int[] dijkstra = new int[N + 1];
        Arrays.fill(dijkstra, INF);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> dijkstra[o]));
        dijkstra[start] = 0;
        priorityQueue.add(start);
        while (!priorityQueue.isEmpty()) {
            int index = priorityQueue.poll();
            if (null != adjacentList[index]) {
                for (Edge edge : adjacentList[index]) {
                    if (dijkstra[edge.to] > dijkstra[index] + edge.length) {
                        dijkstra[edge.to] = dijkstra[index] + edge.length;
                        priorityQueue.add(edge.to);
                    }
                }
            }
        }
        return dijkstra[end];
    }


    static class Edge {
        final int to;
        final int length;

        public Edge(int to, int length) {
            this.to = to;
            this.length = length;
        }
    }
}
