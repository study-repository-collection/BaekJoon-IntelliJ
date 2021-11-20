package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P4179 {
    static final char WALL = '#';
    static final char EMPTY = '.';
    static final char JIHUN = 'J';
    static final char FIRE = 'F';

    static final String Impossible = "IMPOSSIBLE";
    static char[][] map;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static Queue<Point> Fire = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer info = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(info.nextToken());
        int C = Integer.parseInt(info.nextToken());
        map = new char[R][C];
        Point JihunPosition = null;
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == JIHUN) {
                    JihunPosition = new Point(j, i);
                } else if (map[i][j] == FIRE) {
                    Fire.add(new Point(j, i));
                }
            }
        }
        int ret = solution(R, C, JihunPosition);
        System.out.println(ret == -1 ? Impossible : ret);
    }

    static int solution(int R, int C, Point JihunPosition) {
        int count = 0;
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        visited[JihunPosition.y][JihunPosition.x] = true;
        queue.add(JihunPosition);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point point = queue.poll();
                if (map[point.y][point.x] == FIRE) {
                    continue;
                }
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(X, Y, R, C)) {
                        if (!visited[Y][X] && map[Y][X] == EMPTY) {
                            visited[Y][X] = true;
                            queue.add(new Point(X, Y));
                        }
                    } else {
                        return count + 1;
                    }
                }
            }
            count++;
            fireSpread(R, C);
        }
        return -1;
    }

    static void fireSpread(int R, int C) {
        int size = Fire.size();
        for (int i = 0; i < size; i++) {
            Point point = Fire.poll();
            for (int[] move : dxDy) {
                int X = point.x + move[0];
                int Y = point.y + move[1];
                if (canVisit(X, Y, R, C) && map[Y][X] == EMPTY) {
                    map[Y][X] = FIRE;
                    Fire.add(new Point(X, Y));
                }
            }
        }
    }

    static boolean canVisit(int x, int y, int R, int C) {
        return x >= 0 && y >= 0 && x < C && y < R;
    }
}
