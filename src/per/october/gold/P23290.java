package per.october.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P23290 {

    static int[][] direction = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    static int[][] sharkMove = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    static Queue<Integer>[][][] map = new Queue[5][5][3];
    static int[][] fishSmokeMap = new int[5][5];
    static Point shark;

    static final int Current = 0;
    static final int ToCopy = 1;
    static final int Moved = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        initMap();
        int M = Integer.parseInt(st.nextToken());//물고기의 수
        int S = Integer.parseInt(st.nextToken());//상어가 마법을 연습한 횟수
        while (M-- > 0) {
            StringTokenizer fishInfo = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(fishInfo.nextToken());//y좌표
            int c = Integer.parseInt(fishInfo.nextToken());//x좌표
            int d = Integer.parseInt(fishInfo.nextToken());//물고기의 방향
            map[r][c][Current].add(d);
        }

        StringTokenizer sharkInfo = new StringTokenizer(br.readLine());
        int sharkR = Integer.parseInt(sharkInfo.nextToken()); //상어의 y좌표
        int sharkC = Integer.parseInt(sharkInfo.nextToken()); //상어의 x좌표
        shark = new Point(sharkC, sharkR);

        while (S-- > 0) {
            doCopyMagic();
            fishMove();
            moveShark();
            removeSmoke();
            copyMagicComplete();
        }
        System.out.println(answer());
    }


    static int answer() {
        int sum = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                sum += map[i][j][Current].size();
            }
        }
        return sum;
    }

    static void doCopyMagic() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int value : map[i][j][Current]) {
                    map[i][j][ToCopy].add(value);
                }
            }
        }
    }

    static void fishMove() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (map[i][j][Current].isEmpty()) continue;
                int size = map[i][j][Current].size();
                for (int k = 0; k < size; k++) {
                    boolean isMove = false;
                    int fishDirection = map[i][j][Current].poll();
                    for (int l = 0; l < 8; l++) {
                        int X = j + direction[fishDirection][0];
                        int Y = i + direction[fishDirection][1];
                        if (canGo(X, Y)) {
                            map[Y][X][Moved].add(fishDirection);
                            isMove = true;
                            break;
                        } else {
                            fishDirection--;
                            if (fishDirection == 0) fishDirection = 8;
                        }
                    }
                    if (!isMove) {
                        map[i][j][Current].add(fishDirection);
                    }
                }
            }
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                while (!map[i][j][Moved].isEmpty()) {
                    map[i][j][Current].add(map[i][j][Moved].poll());
                }
            }
        }
    }

    static int sharkFirstMove;
    static int sharkSecondMove;
    static int sharkThirdMove;

    static int maxFishCount = -1;


    static void moveShark() {
        maxFishCount = -1;
        sharkFirstMove = 0;
        sharkSecondMove = 0;
        sharkThirdMove = 0;
        sharkMove(shark.x, shark.y, 0, new int[3]);

        shark.x += sharkMove[sharkFirstMove][0];
        shark.y += sharkMove[sharkFirstMove][1];
        if (!map[shark.y][shark.x][Current].isEmpty()) {
            map[shark.y][shark.x][Current].clear();
            fishSmokeMap[shark.y][shark.x] = 3;
        }
        shark.x += sharkMove[sharkSecondMove][0];
        shark.y += sharkMove[sharkSecondMove][1];
        if (!map[shark.y][shark.x][Current].isEmpty()) {
            map[shark.y][shark.x][Current].clear();
            fishSmokeMap[shark.y][shark.x] = 3;
        }
        shark.x += sharkMove[sharkThirdMove][0];
        shark.y += sharkMove[sharkThirdMove][1];
        if (!map[shark.y][shark.x][Current].isEmpty()) {
            map[shark.y][shark.x][Current].clear();
            fishSmokeMap[shark.y][shark.x] = 3;
        }
    }

    static void removeSmoke() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                fishSmokeMap[i][j] = Math.max(0, fishSmokeMap[i][j] - 1);
            }
        }
    }

    static void copyMagicComplete() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                while (!map[i][j][ToCopy].isEmpty()) {
                    map[i][j][Current].add(map[i][j][ToCopy].poll());
                }
            }
        }
    }

    static void sharkMove(int x, int y, int count, int[] moveRecord) {

        if (count == 3) {
            int fishCount = 0;
            int X = shark.x;
            int Y = shark.y;
            boolean[][] visited = new boolean[5][5];
            for (int i = 0; i < 3; i++) {
                X += sharkMove[moveRecord[i]][0];
                Y += sharkMove[moveRecord[i]][1];
                if (!visited[Y][X]) {
                    fishCount += map[Y][X][Current].size();
                }
                visited[Y][X] = true;
            }
            if (maxFishCount < fishCount) {
                sharkFirstMove = moveRecord[0];
                sharkSecondMove = moveRecord[1];
                sharkThirdMove = moveRecord[2];
                maxFishCount = fishCount;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int X = x + sharkMove[i][0];
            int Y = y + sharkMove[i][1];
            if (X >= 1 && Y >= 1 && X <= 4 && Y <= 4) {
                moveRecord[count] = i;
                sharkMove(X, Y, count + 1, moveRecord);
            }
        }
    }

    static void initMap() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                map[i][j][Current] = new LinkedList<>();
                map[i][j][ToCopy] = new LinkedList<>();
                map[i][j][Moved] = new LinkedList<>();
            }
        }
    }

    static boolean canGo(int x, int y) {
        return (x >= 1 && y >= 1 && x <= 4 && y <= 4) && !(shark.x == x && shark.y == y) && fishSmokeMap[y][x] == 0;
    }

}
