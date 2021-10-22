package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P6087 {

    static final char EMPTY = '.';
    static final char WALL = '*';
    static final char OBJECTIVE = 'C';

    static char[][] map;

    static java.awt.Point firstC1;
    static java.awt.Point secondC1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(mapInfo.nextToken());
        int H = Integer.parseInt(mapInfo.nextToken());
        map = new char[H][W];

        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'C') {
                    if (firstC1 == null) {
                        firstC1 = new java.awt.Point(j, i);
                    } else {
                        secondC1 = new java.awt.Point(j, i);
                    }
                }
            }
        }
        System.out.println(bfs(H, W));
    }

    static int bfs(int N, int M) {
        int[][][] minCount = new int[N][M][4];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                Arrays.fill(minCount[i][j], 987654321);
            }
        Queue<Point> queue = new LinkedList<>();
        minCount[firstC1.y][firstC1.x][0] = 0;
        minCount[firstC1.y][firstC1.x][1] = 0;
        minCount[firstC1.y][firstC1.x][2] = 0;
        minCount[firstC1.y][firstC1.x][3] = 0;

        queue.add(new Point(firstC1.x, firstC1.y, Direction.NORTH, 0));
        queue.add(new Point(firstC1.x, firstC1.y, Direction.SOUTH, 0));
        queue.add(new Point(firstC1.x, firstC1.y, Direction.EAST, 0));
        queue.add(new Point(firstC1.x, firstC1.y, Direction.WEST, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = -1; i < 2; i++) {
                int value = point.direction.value + i;
                if (value < 0) {
                    value = 3;
                } else {
                    value = value % 4;
                }
                int X = point.x;
                int Y = point.y;
                switch (Direction.valueOf(value)) {
                    case NORTH:
                        Y -= 1;
                        break;
                    case EAST:
                        X += 1;
                        break;
                    case SOUTH:
                        Y += 1;
                        break;
                    case WEST:
                        X -= 1;
                        break;
                }
                if (canGo(X, Y, N, M) && minCount[Y][X][value] > point.rotateCount + Math.abs(i)) {
                    minCount[Y][X][value] = point.rotateCount + Math.abs(i);
                    queue.add(new Point(X, Y, Direction.valueOf(value), point.rotateCount + Math.abs(i)));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            min = Math.min(minCount[secondC1.y][secondC1.x][i], min);
        }
        return min;
    }

    static boolean canGo(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N && map[y][x] != WALL);
    }

    static class Point {
        int x;
        int y;
        Direction direction;
        int rotateCount = 0;

        public Point(int x, int y, Direction direction, int rotateCount) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.rotateCount = rotateCount;
        }
    }

    enum Direction {
        NORTH(0),
        EAST(1),
        SOUTH(2),
        WEST(3);

        final int value;

        Direction(int i) {
            value = i;
        }

        static Direction valueOf(int i) {
            switch (i) {
                case 0:
                    return NORTH;
                case 1:
                    return EAST;
                case 2:
                    return SOUTH;
                case 3:
                    return WEST;
            }
            return NORTH;
        }
    }
}
