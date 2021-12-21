package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P4991 {
    static char[][] map;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static final char CLEAN = '.';
    static final char DIRTY = '*';
    static final char FURNI = 'x';
    static final char START = 'o';

    static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer mapSize = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(mapSize.nextToken());
            int N = Integer.parseInt(mapSize.nextToken());
            if (N == 0) break;
            map = new char[N][M];
            Point startPoint = new Point();
            int dirtyCount = 0;
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = input.charAt(j);
                    if (map[i][j] == START) {
                        startPoint.y = i;
                        startPoint.x = j;
                    } else if (map[i][j] == DIRTY) {
                        map[i][j] = (char) (dirtyCount++ + '0');
                    }
                }
            }
            edges = new ArrayList[dirtyCount + 1];
            if (!makeEdge(dirtyCount, N, M, startPoint)) {
                sb.append(-1).append("\n");
                continue;
            }
            solution(0, dirtyCount, new boolean[dirtyCount], 0, dirtyCount);
            sb.append(min == Integer.MAX_VALUE ? -1 : min).append("\n");
            min = Integer.MAX_VALUE;
        }
        System.out.println(sb);
    }

    static int min = Integer.MAX_VALUE;

    static void solution(int count, int dirtyCount, boolean[] use, int distance, int to) {
        if (count == dirtyCount) {
            min = Math.min(min, distance);
            return;
        }
        for (Edge edge : edges[to]) {
            if (!use[edge.to]) {
                use[edge.to] = true;
                solution(count + 1, dirtyCount, use, distance + edge.value, edge.to);
                use[edge.to] = false;
            }
        }
    }

    static boolean makeEdge(int dirtyCount, int N, int M, Point startPoint) {
        for (int i = 0; i <= dirtyCount; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] >= '0' && map[i][j] <= '9') {
                    bfsToMakeEdge(new Point(j, i), N, M, map[i][j] - '0');
                }
            }
        }
        bfsToMakeEdge(startPoint, N, M, dirtyCount);
        for (int i = 0; i <= dirtyCount; i++) {
            if (edges[i].isEmpty()) return false;
        }
        return true;
    }

    static void bfsToMakeEdge(Point startPoint, int N, int M, int number) {
        boolean[][] visited = new boolean[N][M];
        visited[startPoint.y][startPoint.x] = true;
        Queue<Point> points = new LinkedList<>();
        points.add(startPoint);
        int count = 0;
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point temp = points.poll();
                if (map[temp.y][temp.x] >= '0' && map[temp.y][temp.x] <= '9') {
                    int tempNumber = map[temp.y][temp.x] - '0';
                    if (number != tempNumber) {
                        edges[number].add(new Edge(tempNumber, count));
                    }
                }
                for (int[] move : dxDy) {
                    int X = temp.x + move[0];
                    int Y = temp.y + move[1];
                    if (canVisit(X, Y, N, M) && !visited[Y][X] && map[Y][X] != FURNI) {
                        visited[Y][X] = true;
                        points.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    static class Edge {
        int to;
        int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }
}
