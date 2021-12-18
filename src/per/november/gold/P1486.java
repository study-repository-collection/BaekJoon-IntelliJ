package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P1486 {
    static final int[][] dxDy = {{1, 0}, {0, 1}};

    static ArrayList<Edge>[][] adjacentList;
    static ArrayList<Edge>[][] adjacentList2;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int T = Integer.parseInt(input.nextToken()); //높이의 차
        int D = Integer.parseInt(input.nextToken()); //어두워지는 시간
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String mapInfo = br.readLine();
            for (int j = 0; j < M; j++) {
                if (mapInfo.charAt(j) <= 'Z') {
                    map[i][j] = mapInfo.charAt(j) - 'A';
                } else {
                    map[i][j] = mapInfo.charAt(j) - 71;
                }
            }
        }
        adjacentList = new ArrayList[N][M];
        adjacentList2 = new ArrayList[N][M];
        makeAdjacentList2(N, M, T);
        makeAdjacentList(N, M, T);
        System.out.println(solution(N, M, D));

    }

    static int solution(int N, int M, int D) {
        int[][] dist = dijkstra(N, M, D, new Point(0, 0));
        int[][] dist2 = dijkstra2(N, M, new Point(0, 0));
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dist[i][j] + dist2[i][j] <= D) {
                    max = Math.max(max, map[i][j]);
                }
            }
        }
        return max;
    }

    static int[][] dijkstra2(int N, int M, Point start) {
        int ret = 0;
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++)
            Arrays.fill(dist[i], 987654321);
        dist[start.y][start.x] = 0;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        priorityQueue.add(new Edge(start, 0));
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (dist[edge.to.y][edge.to.x] < edge.value) continue;
            if (null != adjacentList2[edge.to.y][edge.to.x]) {
                for (Edge temp : adjacentList2[edge.to.y][edge.to.x]) {
                    if (dist[temp.to.y][temp.to.x] > dist[edge.to.y][edge.to.x] + temp.value) {
                        dist[temp.to.y][temp.to.x] = dist[edge.to.y][edge.to.x] + temp.value;
                        priorityQueue.add(new Edge(temp.to, dist[temp.to.y][temp.to.x]));
                    }
                }
            }
        }
        return dist;
    }

    static int[][] dijkstra(int N, int M, int D, Point start) {
        int ret = 0;
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++)
            Arrays.fill(dist[i], 987654321);
        dist[start.y][start.x] = 0;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        priorityQueue.add(new Edge(start, 0));
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (dist[edge.to.y][edge.to.x] < edge.value) continue;
            if (null != adjacentList[edge.to.y][edge.to.x]) {
                for (Edge temp : adjacentList[edge.to.y][edge.to.x]) {
                    if (dist[temp.to.y][temp.to.x] > dist[edge.to.y][edge.to.x] + temp.value) {
                        dist[temp.to.y][temp.to.x] = dist[edge.to.y][edge.to.x] + temp.value;
                        priorityQueue.add(new Edge(temp.to, dist[temp.to.y][temp.to.x]));
                    }
                }
            }
        }
        return dist;
    }


    static void makeAdjacentList(int N, int M, int T) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[] move : dxDy) {
                    int X = j + move[0];
                    int Y = i + move[1];
                    if (canVisit(X, Y, N, M) && Math.abs(map[Y][X] - map[i][j]) <= T) {
                        if (null == adjacentList[Y][X]) adjacentList[Y][X] = new ArrayList<>();
                        if (null == adjacentList[i][j]) adjacentList[i][j] = new ArrayList<>();
                        if (map[Y][X] > map[i][j]) {
                            adjacentList[Y][X].add(new Edge(new Point(j, i), 1));
                            adjacentList[i][j].add(new Edge(new Point(X, Y), (int) Math.pow(map[Y][X] - map[i][j], 2)));
                        } else if (map[Y][X] < map[i][j]) {
                            adjacentList[i][j].add(new Edge(new Point(X, Y), 1));
                            adjacentList[Y][X].add(new Edge(new Point(j, i), (int) Math.pow(map[Y][X] - map[i][j], 2)));
                        } else {
                            adjacentList[Y][X].add(new Edge(new Point(j, i), 1));
                            adjacentList[i][j].add(new Edge(new Point(X, Y), 1));
                        }
                    }
                }
            }
        }
    }

    static void makeAdjacentList2(int N, int M, int T) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[] move : dxDy) {
                    int X = j + move[0];
                    int Y = i + move[1];
                    if (canVisit(X, Y, N, M) && Math.abs(map[Y][X] - map[i][j]) <= T) {
                        if (null == adjacentList2[Y][X]) adjacentList2[Y][X] = new ArrayList<>();
                        if (null == adjacentList2[i][j]) adjacentList2[i][j] = new ArrayList<>();
                        if (map[Y][X] < map[i][j]) {
                            adjacentList2[Y][X].add(new Edge(new Point(j, i), 1));
                            adjacentList2[i][j].add(new Edge(new Point(X, Y), (int) Math.pow(map[Y][X] - map[i][j], 2)));
                        } else if (map[Y][X] > map[i][j]) {
                            adjacentList2[i][j].add(new Edge(new Point(X, Y), 1));
                            adjacentList2[Y][X].add(new Edge(new Point(j, i), (int) Math.pow(map[Y][X] - map[i][j], 2)));
                        } else {
                            adjacentList2[Y][X].add(new Edge(new Point(j, i), 1));
                            adjacentList2[i][j].add(new Edge(new Point(X, Y), 1));
                        }
                    }
                }
            }
        }
    }


    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    static class Edge {
        Point to;
        int value;

        public Edge(Point to, int value) {
            this.to = to;
            this.value = value;
        }
    }
}
