package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1103 {
    static int[][] map;
    static final int HOLE = 'H' - '0';
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String mapInfo = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = mapInfo.charAt(j) - '0';
            }
        }
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        boolean[][] visited = new boolean[N][M];
        boolean[][] visitStack = new boolean[N][M];
        visited[0][0] = true;
        solution(0, 0, 0, visited, N, M, dp, visitStack);
        if (!isLoop) {
            System.out.println(max);
        } else {
            System.out.println(-1);
        }
    }

    static int max = -1;
    static boolean isLoop = false;

    static void solution(int count, int x, int y, boolean[][] visited, int N, int M, int[][] dp, boolean[][] visitStack) {
        visited[y][x] = true;
        visitStack[y][x] = true;
        dp[y][x] = count;
        for (int[] move : dxDy) {
            int X = x + move[0] * map[y][x];
            int Y = y + move[1] * map[y][x];
            if (!canVisit(X, Y, N, M) || map[Y][X] == HOLE) {
                max = Math.max(max, count + 1);
                continue;
            }
            if (visitStack[Y][X]) {
                isLoop = true;
                return;
            }
            if (dp[Y][X] <= count) {
                solution(count + 1, X, Y, visited, N, M, dp, visitStack);
            }
        }

        visitStack[y][x] = false;
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }
}
