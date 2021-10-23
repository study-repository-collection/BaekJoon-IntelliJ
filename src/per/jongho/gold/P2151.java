package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

public class P2151 {

    static char[][] map;
    static final char WALL = '*';
    static final char CAN_INSTALL = '!';
    static final char EMPTY = '.';
    static final char DOOR = '#';


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        Point firstDoor = null;
        Point secondDoor = null;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == DOOR) {
                    if (null == firstDoor) {
                        firstDoor = new Point(j, i);
                    } else {
                        secondDoor = new Point(j, i);
                    }
                }
            }
        }
        System.out.println(solution(N, firstDoor, secondDoor));
    }

    static final int INF = 987654321;

    static int solution(int N, Point start, Point end) {
        int[][][] minCount = new int[N][N][4];
        Queue<Info> infos = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(minCount[i][j], INF);
            }
        }
        infos.add(new Info(start.x, start.y, 0, Direction.NORTH));
        infos.add(new Info(start.x, start.y, 0, Direction.EAST));
        infos.add(new Info(start.x, start.y, 0, Direction.SOUTH));
        infos.add(new Info(start.x, start.y, 0, Direction.WEST));


        while (!infos.isEmpty()) {
            Info info = infos.poll();
            if (map[info.y][info.x] == CAN_INSTALL) {
                for (int i = -1; i < 2; i++) {
                    int direction = info.direction.value + i;
                    if (direction < 0) direction = 3;
                    else direction = direction % 4;
                    int X = info.x;
                    int Y = info.y;
                    int rotateCount = info.rotateCount + Math.abs(i);
                    switch (Direction.valueOf(direction)) {
                        case NORTH:
                            Y -= 1;
                            break;
                        case SOUTH:
                            Y += 1;
                            break;
                        case WEST:
                            X -= 1;
                            break;
                        default:
                            X += 1;
                            break;
                    }
                    if (canGo(X, Y, N) && minCount[Y][X][direction] > rotateCount) {
                        minCount[Y][X][direction] = rotateCount;
                        infos.add(new Info(X, Y, rotateCount, Direction.valueOf(direction)));
                    }
                }
            } else {
                int X = info.x;
                int Y = info.y;
                switch (info.direction) {
                    case NORTH:
                        Y -= 1;
                        break;
                    case SOUTH:
                        Y += 1;
                        break;
                    case WEST:
                        X -= 1;
                        break;
                    default:
                        X += 1;
                        break;
                }
                if (canGo(X, Y, N) && minCount[Y][X][info.direction.value] > info.rotateCount) {
                    minCount[Y][X][info.direction.value] = info.rotateCount;
                    info.x = X;
                    info.y = Y;
                    infos.add(info);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            min = Math.min(minCount[end.y][end.x][i], min);
        }
        return min;
    }


    static boolean canGo(int x, int y, int N) {
        return (x >= 0 && y >= 0 && x < N && y < N && map[y][x] != WALL);
    }

    static class Info {
        int x;
        int y;
        int rotateCount;
        Direction direction;

        public Info(int x, int y, int rotateCount, Direction direction) {
            this.x = x;
            this.y = y;
            this.rotateCount = rotateCount;
            this.direction = direction;
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
