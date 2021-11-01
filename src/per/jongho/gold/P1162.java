package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P1162 {

    static ArrayList<Edge>[] adjacentList;

    static class Edge {
        final int to;
        final long value;
        int K = 0;

        public Edge(int to, long value, int K) {
            this.to = to;
            this.value = value;
            this.K = K;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());
        adjacentList = new ArrayList[N + 1];
        while (M-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(edgeInfo.nextToken());
            int end = Integer.parseInt(edgeInfo.nextToken());
            int weight = Integer.parseInt(edgeInfo.nextToken());
            if (null == adjacentList[start]) adjacentList[start] = new ArrayList<>();
            if (null == adjacentList[end]) adjacentList[end] = new ArrayList<>();
            adjacentList[start].add(new Edge(end, weight, 0));
            adjacentList[end].add(new Edge(start, weight, 0));
        }
        solution(N, K, 1);
    }

    static final long INF = 11109876543210L;

    static void solution(int N, int K, int start) {
        long[][] dist = new long[N + 1][K + 1];
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingLong(o -> o.value));
        for (int i = 1; i <= N; i++) Arrays.fill(dist[i], INF);
        dist[start][0] = 0;
        edges.add(new Edge(start, 0, 0));

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (dist[edge.to][edge.K] < edge.value) continue;
            if (null != adjacentList[edge.to]) {
                for (Edge temp : adjacentList[edge.to]) {
                    if (dist[temp.to][edge.K] > dist[edge.to][edge.K] + temp.value) {
                        dist[temp.to][edge.K] = dist[edge.to][edge.K] + temp.value;
                        edges.add(new Edge(temp.to, dist[temp.to][edge.K], edge.K));
                    }
                    if (edge.K < K) {
                        if (dist[temp.to][edge.K + 1] > dist[edge.to][edge.K]) {
                            dist[temp.to][edge.K + 1] = dist[edge.to][edge.K];
                            edges.add(new Edge(temp.to, dist[temp.to][edge.K + 1], edge.K + 1));
                        }
                    }
                }
            }
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            min = Math.min(dist[N][i], min);
        }
        System.out.println(min);
    }
}
