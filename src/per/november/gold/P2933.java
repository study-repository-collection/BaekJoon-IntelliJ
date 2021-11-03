package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2933 {
    static final char EMPTY = '.';
    static final char MINERAL = 'x';
    static char[][] map;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    static final int FROM_LEFT = 1;
    static final int FROM_RIGHT = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(mapInfo.nextToken());
        int C = Integer.parseInt(mapInfo.nextToken());

        map = new char[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            String input = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = input.charAt(j - 1);
            }
        }

        int N = Integer.parseInt(br.readLine());
        StringTokenizer throwInfo = new StringTokenizer(br.readLine());
        Queue<Integer> throwHeights = new LinkedList<>();
        while (N-- > 0) {
            throwHeights.add(Integer.parseInt(throwInfo.nextToken()));
        }

        solution(R, C, throwHeights);
    }


    static void solution(int R, int C, Queue<Integer> throwHeight) {
        int count = 1;

        while (!throwHeight.isEmpty()) {
            int height = throwHeight.poll();
            throwStick(height, (count++ & 1), R, C);
            Queue<Point> clusters = findCluster(R, C);
            map = fall(R, C, clusters);
        }
        printAnswer(R, C);
    }


    static char[][] copyMap(int R, int C) {
        char[][] copiedMap = new char[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }

    static char[][] fall(int R, int C, Queue<Point> points) {
        if (points.isEmpty()) return map;
        char[][] copiedMap = null;
        boolean find = false;
        while (!find) {
            copiedMap = copyMap(R, C);
            for (Point point : points) {
                if (point.y == R || map[point.y + 1][point.x] == MINERAL) {
                    find = true;
                    break;
                }
                map[point.y + 1][point.x] = MINERAL;
                map[point.y][point.x] = EMPTY;
                point.y++;
            }
        }
        return copiedMap;
    }


    static Queue<Point> findCluster(int R, int C) {
        Queue<Point> points = new LinkedList<>();
        boolean[][] visited = new boolean[R + 1][C + 1];
        for (int i = 1; i <= C; i++) {
            if (!visited[R][i] && map[R][i] == MINERAL) {
                bfs(i, R, R, C, visited);
            }
        }

        for (int i = R - 1; i >= 1; i--) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == MINERAL && !visited[i][j]) {
                    points.add(new Point(j, i));
                }
            }
        }
        return points;
    }

    static void bfs(int x, int y, int R, int C, boolean[][] visited) {
        Queue<Point> points = new LinkedList<>();

        visited[y][x] = true;
        points.add(new Point(x, y));
        while (!points.isEmpty()) {
            Point point = points.poll();
            for (int i = 0; i < 4; i++) {
                int X = point.x + dxDy[i][0];
                int Y = point.y + dxDy[i][1];
                if (canVisit(X, Y, R, C) && !visited[Y][X] && map[Y][X] == MINERAL) {
                    visited[Y][X] = true;
                    points.add(new Point(X, Y));
                }
            }
        }
    }

    static void printAnswer(int R, int C) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);
    }


    static boolean canVisit(int x, int y, int R, int C) {
        return (x >= 1 && y >= 1 && x <= C && y <= R);
    }

    static void throwStick(int height, int direction, int R, int C) {
        height = R - height + 1;
        switch (direction) {
            case FROM_LEFT:
                for (int i = 1; i <= C; i++) {
                    if (map[height][i] == MINERAL) {
                        map[height][i] = EMPTY;
                        break;
                    }
                }
                break;
            case FROM_RIGHT:
                for (int i = C; i >= 1; i--) {
                    if (map[height][i] == MINERAL) {
                        map[height][i] = EMPTY;
                        break;
                    }
                }
                break;
        }
    }

}
