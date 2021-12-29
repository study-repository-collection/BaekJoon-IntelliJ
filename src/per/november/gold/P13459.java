package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13459 {

    static char[][] map;

    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static final char EMPTY = '.';
    static final char WALL = '#';
    static final char HOLE = 'O';
    static final char RED_BALL = 'R';
    static final char BLUE_BALL = 'B';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Point redBallPoint = null;
        Point blueBallPoint = null;
        Point holePoint = null;
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String mapInfo = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = mapInfo.charAt(j);
                if (map[i][j] == RED_BALL) {
                    redBallPoint = new Point(j, i);
                } else if (map[i][j] == BLUE_BALL) {
                    blueBallPoint = new Point(j, i);
                } else if (map[i][j] == HOLE) {
                    holePoint = new Point(j, i);
                }
            }
        }
        map[redBallPoint.y][redBallPoint.x] = EMPTY;
        map[blueBallPoint.y][blueBallPoint.x] = EMPTY;
        if (canVisit(redBallPoint, blueBallPoint, 0, holePoint)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static boolean canVisit(Point red, Point blue, int count, Point hole) {
        if (count == 10) return false;
        boolean ret = false;
        for (int[] move : dxDy) {
            Pair<Point, Point> nextPoint = moveBalls(move, red, blue, hole);
            Point nextRedPoint = nextPoint.first;
            Point nextBluePoint = nextPoint.second;
            //움직인 레드 볼이 구멍에 빠졌으면?
            if (pointEqual(nextRedPoint, hole)) {
                //블루 볼이 같이 안 빠졋을대 성공임!
                if (!pointEqual(nextBluePoint, hole)) {
                    return true;
                }
            }
            //둘 다 빠지지 않았을 경우 계속 진행
            else if (!pointEqual(nextBluePoint, hole)) {
                ret |= canVisit(nextRedPoint, nextBluePoint, count + 1, hole);
            }
        }

        return ret;
    }

    static boolean pointEqual(Point first, Point second) {
        return first.x == second.x && first.y == second.y;
    }

    static Pair<Point, Point> moveBalls(int[] move, Point red, Point blue, Point hole) {
        boolean redFirst = whosFirst(move, red, blue);
        Point retRed;
        Point retBlue;
        if (redFirst) {
            retRed = moveBall(RED_BALL, move, red);
            if (!pointEqual(retRed, hole)) {
                map[retRed.y][retRed.x] = RED_BALL;
            }
            retBlue = moveBall(BLUE_BALL, move, blue);
            if (!pointEqual(retBlue, hole)) {
                map[retBlue.y][retBlue.x] = BLUE_BALL;
            }
        } else {
            retBlue = moveBall(BLUE_BALL, move, blue);
            if (!pointEqual(retBlue, hole)) {
                map[retBlue.y][retBlue.x] = BLUE_BALL;
            }
            retRed = moveBall(RED_BALL, move, red);
            if (!pointEqual(retRed, hole)) {
                map[retRed.y][retRed.x] = RED_BALL;
            }
        }
        map[retRed.y][retRed.x] = EMPTY;
        map[retBlue.y][retBlue.x] = EMPTY;
        map[hole.y][hole.x] = HOLE;
        return new Pair<>(retRed, retBlue);
    }

    static Point moveBall(char draw, int[] move, Point point) {
        int X = point.x;
        int Y = point.y;
        while (true) {
            int tempX = X + move[0];
            int tempY = Y + move[1];
            if (map[tempY][tempX] == EMPTY) {
                X = tempX;
                Y = tempY;
            } else if (map[tempY][tempX] == HOLE) {
                return new Point(tempX, tempY);
            } else {
                break;
            }
        }
        map[Y][X] = draw;
        return new Point(X, Y);
    }


    //무슨 공을 먼저 움직여야 할까 ㅎㅎ
    //true 일 시 red를 먼저 움직여야 함!
    static boolean whosFirst(int[] move, Point red, Point blue) {
        if (move[0] == -1) {
            return red.x < blue.x;
        } else if (move[0] == 1) {
            return blue.x < red.x;
        } else if (move[1] == -1) {
            return red.y < blue.y;
        } else {
            return blue.y < red.y;
        }
    }

    static class Pair<T, K> {
        T first;
        K second;

        public Pair(T first, K second) {
            this.first = first;
            this.second = second;
        }
    }
}
