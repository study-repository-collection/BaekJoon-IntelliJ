package per.october.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1774 {
    static Point[] points;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        points = new Point[N + 1];
        parent = new int[N + 1];

        initParent(N);
        for (int i = 1; i <= N; i++) {
            StringTokenizer pointInfo = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(pointInfo.nextToken()), Integer.parseInt(pointInfo.nextToken()));
        }

        while (M-- > 0) {
            StringTokenizer existedEdge = new StringTokenizer(br.readLine());
            union(Integer.parseInt(existedEdge.nextToken()), Integer.parseInt(existedEdge.nextToken()));
        }

        System.out.printf("%.2f", kruskal(makeEdges(N)));
    }

    static PriorityQueue<Edge> makeEdges(int N) {
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                Point point1 = points[i];
                Point point2 = points[j];
                double length = Math.sqrt(Math.pow(Math.abs(point1.x - point2.x), 2) + Math.pow(Math.abs(point1.y - point2.y), 2));
                edges.add(new Edge(i, j, length));
            }
        }
        return edges;
    }

    static double kruskal(PriorityQueue<Edge> edges) {
        double sum = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int a = find(edge.a);
            int b = find(edge.b);
            if (a != b) {
                union(edge.a, edge.b);
                sum += edge.length;
            }
        }
        return sum;
    }

    static void initParent(int N) {
        for (int i = 1; i <= N; i++) parent[i] = i;
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    static class Edge implements Comparable<Edge> {
        final int a;
        final int b;
        final double length;

        public Edge(int a, int b, double length) {
            this.a = a;
            this.b = b;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.length, o.length);
        }
    }
}
