package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1647 {
    static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        while (M-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            int weight = Integer.parseInt(edgeInfo.nextToken());
            priorityQueue.add(new Edge(a, b, weight));
        }
        System.out.print(kruskal(N));
    }


    static int findParent(int a, int[] parent) {
        if (parent[a] == a) return a;
        else return findParent(parent[a], parent);
    }


    static void union(int a, int b, int[] parent) {
        a = findParent(a, parent);
        b = findParent(b, parent);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static int kruskal(int N) {
        int sum = 0;
        int maxEdge = -1;
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int aRoot = findParent(edge.a, parent);
            int bRoot = findParent(edge.b, parent);
            if (aRoot != bRoot) {
                sum += edge.weight;
                maxEdge = Math.max(maxEdge, edge.weight);
                union(edge.a, edge.b, parent);
            }
        }
        return sum - maxEdge;
    }

    static class Edge {
        final int a;
        final int b;
        final int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }
}
