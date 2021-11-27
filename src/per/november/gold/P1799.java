package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1799 {
    static int[][] dxDy = {{-1, -1}, {1, -1}, {1, 1}, {-1, 1}};
    static int[][] map;
    static final int CAN = 1;
    static final int CANT = 0;
    static final int BISHOP = 2;
    static final int TEMP_CANT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        int max = solution(0, 0, 0, N, 0);
        int maxSecond = solution(0, 1, 0, N, 1);
        System.out.println(max + maxSecond);
    }


    static int solution(int count, int x, int y, int N, int mode) {
        int max = -1;
        if (y == N) {
            return count;
        }
        //놓을 수 있으면
        if (map[y][x] == CAN) {
            //비숍을 놓아봄
            putBishop(N, x, y);
            int X = x + 2;
            int Y = y;
            if (X >= N) {
                Y++;
                if (mode == 0) {
                    if ((Y & 1) == 1)
                        X = 1;
                    else {
                        X = 0;
                    }
                } else {
                    if ((Y & 1) == 1) {
                        X = 0;
                    } else {
                        X = 1;
                    }
                }
            }
            //놓은 상태로 다음 좌표로 이동
            max = Math.max(solution(count + 1, X, Y, N, mode), max);
            //비숍을 다시 회수함
            backBishop(N, x, y);
            //회수한 상태로 다음 좌표로 이동
            max = Math.max(solution(count, X, Y, N, mode), max);
        }
        //현재 위치에 놓을 수 없으면
        else {
            int X = x + 2;
            int Y = y;
            if (X >= N) {
                Y++;

                if (mode == 0) {
                    if ((Y & 1) == 1)
                        X = 1;
                    else {
                        X = 0;
                    }
                } else {
                    if ((Y & 1) == 1) {
                        X = 0;
                    } else {
                        X = 1;
                    }
                }
            }
            max = Math.max(solution(count, X, Y, N, mode), max);
        }
        return max;
    }

    static void putBishop(int N, int x, int y) {
        map[y][x] = BISHOP;
        for (int[] move : dxDy) {
            int X = x + move[0];
            int Y = y + move[1];
            while (canGo(X, Y, N)) {
                if (map[Y][X] == CAN) {
                    map[Y][X] = TEMP_CANT;
                } else if (map[Y][X] >= TEMP_CANT) {
                    map[Y][X]++;
                }
                X += move[0];
                Y += move[1];
            }
        }
    }

    static void backBishop(int N, int x, int y) {
        map[y][x] = CAN;
        for (int[] move : dxDy) {
            int X = x + move[0];
            int Y = y + move[1];
            while (canGo(X, Y, N)) {
                if (map[Y][X] == TEMP_CANT) {
                    map[Y][X] = CAN;
                } else if (map[Y][X] > TEMP_CANT) {
                    map[Y][X]--;
                }
                X += move[0];
                Y += move[1];
            }
        }
    }

    static boolean canGo(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
