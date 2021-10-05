package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17143 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); //격자판 높이
        int C = Integer.parseInt(st.nextToken()); //격자판 길이
        int M = Integer.parseInt(st.nextToken()); //상어의 수

        Shark[][] map = new Shark[R + 1][C + 1];

        //상어 정보 입력
        for (int i = 0; i < M; i++) {
            StringTokenizer sharkInfo = new StringTokenizer(br.readLine());
            Shark shark = new Shark(Integer.parseInt(sharkInfo.nextToken()), Integer.parseInt(sharkInfo.nextToken()), Integer.parseInt(sharkInfo.nextToken()),
                    Direction.valueOf(Integer.parseInt(sharkInfo.nextToken())), Integer.parseInt(sharkInfo.nextToken()));
            map[shark.mCurrentPosition.y][shark.mCurrentPosition.x] = shark;
        }
        //상어 정보 입력 끝
        Angler angler = new Angler(map);
        fishing(angler, R, C);
        System.out.println(angler.sumOfCaughtSharkSize());

    }

    /**
     * 낚시꾼 클래스
     */
    static class Angler {
        private int mCurrentPosition = 0;
        private final ArrayList<Shark> mCaughtShark = new ArrayList<>(); //잡은 상어 리스트
        private Shark[][] mMap;

        public Angler(Shark[][] map) {
            mMap = map;
        }

        public void move() {
            mCurrentPosition++;
        }

        public int sumOfCaughtSharkSize() {
            int sum = 0;
            for (Shark shark : mCaughtShark) {
                sum += shark.mSize;
            }
            return sum;
        }

        /**
         * 상어 잡는 함수
         */
        public void catchShark() {
            for (int i = 1; i <= mMap.length - 1; i++) {
                if (null != mMap[i][mCurrentPosition]) {
                    mCaughtShark.add(mMap[i][mCurrentPosition]);
                    mMap[i][mCurrentPosition] = null;
                    break;
                }
            }
        }
    }

    static void fishing(Angler angler, int R, int C) {
        while (angler.mCurrentPosition != C) {
            angler.move();
            angler.catchShark();
            Shark[][] map = new Shark[R + 1][C + 1];
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    if (null != angler.mMap[i][j]) {
                        angler.mMap[i][j].move(map, R, C);
                    }
                }
            }
            angler.mMap = map;
        }
    }

    /**
     * 상어 클래스
     */
    static class Shark {
        private final Point mCurrentPosition; //상어의 현재 위치
        private final int mSpeed; //상어의 속도
        private Direction mDirection; //상어의 진행방향
        private final int mSize; //상어의 크기

        public Shark(int y, int x, int speed, Direction direction, int size) {
            mCurrentPosition = new Point(x, y);
            mSpeed = speed;
            mDirection = direction;
            mSize = size;
        }


        public void move(Shark[][] map, int R, int C) {
            switch (mDirection) {
                case SOUTH:
                case NORTH:
                    moveVertical(map, R);
                    break;
                case EAST:
                case WEST:
                    moveHorizontal(map, C);
                    break;
            }
        }

        private void moveVertical(Shark[][] map, int R) {
            int Y = mCurrentPosition.y;
            int backToOriginalCount = 2 * (R - 1);
            int remainSpeed = mSpeed % backToOriginalCount;
            for (int i = 0; i < remainSpeed; i++) {
                if (Y == 1) {
                    Y = 2;
                    mDirection = Direction.SOUTH;
                } else if (Y == R) {
                    Y = R - 1;
                    mDirection = Direction.NORTH;
                } else {
                    switch (mDirection) {
                        case NORTH:
                            Y -= 1;
                            break;
                        case SOUTH:
                            Y += 1;
                            break;
                    }
                }
            }
            mCurrentPosition.y = Y;
            if (null == map[Y][mCurrentPosition.x]) {
                map[Y][mCurrentPosition.x] = this;
            } else if (map[Y][mCurrentPosition.x].mSize < mSize) {
                map[Y][mCurrentPosition.x] = this;
            }
        }

        private void moveHorizontal(Shark[][] map, int C) {
            int X = mCurrentPosition.x;
            int backToOriginalCount = 2 * (C - 1);
            int remainSpeed = mSpeed % backToOriginalCount;
            for (int i = 0; i < remainSpeed; i++) {
                if (X == 1) {
                    X = 2;
                    mDirection = Direction.EAST;
                } else if (X == C) {
                    X = C - 1;
                    mDirection = Direction.WEST;
                } else {
                    switch (mDirection) {
                        case EAST:
                            X += 1;
                            break;
                        case WEST:
                            X -= 1;
                            break;
                    }
                }
            }
            //이동종료
            mCurrentPosition.x = X;
            if (null == map[mCurrentPosition.y][X]) {
                map[mCurrentPosition.y][X] = this;
            } else if (map[mCurrentPosition.y][X].mSize < mSize) {
                map[mCurrentPosition.y][X] = this;
            }
        }
    }

    enum Direction {
        NORTH(1),
        EAST(3),
        SOUTH(2),
        WEST(4);


        Direction(int value) {
        }

        public static Direction valueOf(int value) {
            switch (value) {
                case 1:
                    return NORTH;
                case 2:
                    return SOUTH;
                case 3:
                    return EAST;
                default:
                    return WEST;
            }
        }
    }
}
