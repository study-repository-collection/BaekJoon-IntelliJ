package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17836 {
    static int[][] map;
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int GRAM = 2;
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
            }
        }
        int ret = solution(N, M, T);
        System.out.println(ret <= T ? ret : "Fail");
    }

    static int solution(int N, int M, int T) {
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(0, 0, 0, 0));
        int[][][] visited = new int[N][M][2];
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) Arrays.fill(visited[i][j], 98765421);
        visited[0][0][0] = 0;
        while (!points.isEmpty()) {
            Point point = points.poll();
            for (int[] move : dxDy) {
                int X = point.x + move[0];
                int Y = point.y + move[1];
                if (canVisit(X, Y, N, M)) {
                    if (map[Y][X] == EMPTY && visited[Y][X][point.gram] > point.time + 1) {
                        visited[Y][X][point.gram] = point.time + 1;
                        points.add(new Point(X, Y, point.gram, point.time + 1));
                    } else if (map[Y][X] == WALL && point.gram == 1 && visited[Y][X][1] > point.time + 1) {
                        visited[Y][X][1] = point.time + 1;
                        points.add(new Point(X, Y, point.gram, point.time + 1));
                    } else if (map[Y][X] == GRAM && visited[Y][X][0] > point.time + 1) {
                        visited[Y][X][0] = point.time + 1;
                        points.add(new Point(X, Y, 1, point.time + 1));
                    }
                }
            }
        }
        return Math.min(visited[N - 1][M - 1][0], visited[N - 1][M - 1][1]);
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    static class Point {
        int x;
        int y;
        int time = 0;
        int gram = 0;

        public Point(int x, int y, int gram, int time) {
            this.x = x;
            this.y = y;
            this.gram = gram;
            this.time = time;
        }
    }
}
