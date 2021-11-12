package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14621 {
    static char[] school;
    static final char MEN = 'M';
    static final char WOMAN = 'W';
    static PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    static class Edge {
        int a;
        int b;
        int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        school = new char[N + 1];
        StringTokenizer info = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            school[i] = info.nextToken().charAt(0);
        }
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
        System.out.println(solution(N));
    }

    static int find(int[] parent, int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent, parent[a]);
    }

    static void union(int a, int b, int[] parent) {
        a = find(parent, a);
        b = find(parent, b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static int solution(int N) {
        int[] parent = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        int length = 0;
        for (int i = 1; i <= N; i++) parent[i] = i;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (school[edge.a] == school[edge.b]) {
                continue;
            }
            int aRoot = find(parent, edge.a);
            int bRoot = find(parent, edge.b);
            if (aRoot != bRoot) {
                union(edge.a, edge.b, parent);
                visited[edge.a] = true;
                visited[edge.b] = true;
                length += edge.weight;
            }
        }
        boolean find = true;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) find = false;
        }
        return find ? length : -1;
    }
}
