package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P4386 {

    static Star[] stars;
    static PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingDouble(o -> o.distance));

    static class Star {
        final double x;
        final double y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        final int start;
        final int finish;
        final double distance;

        public Edge(int start, int finish, double distance) {
            this.start = start;
            this.finish = finish;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //별들의 개수
        stars = new Star[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer starInfo = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(starInfo.nextToken());
            double y = Double.parseDouble(starInfo.nextToken());
            stars[i] = new Star(x, y);
        }
        makeEdges(N);
        System.out.printf("%.2f", makeMST(N));

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

    static double makeMST(int N) {
        double minDistance = 0;
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int aRoot = find(edge.start, parent);
            int bRoot = find(edge.finish, parent);

            if (aRoot != bRoot) {
                minDistance += edge.distance;
                union(edge.start, edge.finish, parent);
            }
        }
        return minDistance;
    }

    static void makeEdges(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                edges.add(new Edge(i, j, findDistance(stars[i].x, stars[i].y, stars[j].x, stars[j].y)));
            }
        }
    }

    static double findDistance(double x, double y, double x1, double y1) {
        double a = Math.abs(x - x1);
        double b = Math.abs(y - y1);
        return Math.sqrt(a * a + b * b);
    }
}
