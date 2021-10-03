package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        StringTokenizer input = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(input.nextToken());
        int x = Integer.parseInt(input.nextToken());
        int direction = Integer.parseInt(input.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInput = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInput.nextToken());
            }
        }
        RobotCleaner robotCleaner = new RobotCleaner(map, y, x, Direction.valueOf(direction));
        System.out.println(robotCleaner.mCleanSection);
    }


    static class RobotCleaner {
        private Direction mDirection;
        private int mCleanSection = 0;
        private int mY;
        private int mX;
        private final int[][] mMap;

        private final int EMPTY = 0;
        private final int WALL = 1;
        private final int CLEAN = 2;

        private int functionACount = 0;

        public RobotCleaner(int[][] map, int Y, int X, Direction direction) {
            this.mMap = map;
            this.mY = Y;
            this.mX = X;
            this.mDirection = direction;
            clean();
            functionA();
        }

        public void functionA() {
            functionACount++;
            int X = 0;
            int Y = 0;
            switch (mDirection) {
                case NORTH:
                    X = mX - 1;
                    Y = mY;
                    break;
                case SOUTH:
                    X = mX + 1;
                    Y = mY;
                    break;
                case EAST:
                    X = mX;
                    Y = mY - 1;
                    break;
                case WEST:
                    X = mX;
                    Y = mY + 1;
                    break;
            }
            rotate();
            if (canGo(X, Y) && mMap[Y][X] == EMPTY) {
                move(X, Y);
                clean();
                functionACount = 0;
                functionA();
            } else {
                //네 방향 모두 청소가 되있거나 벽인 경우
                if (functionACount == 4) {
                    functionACount = 0;
                    functionC();
                } else {
                    functionA();
                }
            }
        }

        public void rotate() {
            switch (mDirection) {
                case EAST:
                    mDirection = Direction.NORTH;
                    break;
                case WEST:
                    mDirection = Direction.SOUTH;
                    break;
                case NORTH:
                    mDirection = Direction.WEST;
                    break;
                case SOUTH:
                    mDirection = Direction.EAST;
                    break;
            }
        }

        private void move(int x, int y) {
            mX = x;
            mY = y;
        }

        private void clean() {
            if (mMap[mY][mX] != CLEAN) {
                mMap[mY][mX] = CLEAN;
                mCleanSection++;
            }
        }

        private boolean canGo(int x, int y) {
            return (x >= 0 && y >= 0 && x < mMap[0].length && y < mMap.length);
        }

        public void functionC() {
            int X = 0;
            int Y = 0;
            switch (mDirection) {
                case EAST:
                    X = mX - 1;
                    Y = mY;
                    break;
                case WEST:
                    X = mX + 1;
                    Y = mY;
                    break;
                case NORTH:
                    X = mX;
                    Y = mY + 1;
                    break;
                case SOUTH:
                    X = mX;
                    Y = mY - 1;
                    break;
            }
            if (canGo(X, Y) && mMap[Y][X] != WALL) {
                move(X, Y);
                functionA();
            }
        }
    }


    enum Direction {
        NORTH(0),
        SOUTH(2),
        EAST(1),
        WEST(3);

        private final int mValue;

        Direction(int value) {
            mValue = value;
        }

        public static Direction valueOf(int i) {
            switch (i) {
                case 1:
                    return EAST;
                case 2:
                    return SOUTH;
                case 3:
                    return WEST;
                default:
                    return NORTH;
            }
        }
    }
}
