package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17070 {

    static final int TO_RIGHT = 0;
    static final int TO_45 = 1;
    static final int TO_BOTTOM = 2;

    static final int WALL = 1;
    static final int EMPTY = 0;
    static int[][] map;
    static int[] dxTo45 = {0, 1, 1};
    static int[] dyTo45 = {1, 1, 0};

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
        dfs(1, 0, TO_RIGHT, N);
        System.out.println(count);
    }

    static int count = 0;

    static void dfs(int x, int y, int currentDirection, int N) {
        if (x == N - 1 && y == N - 1) {
            count++;
            return;
        }
        switch (currentDirection) {
            case TO_RIGHT:
                if (canGo(x, y, TO_RIGHT, N)) {
                    dfs(x + 1, y, TO_RIGHT, N);
                }
                if (canGo(x, y, TO_45, N)) {
                    dfs(x + 1, y + 1, TO_45, N);
                }
                break;
            case TO_45:
                if (canGo(x, y, TO_RIGHT, N)) {
                    dfs(x + 1, y, TO_RIGHT, N);
                }
                if (canGo(x, y, TO_45, N)) {
                    dfs(x + 1, y + 1, TO_45, N);
                }
                if (canGo(x, y, TO_BOTTOM, N)) {
                    dfs(x, y + 1, TO_BOTTOM, N);
                }
                break;
            case TO_BOTTOM:
                if (canGo(x, y, TO_BOTTOM, N)) {
                    dfs(x, y + 1, TO_BOTTOM, N);
                }
                if (canGo(x, y, TO_45, N)) {
                    dfs(x + 1, y + 1, TO_45, N);
                }
                break;
        }
    }

    static boolean canGo(int x, int y, int direction, int N) {
        int X;
        int Y;
        switch (direction) {
            case TO_RIGHT:
                X = x + 1;
                return (X < N && map[y][X] == EMPTY);
            case TO_45:
                for (int i = 0; i < 3; i++) {
                    X = x + dxTo45[i];
                    Y = y + dyTo45[i];
                    if (X >= N || Y >= N || map[Y][X] != EMPTY) {
                        return false;
                    }
                }
                return true;
            case TO_BOTTOM:
                Y = y + 1;
                return (Y < N && map[Y][x] == EMPTY);
        }
        return false;
    }
}
