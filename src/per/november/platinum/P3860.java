package per.november.platinum;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P3860 {
    static int[][] map;
    static final int GRASS = 0;
    static final int GRAVE = 1;
    static final int HOLE = 2;

    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static LinkedList<Edge> edges = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(mapInfo.nextToken());
            int H = Integer.parseInt(mapInfo.nextToken());
            if (W == 0) break;
            map = new int[H][W];
            edges.clear();
            int G = Integer.parseInt(br.readLine());
            while (G-- > 0) {
                StringTokenizer info = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(info.nextToken());
                int y = Integer.parseInt(info.nextToken());
                map[y][x] = GRAVE;
            }
            int E = Integer.parseInt(br.readLine());
            while (E-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(stringTokenizer.nextToken());
                int y1 = Integer.parseInt(stringTokenizer.nextToken());
                int x2 = Integer.parseInt(stringTokenizer.nextToken());
                int y2 = Integer.parseInt(stringTokenizer.nextToken());
                int t = Integer.parseInt(stringTokenizer.nextToken());
                map[y1][x1] = HOLE;
                edges.add(new Edge(new Point(x1, y1), new Point(x2, y2), t));
            }
            makeEdges(W, H);
            int ret = bellmanFord(W, H);
            System.out.println(ret == Integer.MIN_VALUE ? "Never" : (ret == INF ? "Impossible" : ret));
        }
    }

    static final int INF = 1987654321;

    static int bellmanFord(int W, int H) {
        int[][] dist = new int[H][W];
        for (int i = 0; i < H; i++) Arrays.fill(dist[i], INF);
        dist[0][0] = 0;
        int vertexNumbers = W * H;
        for (int i = 0; i < vertexNumbers; i++) {
            boolean isUpdate = false;
            for (Edge edge : edges) {
                Point start = edge.start;
                Point end = edge.end;
                if (dist[start.y][start.x] == INF) continue;
                if (dist[end.y][end.x] > dist[start.y][start.x] + edge.value) {
                    dist[end.y][end.x] = dist[start.y][start.x] + edge.value;
                    isUpdate = true;
                }
            }
            if (i == vertexNumbers - 1 && isUpdate) {
                return Integer.MIN_VALUE;
            }
        }
        return dist[H - 1][W - 1];
    }

    static void makeEdges(int W, int H) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (i == H - 1 && j == W - 1) continue;
                if (map[i][j] != GRASS) continue;
                for (int[] move : dxDy) {
                    int X = j + move[0];
                    int Y = i + move[1];
                    if (canVisit(X, Y, W, H) && map[Y][X] != GRAVE) {
                        edges.add(new Edge(new Point(j, i), new Point(X, Y), 1));
                    }
                }
            }
        }
    }


    static class Edge {
        Point start;
        Point end;
        int value;

        public Edge(Point start, Point end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }

    static boolean canVisit(int x, int y, int W, int H) {
        return (x >= 0 && y >= 0 && x < W && y < H);
    }
}
