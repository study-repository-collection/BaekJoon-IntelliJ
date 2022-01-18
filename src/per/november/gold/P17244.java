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

public class P17244 {

    static final char EXIT = 'E';
    static final char EMPTY = '.';
    static final char START = 'S';
    static final char MUST_HAVE = 'X';
    static final char WALL = '#';
    static int[][] map;

    static final int[][] dxDy = {{-1, 0,}, {0, -1}, {1, 0}, {0, 1}};

    static final ArrayList<Edge>[] adjacentList = new ArrayList[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());

        int number = 0;
        int M = Integer.parseInt(mapInfo.nextToken());
        int N = Integer.parseInt(mapInfo.nextToken());

        for (int i = 0; i < 6; i++) adjacentList[i] = new ArrayList<>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == MUST_HAVE) {
                    map[i][j] = ++number;
                }
            }
        }
        initEdge(N, M);
        solution(number, 0, 0, new boolean[6], 0);
        System.out.println(min);
    }

    static int min = Integer.MAX_VALUE;

    static void solution(int totalCount, int index, int length, boolean[] use, int to) {
        if (totalCount == index) {
            for (Edge edge : adjacentList[to]) {
                if (edge.to == 6) {
                    min = Math.min(length + edge.weight, min);
                    return;
                }
            }
        }
        for (Edge edge : adjacentList[to]) {
            if (edge.to != 6 && !use[edge.to]) {
                use[edge.to] = true;
                solution(totalCount, index + 1, length + edge.weight, use, edge.to);
                use[edge.to] = false;
            }
        }
    }

    static void initEdge(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == START) {
                    bfs(new Point(j, i), N, M, 0);
                } else if (map[i][j] >= 1 && map[i][j] <= 5) {
                    bfs(new Point(j, i), N, M, map[i][j]);
                }
            }
        }
    }

    static void bfs(Point startPoint, int N, int M, int start) {
        Queue<Point> points = new LinkedList<>();
        points.add(startPoint);
        boolean[][] visited = new boolean[N][M];
        visited[startPoint.y][startPoint.x] = true;
        int length = 0;
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point temp = points.poll();
                for (int[] move : dxDy) {
                    int X = temp.x + move[0];
                    int Y = temp.y + move[1];
                    if (!visited[Y][X]) {
                        if (map[Y][X] >= 1 && map[Y][X] <= 5) {
                            visited[Y][X] = true;
                            adjacentList[start].add(new Edge(map[Y][X], length + 1));
                            points.add(new Point(X, Y));
                        } else if (map[Y][X] == EXIT) {
                            visited[Y][X] = true;
                            adjacentList[start].add(new Edge(6, length + 1));
                        } else if (map[Y][X] != WALL) {
                            visited[Y][X] = true;
                            points.add(new Point(X, Y));
                        }
                    }
                }
            }
            length++;
        }
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
