package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P6593 {

    static final char WALL = '#';
    static final char EMPTY = '.';
    static final char EXIT = 'E';
    static final char START = 'S';
    static final String answerString = "Escaped in %d minute(s).\n";
    static final String cantEscape = "Trapped!\n";
    static char[][][] map;

    static final int[][] dxDyDz = {{-1, 0, 0}, {0, -1, 0}, {1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(input.nextToken());
            int R = Integer.parseInt(input.nextToken());
            int C = Integer.parseInt(input.nextToken());
            if (L == 0) break;
            map = new char[L][R][C];
            Point point = null;
            for (int l = 0; l < L; l++) {
                for (int i = 0; i < R; i++) {
                    String mapInfo = br.readLine();
                    for (int j = 0; j < C; j++) {
                        map[l][i][j] = mapInfo.charAt(j);
                        if (map[l][i][j] == START) point = new Point(j, i, l);
                    }
                }
                br.readLine();
            }
            int ret = solution(L, R, C, point);
            if (ret == -1) {
                System.out.print(cantEscape);
            } else {
                System.out.printf(answerString, ret);
            }
        }
    }

    static int solution(int L, int R, int C, Point start) {
        boolean[][][] visited = new boolean[L][R][C];
        visited[start.z][start.y][start.x] = true;
        Queue<Point> points = new LinkedList<>();
        points.add(start);

        int count = 0;
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                if (map[point.z][point.y][point.x] == EXIT) {
                    return count;
                }
                for (int[] move : dxDyDz) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    int Z = point.z + move[2];
                    if (canVisit(X, Y, Z, L, R, C) && map[Z][Y][X] != WALL && !visited[Z][Y][X]) {
                        visited[Z][Y][X] = true;
                        points.add(new Point(X, Y, Z));
                    }
                }
            }
            count++;
        }
        return -1;
    }

    static boolean canVisit(int x, int y, int z, int L, int R, int C) {
        return x >= 0 && y >= 0 && z >= 0 && x < C && y < R && z < L;
    }

    static class Point {
        int x;
        int y;
        int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
