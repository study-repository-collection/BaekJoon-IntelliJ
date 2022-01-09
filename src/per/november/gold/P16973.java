package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16973 {
    static int[][] map;
    static final int WALL = 1;
    static final int EMPTY = 0;

    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(mapInfo.nextToken());
        int M = Integer.parseInt(mapInfo.nextToken());

        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        StringTokenizer rectangleInfo = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(rectangleInfo.nextToken());
        int W = Integer.parseInt(rectangleInfo.nextToken());
        int sY = Integer.parseInt(rectangleInfo.nextToken());
        int sX = Integer.parseInt(rectangleInfo.nextToken());
        int fY = Integer.parseInt(rectangleInfo.nextToken());
        int fX = Integer.parseInt(rectangleInfo.nextToken());

        System.out.println(solution(N, M, H, W, sY, sX, fY, fX));
    }

    static int solution(int N, int M, int H, int W, int sY, int sX, int fY, int fX) {
        boolean[][] visited = new boolean[N + 1][M + 1];
        Queue<Point> points = new LinkedList<>();
        visited[sY][sX] = true;
        points.add(new Point(sX, sY));
        int count = 0;
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                if (point.y == fY && point.x == fX) {
                    return count;
                }
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(H, W, X, Y, N, M, point.x, point.y) && !visited[Y][X]) {
                        visited[Y][X] = true;
                        points.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }
        return -1;
    }

    static boolean canVisit(int H, int W, int x, int y, int N, int M, int ox, int oy) {
        if (x >= 1 && y >= 1 && (x + W - 1) <= M && (y + H - 1) <= N) {
            return canMove(ox, oy, x, y, H, W);
        } else {
            return false;
        }
    }

    static boolean canMove(int ox, int oy, int x, int y, int H, int W) {
        int direction = x - ox;
        if (direction > 0) {
            for (int i = y; i < y + H; i++) {
                if (map[i][x + W - 1] == WALL) {
                    return false;
                }
            }
            return true;
        } else if (direction < 0) {
            for (int i = y; i < y + H; i++) {
                if (map[i][x] == WALL) {
                    return false;
                }
            }
            return true;
        }
        direction = y - oy;

        if (direction > 0) {
            for (int i = x; i < x + W; i++) {
                if (map[y + H - 1][i] == WALL) {
                    return false;
                }
            }
            return true;
        } else if (direction < 0) {
            for (int i = x; i < x + W; i++) {
                if (map[y][i] == WALL) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
