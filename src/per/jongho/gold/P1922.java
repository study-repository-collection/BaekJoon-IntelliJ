package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1922 {


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

    static PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            int weight = Integer.parseInt(edgeInfo.nextToken());
            priorityQueue.add(new Edge(a, b, weight));
        }
        System.out.println(kruskal(N));
    }

    static int find(int a, int[] parent) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a], parent);
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static int kruskal(int N) {
        int sum = 0;
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int aRoot = find(edge.a, parent);
            int bRoot = find(edge.b, parent);
            if (aRoot != bRoot) {
                sum += edge.weight;
                union(edge.a, edge.b, parent);
            }
        }
        return sum;
    }
}
