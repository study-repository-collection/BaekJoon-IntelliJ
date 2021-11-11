package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16724 {

    static char[][] map;
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(mapInfo.nextToken());
        int M = Integer.parseInt(mapInfo.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        int count = 0;
        boolean[][] visited = new boolean[N][M];
        boolean[][] destinationIsDefined = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    count += dfs(destinationIsDefined, visited, j, i);
                }
            }
        }
        return count;
    }

    static int dfs(boolean[][] destinationIsDefined, boolean[][] visited, int x, int y) {
        ArrayList<Point> points = new ArrayList<>();
        while (true) {
            visited[y][x] = true;
            points.add(new Point(x, y));
            int[] move = move(Direction.valueOf(map[y][x]));
            x = x + move[0];
            y = y + move[1];
            if (visited[y][x]) {
                if (destinationIsDefined[y][x]) {
                    for (Point point : points) destinationIsDefined[point.y][point.x] = true;
                    return 0;
                } else {
                    for (Point point : points) destinationIsDefined[point.y][point.x] = true;
                    return 1;
                }
            }
        }

    }

    static int[] move(Direction direction) {
        switch (direction) {
            case DOWN:
                return dxDy[3];
            case LEFT:
                return dxDy[0];
            case RIGHT:
                return dxDy[2];
            default:
                return dxDy[1];
        }
    }

    enum Direction {
        DOWN('D'),
        LEFT('L'),
        RIGHT('R'),
        UP('U');

        final char value;

        Direction(char value) {
            this.value = value;
        }

        static Direction valueOf(char value) {
            switch (value) {
                case 'D':
                    return DOWN;
                case 'L':
                    return LEFT;
                case 'R':
                    return RIGHT;
                default:
                    return UP;
            }
        }
    }
}
