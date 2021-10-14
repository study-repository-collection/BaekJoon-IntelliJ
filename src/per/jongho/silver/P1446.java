package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1446 {
    static int[][] map;
    static Shortcut[] shortcuts = new Shortcut[12];
    static final int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//지름길의 총 개수
        int D = Integer.parseInt(st.nextToken());//고속도로의 총 길이
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], INF);
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            int length = Integer.parseInt(input.nextToken());
            shortcuts[i] = new Shortcut(start, end, length);
        }
        makeMap(N);
        System.out.println(dijkstra(N, D));
    }

    static int dijkstra(int N, int D) {
        boolean[] visited = new boolean[N];
        int[] dijkstra = new int[N + 1];
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            dijkstra[i] = shortcuts[i].start;
            priorityQueue.add(new Point(i, dijkstra[i]));
        }
        dijkstra[N] = D;

        while (!priorityQueue.isEmpty()) {
            Point point = priorityQueue.poll();
            if (visited[point.index]) continue;
            visited[point.index] = true;

            for (int i = 0; i < N; i++) {
                if (i == point.index) continue;
                int length = dijkstra[point.index] + shortcuts[point.index].length + map[point.index][i];
                if (dijkstra[i] > length) {
                    dijkstra[i] = length;
                    if (!visited[i]) {
                        priorityQueue.add(new Point(i, dijkstra[i]));
                    }
                }
            }
            if (shortcuts[point.index].end <= D) {
                dijkstra[N] = Math.min(dijkstra[point.index] + shortcuts[point.index].length + D - shortcuts[point.index].end, dijkstra[N]);
            }
        }
        return dijkstra[N];
    }

    static class Point implements Comparable<Point> {
        int index;
        int value;

        public Point(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Point o) {
            return value > o.value ? 1 : -1;
        }
    }

    static void makeMap(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (shortcuts[i].end <= shortcuts[j].start) {
                    map[i][j] = shortcuts[j].start - shortcuts[i].end;
                }
            }
        }
    }

    static class Shortcut {
        final int start;
        final int end;
        final int length;

        public Shortcut(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
}
