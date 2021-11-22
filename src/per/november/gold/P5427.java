package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P5427 {
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static final char EMPTY = '.';
    static final char WALL = '#';
    static final char SANGUN = '@';
    static final char FIRE = '*';


    static char[][] map;
    static Queue<Point> fires;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(mapInfo.nextToken());
            int N = Integer.parseInt(mapInfo.nextToken());
            fires = new LinkedList<>();
            map = new char[N][M];
            Point start = null;
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = input.charAt(j);
                    if (map[i][j] == FIRE) {
                        fires.add(new Point(j, i));
                    } else if (map[i][j] == SANGUN) {
                        start = new Point(j, i);
                    }
                }
            }
            int ret = solution(N, M, start);
            System.out.println(ret == -1 ? "IMPOSSIBLE" : ret);
        }
    }

    static int solution(int N, int M, Point start) {
        Queue<Point> points = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[start.y][start.x] = true;
        points.add(new Point(start));
        int count = 0;
        while (!points.isEmpty()) {
            fire(N, M);
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(X, Y, N, M)) {
                        if (!visited[Y][X] && map[Y][X] == EMPTY) {
                            visited[Y][X] = true;
                            points.add(new Point(X, Y));
                        }
                    } else {
                        return count + 1;
                    }
                }
            }
            count++;
        }
        return -1;
    }

    static void fire(int N, int M) {
        int size = fires.size();
        for (int i = 0; i < size; i++) {
            Point point = fires.poll();
            for (int[] move : dxDy) {
                int x = point.x + move[0];
                int y = point.y + move[1];
                if (canVisit(x, y, N, M) && map[y][x] == EMPTY) {
                    map[y][x] = FIRE;
                    fires.add(new Point(x, y));
                }
            }
        }
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }
}
