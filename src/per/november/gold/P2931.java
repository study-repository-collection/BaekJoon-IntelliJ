package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2931 {
    static final char EMPTY = '.';
    static final char VERTICAL_BLOCK = '|';
    static final char HORIZONTAL_BLOCK = '-';
    static final char PLUS = '+';
    static final char MOSCOW = 'M';
    static final char ZAGREV = 'Z';

    static int[][] blockI = {{0, -1}, {0, 1}};
    static int[][] blockHorizontal = {{-1, 0}, {1, 0}};
    static int[][] blockPlus = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] blockOne = {{0, 1}, {1, 0}};
    static int[][] blockTwo = {{0, -1}, {1, 0}};
    static int[][] blockThree = {{-1, 0}, {0, -1}};
    static int[][] blockFour = {{-1, 0}, {0, 1}};
    static char[][] map;

    static Point moscowPoint;
    static Point zagrevPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapInfo = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(mapInfo.nextToken());
        int C = Integer.parseInt(mapInfo.nextToken());
        map = new char[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            String input = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = input.charAt(j - 1);
                if (map[i][j] == MOSCOW) {
                    moscowPoint = new Point(j, i);
                } else if (map[i][j] == ZAGREV) {
                    zagrevPoint = new Point(j, i);
                }
            }
        }
        Point startPoint = findStart(R, C);
        Point removedPoint = findRemovedSpace(R, C, startPoint);
        System.out.println(removedPoint.y + " " + removedPoint.x + " " + answer(removedPoint.x, removedPoint.y, R, C));
    }


    static char answer(int x, int y, int R, int C) {
        boolean topConnection = false;
        boolean bottomConnection = false;
        boolean leftConnection = false;
        boolean rightConnection = false;
        for (int i = 0; i < 4; i++) {
            int X = x + blockPlus[i][0];
            int Y = y + blockPlus[i][1];
            if (canVisit(X, Y, R, C)) {
                switch (i) {
                    case 0:
                        if (map[Y][X] == HORIZONTAL_BLOCK || map[Y][X] == PLUS || map[Y][X] == '1' || map[Y][X] == '2') {
                            leftConnection = true;
                        }
                        break;
                    case 1:
                        if (map[Y][X] == VERTICAL_BLOCK || map[Y][X] == PLUS || map[Y][X] == '1' || map[Y][X] == '4') {
                            topConnection = true;
                        }
                        break;
                    case 2:
                        if (map[Y][X] == HORIZONTAL_BLOCK || map[Y][X] == PLUS || map[Y][X] == '3' || map[Y][X] == '4') {
                            rightConnection = true;
                        }
                        break;
                    case 3:
                        if (map[Y][X] == VERTICAL_BLOCK || map[Y][X] == PLUS || map[Y][X] == '2' || map[Y][X] == '3') {
                            bottomConnection = true;
                        }
                        break;
                }
            }
        }
        if (topConnection && bottomConnection && leftConnection && rightConnection) {
            return '+';
        } else if (topConnection && bottomConnection) {
            return '|';
        } else if (leftConnection && rightConnection) {
            return '-';
        } else if (rightConnection && bottomConnection) {
            return '1';
        } else if (topConnection && rightConnection) {
            return '2';
        } else if (leftConnection && topConnection) {
            return '3';
        } else {
            return '4';
        }
    }

    static Point findRemovedSpace(int R, int C, Point startPoint) {
        boolean[][] visited = new boolean[R + 1][C + 1];
        Queue<Point> points = new LinkedList<>();
        visited[startPoint.y][startPoint.x] = true;
        points.add(startPoint);

        while (!points.isEmpty()) {
            Point point = points.poll();

            switch (map[point.y][point.x]) {
                case VERTICAL_BLOCK:
                    for (int i = 0; i < blockI.length; i++) {
                        int X = point.x + blockI[i][0];
                        int Y = point.y + blockI[i][1];
                        if (canVisit(X, Y, R, C)) {
                            if (map[Y][X] == EMPTY) {
                                return new Point(X, Y);
                            } else if (!visited[Y][X] && map[Y][X] != ZAGREV && map[Y][X] != MOSCOW) {
                                visited[Y][X] = true;
                                points.add(new Point(X, Y));
                            }
                        }
                    }
                    break;
                case HORIZONTAL_BLOCK:
                    for (int i = 0; i < blockHorizontal.length; i++) {
                        int X = point.x + blockHorizontal[i][0];
                        int Y = point.y + blockHorizontal[i][1];
                        if (canVisit(X, Y, R, C)) {
                            if (map[Y][X] == EMPTY) {
                                return new Point(X, Y);
                            } else if (!visited[Y][X] && map[Y][X] != ZAGREV && map[Y][X] != MOSCOW) {
                                visited[Y][X] = true;
                                points.add(new Point(X, Y));
                            }
                        }
                    }
                    break;
                case PLUS:
                    for (int i = 0; i < blockPlus.length; i++) {
                        int X = point.x + blockPlus[i][0];
                        int Y = point.y + blockPlus[i][1];
                        if (canVisit(X, Y, R, C)) {
                            if (map[Y][X] == EMPTY) {
                                return new Point(X, Y);
                            } else if (!visited[Y][X] && map[Y][X] != ZAGREV && map[Y][X] != MOSCOW) {
                                visited[Y][X] = true;
                                points.add(new Point(X, Y));
                            }
                        }
                    }
                    break;
                case '1':
                    for (int i = 0; i < blockOne.length; i++) {
                        int X = point.x + blockOne[i][0];
                        int Y = point.y + blockOne[i][1];
                        if (canVisit(X, Y, R, C)) {
                            if (map[Y][X] == EMPTY) {
                                return new Point(X, Y);
                            } else if (!visited[Y][X] && map[Y][X] != ZAGREV && map[Y][X] != MOSCOW) {
                                visited[Y][X] = true;
                                points.add(new Point(X, Y));
                            }
                        }
                    }
                    break;
                case '2':
                    for (int i = 0; i < blockTwo.length; i++) {
                        int X = point.x + blockTwo[i][0];
                        int Y = point.y + blockTwo[i][1];
                        if (canVisit(X, Y, R, C)) {
                            if (map[Y][X] == EMPTY) {
                                return new Point(X, Y);
                            } else if (!visited[Y][X] && map[Y][X] != ZAGREV && map[Y][X] != MOSCOW) {
                                visited[Y][X] = true;
                                points.add(new Point(X, Y));
                            }
                        }
                    }
                    break;
                case '3':
                    for (int i = 0; i < blockThree.length; i++) {
                        int X = point.x + blockThree[i][0];
                        int Y = point.y + blockThree[i][1];
                        if (canVisit(X, Y, R, C)) {
                            if (map[Y][X] == EMPTY) {
                                return new Point(X, Y);
                            } else if (!visited[Y][X] && map[Y][X] != ZAGREV && map[Y][X] != MOSCOW) {
                                visited[Y][X] = true;
                                points.add(new Point(X, Y));
                            }
                        }
                    }
                    break;
                case '4':
                    for (int i = 0; i < blockFour.length; i++) {
                        int X = point.x + blockFour[i][0];
                        int Y = point.y + blockFour[i][1];
                        if (canVisit(X, Y, R, C)) {
                            if (map[Y][X] == EMPTY) {
                                return new Point(X, Y);
                            } else if (!visited[Y][X] && map[Y][X] != ZAGREV && map[Y][X] != MOSCOW) {
                                visited[Y][X] = true;
                                points.add(new Point(X, Y));
                            }
                        }
                    }
                    break;
            }
        }
        return null;
    }

    static Point findStart(int R, int C) {
        for (int i = 0; i < 4; i++) {
            int X = moscowPoint.x + blockPlus[i][0];
            int Y = moscowPoint.y + blockPlus[i][1];
            if (canVisit(X, Y, R, C) && map[Y][X] != EMPTY && map[Y][X] != ZAGREV) {
                return new Point(X, Y);
            }
        }
        for (int i = 0; i < 4; i++) {
            int X = zagrevPoint.x + blockPlus[i][0];
            int Y = zagrevPoint.y + blockPlus[i][1];
            if (canVisit(X, Y, R, C) && map[Y][X] != EMPTY && map[Y][X] != MOSCOW) {
                return new Point(X, Y);
            }
        }
        return null;
    }

    static boolean canVisit(int x, int y, int R, int C) {
        return (x >= 1 && y >= 1 && x <= C && y <= R);
    }
}
