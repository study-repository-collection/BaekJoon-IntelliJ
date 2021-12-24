package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16398 {

    static PriorityQueue<Edge> edges = new PriorityQueue<>((o1, o2) -> Long.compare(o1.weight, o2.weight));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Long value = Long.parseLong(st.nextToken());
                if (j <= i) continue;
                edges.add(new Edge(i, j, value));
            }
        }
        System.out.println(solution(N));
    }

    static long solution(int N) {
        long answer = 0;
        int[] parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int aRoot = find(edge.a, parent);
            int bRoot = find(edge.b, parent);
            if (aRoot != bRoot) {
                union(aRoot, bRoot, parent);
                answer += edge.weight;
            }
        }
        return answer;
    }

    static int find(int a, int[] parent) {
        if (parent[a] == a) return a;
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


    static class Edge {
        int a;
        int b;
        long weight;

        public Edge(int a, int b, long weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

    }
}
