package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2887 {
    static ArrayList<Point> xList = new ArrayList<>();
    static ArrayList<Point> yList = new ArrayList<>();
    static ArrayList<Point> zList = new ArrayList<>();
    static PriorityQueue<Edge> edges = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.value, o2.value));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer info = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(info.nextToken());
            int y = Integer.parseInt(info.nextToken());
            int z = Integer.parseInt(info.nextToken());
            xList.add(new Point(i, x));
            yList.add(new Point(i, y));
            zList.add(new Point(i, z));
        }
        xList.sort(null);
        yList.sort(null);
        zList.sort(null);
        makeEdge(N);
        System.out.println(solution(N));
    }

    static long solution(int N) {
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        long answer = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int aRoot = find(edge.a, parent);
            int bRoot = find(edge.b, parent);
            if (aRoot != bRoot) {
                union(aRoot, bRoot, parent);
                answer += edge.value;
            }
        }
        return answer;
    }

    static void makeEdge(int N) {
        for (int i = 0; i < N - 1; i++) {
            edges.add(new Edge(xList.get(i + 1).number, xList.get(i).number, Math.abs(xList.get(i + 1).value - xList.get(i).value)));
            edges.add(new Edge(yList.get(i + 1).number, yList.get(i).number, Math.abs(yList.get(i + 1).value - yList.get(i).value)));
            edges.add(new Edge(zList.get(i + 1).number, zList.get(i).number, Math.abs(zList.get(i + 1).value - zList.get(i).value)));
        }
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
        int value;

        public Edge(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }
    }

    static int find(int a, int[] parent) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a], parent);
    }

    static class Point implements Comparable<Point> {
        final int number;
        final int value;

        public Point(int number, int value) {
            this.number = number;
            this.value = value;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.value, o.value);
        }
    }

}
