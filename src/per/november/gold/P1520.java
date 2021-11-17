package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1520 {
    static int[][] map;
    static int[][] dp;
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        dfs(map, 0, 0, N, M);
        System.out.println(dp[0][0]);
    }

    static int dfs(int[][] map, int x, int y, int N, int M) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        if (dp[y][x] == -1) {
            dp[y][x] = 0;
            for (int[] move : dxDy) {
                int X = x + move[0];
                int Y = y + move[1];
                if (canVisit(X, Y, N, M) && map[Y][X] < map[y][x]) {
                    dp[y][x] += dfs(map, X, Y, N, M);
                }
            }
        }
        return dp[y][x];
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }
}
