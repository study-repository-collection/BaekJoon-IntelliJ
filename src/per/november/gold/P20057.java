package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P20057 {
    static int[][] map;
    static Point tornado;
    static Direction tornadoDirection = Direction.WEST;
    static final int[] ratio = {1, 1, 2, 2, 5, 7, 7, 10, 10, 0};
    static final int[][] TORNADO_TO_LEFT = {{1, 1}, {1, -1}, {0, -2}, {0, 2}, {-2, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {-1, 0}};
    static final int[][] TORNADO_TO_RIGHT = {{-1, -1}, {-1, 1}, {0, -2}, {0, 2}, {2, 0}, {0, -1}, {0, 1}, {1, -1}, {1, -1}, {1, 0}};
    static final int[][] TORNADO_TO_TOP = {{-1, 1}, {1, 1}, {-2, 0}, {2, 0}, {0, -2}, {-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {0, -1}};
    static final int[][] TORNADO_TO_BOTTOM = {{-1, -1}, {1, -1}, {-2, 0}, {2, 0}, {0, 2}, {-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
            }
        }
        int center = (N + 1) / 2;
        tornado = new Point(center, center);
        System.out.println(solution(N));
    }

    static int moveTornado(int moveNumber, int N) {
        int dustSum = 0;
        while (moveNumber-- > 0) {
            switch (tornadoDirection) {
                case NORTH:
                    tornado.y--;
                    break;
                case WEST:
                    tornado.x--;
                    break;
                case SOUTH:
                    tornado.y++;
                    break;
                case EAST:
                    tornado.x++;
                    break;
            }
            dustSum += dustMove(tornado.x, tornado.y, N);
        }
        return dustSum;
    }


    static int dustMove(int x, int y, int N) {
        int dust = map[y][x];
        int remain = dust;
        int outDust = 0;
        for (int i = 0; i < 10; i++) {
            int X = x;
            int Y = y;
            switch (tornadoDirection) {
                case NORTH:
                    X += TORNADO_TO_TOP[i][0];
                    Y += TORNADO_TO_TOP[i][1];
                    break;
                case WEST:
                    X += TORNADO_TO_LEFT[i][0];
                    Y += TORNADO_TO_LEFT[i][1];
                    break;
                case SOUTH:
                    X += TORNADO_TO_BOTTOM[i][0];
                    Y += TORNADO_TO_BOTTOM[i][1];
                    break;
                case EAST:
                    X += TORNADO_TO_RIGHT[i][0];
                    Y += TORNADO_TO_RIGHT[i][1];
                    break;
            }
            int moveDust;
            if (ratio[i] == 0) {
                moveDust = remain;
            } else {
                moveDust = (int) (dust * ((double) ratio[i] / 100));
            }
            if (isInside(X, Y, N)) {
                remain -= moveDust;
                map[Y][X] += moveDust;
            } else {
                remain -= moveDust;
                outDust += moveDust;
            }
        }
        map[y][x] = 0;
        return outDust;
    }

    static boolean isInside(int x, int y, int N) {
        return (x >= 1 && y >= 1 && x <= N && y <= N);
    }

    static int solution(int N) {
        int moveNumber = 0;
        int currentMoveNumber = 1;
        int sum = 0;
        while (!(tornado.x == 0 && tornado.y == 1)) {
            sum += moveTornado(currentMoveNumber, N);
            moveNumber++;
            if (moveNumber == 2) {
                moveNumber = 0;
                currentMoveNumber++;
            }
            tornadoDirection = Direction.valueOf((tornadoDirection.value + 1) % 4);
        }
        return sum;
    }

    enum Direction {
        NORTH(0),
        WEST(1),
        SOUTH(2),
        EAST(3);

        final int value;

        Direction(int value) {
            this.value = value;
        }

        static Direction valueOf(int value) {
            switch (value) {
                case 0:
                    return NORTH;
                case 1:
                    return WEST;
                case 2:
                    return SOUTH;
                default:
                    return EAST;
            }
        }
    }
}
