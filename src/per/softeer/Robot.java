package per.softeer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Robot {
    static final char VISIT = '#';
    static final char NOT_VISIT = '.';

    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(input.nextToken()); //높이
        int W = Integer.parseInt(input.nextToken()); //넓이
        map = new char[H + 1][W + 1];
        for (int i = 1; i <= H; i++) {
            String mapInfo = br.readLine();
            for (int j = 1; j <= W; j++) {
                map[i][j] = mapInfo.charAt(j - 1);
            }
        }
        solution(H, W);
    }
    //로봇은 2칸씩 띌수 있기 떄문에, 시작이 짝수면 항상 짝수에서 멈추고, 홀수면 홀수에서 멈춤
    //visit 배열을 두고, 한 개 이상 visit 하지 않은 곳만 탐색해가며, 더 이상 탐색할게 없을떄 모든 부분을 탐색해야 끝임 ㅎㅎㅎ

    static void solution(int H, int W) {
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                if (map[i][j] == VISIT) {
                    int count = 0;
                    Direction startDirection = null;
                    for (int k = 0; k < 4; k++) {
                        int X = j + dxDy[k][0];
                        int Y = i + dxDy[k][1];
                        if (canVisit(new Point(X, Y), H, W) && map[Y][X] == VISIT) {
                            switch (k) {
                                case 0:
                                    startDirection = Direction.WEST;
                                    break;
                                case 1:
                                    startDirection = Direction.NORTH;
                                    break;
                                case 2:
                                    startDirection = Direction.EAST;
                                    break;
                                case 3:
                                    startDirection = Direction.SOUTH;
                            }
                            count++;
                        }
                    }
                    if (count == 1) {
                        map[i][j] = NOT_VISIT;
                        answer(startDirection, new Point(j, i), H, W);
                        return;
                    }
                }
            }
        }
    }

    static void answer(Direction startDirection, Point startPoint, int H, int W) {

        StringBuilder answer = new StringBuilder();
        Point currentPoint = startPoint;
        Direction currentDirection = startDirection;
        while (true) {
            Direction existDirection = null;
            int nextX = 0;
            int nextY = 0;
            for (int i = 0; i < 4; i++) {
                int XX = currentPoint.x + dxDy[i][0];
                int YY = currentPoint.y + dxDy[i][1];
                int X = currentPoint.x + dxDy[i][0] * 2;
                int Y = currentPoint.y + dxDy[i][1] * 2;
                if (canVisit(new Point(X, Y), H, W) && map[Y][X] == VISIT && map[YY][XX] == VISIT) {
                    switch (i) {
                        case 0:
                            existDirection = Direction.WEST;
                            break;
                        case 1:
                            existDirection = Direction.NORTH;
                            break;
                        case 2:
                            existDirection = Direction.EAST;
                            break;
                        case 3:
                            existDirection = Direction.SOUTH;
                            break;
                    }
                    nextY = Y;
                    nextX = X;
                    map[Y][X] = NOT_VISIT;
                }
            }
            if (existDirection == null) break;
            else if (existDirection == currentDirection) {
                answer.append("A");
                currentPoint = new Point(nextX, nextY);
                continue;
            } else {
                Direction tempDirection = currentDirection;
                int LCount = 0;
                while (tempDirection != existDirection) {
                    tempDirection = operationL(tempDirection);
                    LCount++;
                }
                tempDirection = currentDirection;
                int RCount = 0;
                while (tempDirection != existDirection) {
                    tempDirection = operationR(tempDirection);
                    RCount++;
                }
                if (LCount > RCount) {
                    for (int i = 0; i < RCount; i++) {
                        answer.append("R");
                    }
                    answer.append("A");
                } else {
                    for (int i = 0; i < LCount; i++) {
                        answer.append("L");
                    }
                    answer.append("A");
                }
                currentDirection = existDirection;
                currentPoint = new Point(nextX, nextY);
            }

        }

        System.out.println(startPoint.y + " " + startPoint.x);
        System.out.println(startDirection.value);
        System.out.println(answer);
    }


    static Direction operationL(Direction direction) {
        switch (direction) {
            case EAST:
                return Direction.NORTH;
            case WEST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.EAST;
            default:
                return Direction.WEST;
        }
    }

    static Direction operationR(Direction direction) {
        switch (direction) {
            case EAST:
                return Direction.SOUTH;
            case WEST:
                return Direction.NORTH;
            case SOUTH:
                return Direction.WEST;
            default:
                return Direction.EAST;
        }
    }

    static Point operationA(Point point, Direction direction) {
        switch (direction) {
            case EAST:
                return new Point(point.x + 2, point.y);
            case WEST:
                return new Point(point.x - 2, point.y);
            case SOUTH:
                return new Point(point.x, point.y + 2);
            default:
                return new Point(point.x, point.y - 2);
        }
    }

    static boolean canVisit(Point point, int H, int W) {
        return point.x >= 1 && point.y >= 1 && point.x <= W && point.y <= H;
    }

    enum Direction {
        EAST('>'),
        WEST('<'),
        SOUTH('v'),
        NORTH('^');

        final char value;

        Direction(char c) {
            value = c;
        }
    }
}
