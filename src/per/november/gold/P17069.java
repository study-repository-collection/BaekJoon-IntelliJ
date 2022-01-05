package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17069 {
    static int[][] map;

    static final int WALL = 1;

    static final int[][][] dxDy = {{{1, 0}}, {{1, 0}, {0, 1}, {1, 1}}, {{0, 1}}};

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
        System.out.println(solution(N));
    }

    static long solution(int N) {
        long[][][] dp = new long[N][N][3];
        dfs(1, 0, 0, N, dp);
        return dp[0][1][0];
    }

    static boolean canVisit(int x, int y, int N) {
        return x < N && y < N;
    }

    static long dfs(int x, int y, int direction, int N, long[][][] dp) {
        if (x == N - 1 && y == N - 1) {
            dp[y][x][direction] = 1;
            return 1;
        }
        if (dp[y][x][direction] != 0) {
            return dp[y][x][direction];
        }
        long sum = 0;

        int X = 0;
        int Y = 0;
        boolean go = true;
        for (int[] move : dxDy[direction]) {
            X = x + move[0];
            Y = y + move[1];
            if (!canVisit(X, Y, N) || map[Y][X] == WALL) {
                go = false;
                break;
            }
        }
        if (go) {
            sum += dfs(X, Y, direction, N, dp);
        }
        int nextDirection = direction - 1;
        if (nextDirection >= 0) {
            go = true;
            for (int[] move : dxDy[nextDirection]) {
                X = x + move[0];
                Y = y + move[1];
                if (!canVisit(X, Y, N) || map[Y][X] == WALL) {
                    go = false;
                    break;
                }
            }
            if (go) {
                sum += dfs(X, Y, nextDirection, N, dp);
            }
        }

        nextDirection = direction + 1;
        if (nextDirection <= 2) {
            go = true;
            for (int[] move : dxDy[nextDirection]) {
                X = x + move[0];
                Y = y + move[1];
                if (!canVisit(X, Y, N) || map[Y][X] == WALL) {
                    go = false;
                    break;
                }
            }
            if (go) {
                sum += dfs(X, Y, nextDirection, N, dp);
            }
        }
        return dp[y][x][direction] = sum;
    }
}
