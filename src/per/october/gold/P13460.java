package per.october.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13460 {
    static final char EMPTY = '.';
    static final char WALL = '#';
    static final char HOLE = 'O';
    static final char RED = 'R';
    static final char BLUE = 'B';


    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(mapInfo.nextToken());
        int M = Integer.parseInt(mapInfo.nextToken());

        Point redPoint = null;
        Point bluePoint = null;

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == RED) {
                    redPoint = new Point(j, i);
                } else if (map[i][j] == BLUE) {
                    bluePoint = new Point(j, i);
                }
            }
        }
        System.out.println(bfs(redPoint, bluePoint));
    }

    static boolean redGoalIn = false;
    static boolean blueGoalIn = false;

    static class BallPosition {
        Point redPoint;
        Point bluePoint;

        public BallPosition(Point redPoint, Point bluePoint) {
            this.redPoint = redPoint;
            this.bluePoint = bluePoint;
        }
    }


    static int bfs(Point redPoint, Point bluePoint) {
        int count = 0;
        Queue<BallPosition> queue = new LinkedList();
        queue.add(new BallPosition(redPoint, bluePoint));
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            if (count >= 11)
                return -1;
            for (int i = 0; i < size; i++) {
                BallPosition ballPosition = queue.poll();
                for (int j = 0; j < 4; j++) {
                    redGoalIn = false;
                    blueGoalIn = false;
                    Point tempRedPoint = new Point(ballPosition.redPoint.x, ballPosition.redPoint.y);
                    Point tempBluePoint = new Point(ballPosition.bluePoint.x, ballPosition.bluePoint.y);
                    BallPosition tempBallPosition = new BallPosition(tempRedPoint, tempBluePoint);
                    boolean isMove;
                    isMove = moveBall(tempBallPosition.redPoint, tempBallPosition.bluePoint, Direction.valueOf(j));
                    if (isMove && !redGoalIn && !blueGoalIn) {
                        queue.add(tempBallPosition);
                    } else if (redGoalIn && !blueGoalIn) {
                        return count;
                    }
                }
            }
        }
        return -1;
    }

    static boolean moveBall(Point redBall, Point blueBall, Direction direction) {
        switch (direction) {
            case NORTH:
                return moveToTop(redBall, blueBall);
            case SOUTH:
                return moveToBottom(redBall, blueBall);
            case EAST:
                return moveToRight(redBall, blueBall);
            case WEST:
                return moveToLeft(redBall, blueBall);
        }
        return false;
    }

    static boolean moveToLeft(Point redBall, Point blueBall) {
        boolean isChanged = false;
        map[redBall.y][redBall.x] = RED;
        map[blueBall.y][blueBall.x] = BLUE;
        if (redBall.x < blueBall.x) {
            while (canGo(redBall, Direction.WEST)) {
                isChanged = true;
                map[redBall.y][redBall.x] = EMPTY;
                redBall.x -= 1;
                map[redBall.y][redBall.x] = RED;
            }
            if (isGoalIn(redBall, Direction.WEST)) {
                redGoalIn = true;
                map[redBall.y][redBall.x] = EMPTY;
            }
            while (canGo(blueBall, Direction.WEST)) {
                isChanged = true;
                map[blueBall.y][blueBall.x] = EMPTY;
                blueBall.x -= 1;
                map[blueBall.y][blueBall.x] = BLUE;
            }
            if (isGoalIn(blueBall, Direction.WEST)) {
                blueGoalIn = true;
                map[blueBall.y][blueBall.x] = EMPTY;
            }
        } else {
            while (canGo(blueBall, Direction.WEST)) {
                isChanged = true;
                map[blueBall.y][blueBall.x] = EMPTY;
                blueBall.x -= 1;
                map[blueBall.y][blueBall.x] = BLUE;
            }
            if (isGoalIn(blueBall, Direction.WEST)) {
                blueGoalIn = true;
                map[blueBall.y][blueBall.x] = EMPTY;
            }
            while (canGo(redBall, Direction.WEST)) {
                isChanged = true;
                map[redBall.y][redBall.x] = EMPTY;
                redBall.x -= 1;
                map[redBall.y][redBall.x] = RED;
            }
            if (isGoalIn(redBall, Direction.WEST)) {
                redGoalIn = true;
                map[redBall.y][redBall.x] = EMPTY;
            }
        }
        map[redBall.y][redBall.x] = EMPTY;
        map[blueBall.y][blueBall.x] = EMPTY;
        return isChanged;
    }

    static boolean moveToTop(Point redBall, Point blueBall) {
        boolean isChanged = false;
        map[redBall.y][redBall.x] = RED;
        map[blueBall.y][blueBall.x] = BLUE;
        if (redBall.y < blueBall.y) {
            while (canGo(redBall, Direction.NORTH)) {
                isChanged = true;
                map[redBall.y][redBall.x] = EMPTY;
                redBall.y -= 1;
                map[redBall.y][redBall.x] = RED;
            }
            if (isGoalIn(redBall, Direction.NORTH)) {
                map[redBall.y][redBall.x] = EMPTY;
                redGoalIn = true;
            }
            while (canGo(blueBall, Direction.NORTH)) {
                isChanged = true;
                map[blueBall.y][blueBall.x] = EMPTY;
                blueBall.y -= 1;
                map[blueBall.y][blueBall.x] = BLUE;
            }
            if (isGoalIn(blueBall, Direction.NORTH)) {
                map[blueBall.y][blueBall.x] = EMPTY;
                blueGoalIn = true;
            }
        } else {
            while (canGo(blueBall, Direction.NORTH)) {
                isChanged = true;
                map[blueBall.y][blueBall.x] = EMPTY;
                blueBall.y -= 1;
                map[blueBall.y][blueBall.x] = BLUE;
            }
            if (isGoalIn(blueBall, Direction.NORTH)) {
                map[blueBall.y][blueBall.x] = EMPTY;
                blueGoalIn = true;
            }
            while (canGo(redBall, Direction.NORTH)) {
                isChanged = true;
                map[redBall.y][redBall.x] = EMPTY;
                redBall.y -= 1;
                map[redBall.y][redBall.x] = RED;
            }
            if (isGoalIn(redBall, Direction.NORTH)) {
                map[redBall.y][redBall.x] = EMPTY;
                redGoalIn = true;
            }
        }
        map[redBall.y][redBall.x] = EMPTY;
        map[blueBall.y][blueBall.x] = EMPTY;
        return isChanged;
    }

    static boolean moveToBottom(Point redBall, Point blueBall) {
        boolean isChanged = false;
        map[redBall.y][redBall.x] = RED;
        map[blueBall.y][blueBall.x] = BLUE;
        if (redBall.y > blueBall.y) {
            while (canGo(redBall, Direction.SOUTH)) {
                isChanged = true;
                map[redBall.y][redBall.x] = EMPTY;
                redBall.y += 1;
                map[redBall.y][redBall.x] = RED;
            }
            if (isGoalIn(redBall, Direction.SOUTH)) {
                map[redBall.y][redBall.x] = EMPTY;
                redGoalIn = true;
            }
            while (canGo(blueBall, Direction.SOUTH)) {
                isChanged = true;
                map[blueBall.y][blueBall.x] = EMPTY;
                blueBall.y += 1;
                map[blueBall.y][blueBall.x] = BLUE;
            }
            if (isGoalIn(blueBall, Direction.SOUTH)) {
                map[blueBall.y][blueBall.x] = EMPTY;
                blueGoalIn = true;
            }
        } else {
            while (canGo(blueBall, Direction.SOUTH)) {
                isChanged = true;
                map[blueBall.y][blueBall.x] = EMPTY;
                blueBall.y += 1;
                map[blueBall.y][blueBall.x] = BLUE;
            }
            if (isGoalIn(blueBall, Direction.SOUTH)) {
                map[blueBall.y][blueBall.x] = EMPTY;
                blueGoalIn = true;
            }
            while (canGo(redBall, Direction.SOUTH)) {
                isChanged = true;
                map[redBall.y][redBall.x] = EMPTY;
                redBall.y += 1;
                map[redBall.y][redBall.x] = RED;
            }
            if (isGoalIn(redBall, Direction.SOUTH)) {
                map[redBall.y][redBall.x] = EMPTY;
                redGoalIn = true;
            }
        }
        map[redBall.y][redBall.x] = EMPTY;
        map[blueBall.y][blueBall.x] = EMPTY;
        return isChanged;
    }

    static boolean moveToRight(Point redBall, Point blueBall) {
        boolean isChanged = false;
        map[redBall.y][redBall.x] = RED;
        map[blueBall.y][blueBall.x] = BLUE;
        if (redBall.x > blueBall.x) {
            while (canGo(redBall, Direction.EAST)) {
                isChanged = true;
                map[redBall.y][redBall.x] = EMPTY;
                redBall.x += 1;
                map[redBall.y][redBall.x] = RED;
            }
            if (isGoalIn(redBall, Direction.EAST)) {
                map[redBall.y][redBall.x] = EMPTY;
                redGoalIn = true;
            }
            while (canGo(blueBall, Direction.EAST)) {
                isChanged = true;
                map[blueBall.y][blueBall.x] = EMPTY;
                blueBall.x += 1;
                map[blueBall.y][blueBall.x] = BLUE;
            }
            if (isGoalIn(blueBall, Direction.EAST)) {
                map[blueBall.y][blueBall.x] = EMPTY;
                blueGoalIn = true;
            }
        } else {
            while (canGo(blueBall, Direction.EAST)) {
                isChanged = true;
                map[blueBall.y][blueBall.x] = EMPTY;
                blueBall.x += 1;
                map[blueBall.y][blueBall.x] = BLUE;
            }
            if (isGoalIn(blueBall, Direction.EAST)) {
                map[blueBall.y][blueBall.x] = EMPTY;
                blueGoalIn = true;
            }
            while (canGo(redBall, Direction.EAST)) {
                isChanged = true;
                map[redBall.y][redBall.x] = EMPTY;
                redBall.x += 1;
                map[redBall.y][redBall.x] = RED;
            }
            if (isGoalIn(redBall, Direction.EAST)) {
                map[redBall.y][redBall.x] = EMPTY;
                redGoalIn = true;
            }
        }
        map[redBall.y][redBall.x] = EMPTY;
        map[blueBall.y][blueBall.x] = EMPTY;
        return isChanged;
    }

    static boolean canGo(Point ballPoint, Direction direction) {
        switch (direction) {
            case NORTH:
                return map[ballPoint.y - 1][ballPoint.x] == EMPTY;
            case EAST:
                return map[ballPoint.y][ballPoint.x + 1] == EMPTY;
            case SOUTH:
                return map[ballPoint.y + 1][ballPoint.x] == EMPTY;
            case WEST:
                return map[ballPoint.y][ballPoint.x - 1] == EMPTY;
        }
        return false;
    }

    static boolean isGoalIn(Point ballPoint, Direction direction) {
        switch (direction) {
            case NORTH:
                return map[ballPoint.y - 1][ballPoint.x] == HOLE;
            case EAST:
                return map[ballPoint.y][ballPoint.x + 1] == HOLE;
            case SOUTH:
                return map[ballPoint.y + 1][ballPoint.x] == HOLE;
            case WEST:
                return map[ballPoint.y][ballPoint.x - 1] == HOLE;
        }
        return false;
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
