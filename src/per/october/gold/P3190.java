package per.october.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P3190 {

    static int[][] map;
    static final int APPLE = 5;
    static final int LEFT = 'L';
    static final int RIGHT = 'D';
    static final int SNAKE_HEAD = 1;
    static final int SNAKE_BODY = 2;
    static Queue<Point> moveInfos = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer apple = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(apple.nextToken());
            int x = Integer.parseInt(apple.nextToken());
            map[y][x] = APPLE;
        }
        int L = Integer.parseInt(br.readLine());

        while (L-- > 0) {
            StringTokenizer moveInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(moveInfo.nextToken());
            int b = moveInfo.nextToken().charAt(0);
            moveInfos.add(new Point(a, b));
        }
        System.out.println(solution(N));
    }

    static int solution(int N) {
        int time = 0;
        Deque<Point> snakeBody = new LinkedList<>();
        Point snakeHead = new Point(1, 1);
        map[1][1] = SNAKE_HEAD;
        Direction currentDirection = Direction.EAST;

        while (true) {
            Point moveInfo = moveInfos.peek();
            if (moveInfo != null && moveInfo.x == time) {
                int rotate = moveInfo.y;
                int to;
                if (rotate == RIGHT) {
                    to = currentDirection.value;
                    to = (to + 1) % 4;
                } else {
                    to = currentDirection.value;
                    to = to - 1;
                    if (to < 0) {
                        to = 3;
                    }
                }
                currentDirection = Direction.valueOf(to);
                moveInfos.poll();
            }
            time++;
            if (!move(snakeHead, snakeBody, currentDirection, N)) {
                break;
            }
        }
        return time;
    }

    static boolean move(Point snakeHead, Deque<Point> snakeBody, Direction currentDirection, int N) {
        int X = snakeHead.x;
        int Y = snakeHead.y;
        switch (currentDirection) {
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
        if (canMove(X, Y, N)) {
            if (map[Y][X] == 0) {
                snakeBody.offerFirst(new Point(snakeHead.x, snakeHead.y));
                map[snakeHead.y][snakeHead.x] = SNAKE_BODY;
                Point point = snakeBody.pollLast();
                map[point.y][point.x] = 0;
                snakeHead.x = X;
                snakeHead.y = Y;
                map[Y][X] = SNAKE_HEAD;
            } else if (map[Y][X] == APPLE) {
                snakeBody.offerFirst(new Point(snakeHead.x, snakeHead.y));
                map[snakeHead.y][snakeHead.x] = SNAKE_BODY;
                snakeHead.x = X;
                snakeHead.y = Y;
                map[Y][X] = SNAKE_HEAD;
            } else if (map[Y][X] == SNAKE_BODY) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    static boolean canMove(int x, int y, int N) {
        return (x >= 1 && y >= 1 && x <= N && y <= N);
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
