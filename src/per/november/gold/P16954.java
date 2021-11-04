package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

public class P16954 {

    static final char EMPTY = '.';
    static final char WALL = '#';
    static final int[][] dxDy = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static final char[][] map = new char[8][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        System.out.println(solution() ? 1 : 0);
    }

    static boolean solution() {
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(0, 7));

        while (!points.isEmpty()) {
            int size = points.size();
            boolean[][] visited = new boolean[8][8];
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                if (point.x == 7 && point.y == 7) return true;
                if (map[point.y][point.x] == WALL) {
                    continue;
                }
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(X, Y) && map[Y][X] != WALL && !visited[Y][X]) {
                        visited[Y][X] = true;
                        points.add(new Point(X, Y));
                    }
                }
            }
            wallMove();
        }

        return false;
    }

    static void wallMove() {
        for (int i = 0; i < 8; i++) {
            map[7][i] = EMPTY;
        }
        for (int i = 7; i > 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (map[i - 1][j] == WALL) {
                    map[i - 1][j] = EMPTY;
                    map[i][j] = WALL;
                }
            }
        }
    }

    static boolean canVisit(int x, int y) {
        return (x >= 0 && y >= 0 && x < 8 && y < 8);
    }
}
