package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1937 {
    static int[][] map;
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(N));
    }

    static int solution(int N) {
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0) {
                    getCount(j, i, N, dp);
                }
            }
        }
        return getMax(N, dp);
    }

    static int getCount(int x, int y, int N, int[][] dp) {
        int max = 1;
        for (int[] move : dxDy) {
            int X = x + move[0];
            int Y = y + move[1];
            if (canVisit(X, Y, N) && map[Y][X] < map[y][x]) {
                if (dp[Y][X] != 0) {
                    max = Math.max(max, dp[Y][X] + 1);
                } else {
                    max = Math.max(max, getCount(X, Y, N, dp) + 1);
                }
            }
        }
        dp[y][x] = max;
        return dp[y][x];
    }

    static int getMax(int N, int[][] dp) {
        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    static boolean canVisit(int x, int y, int N) {
        return (x >= 0 && y >= 0 && x < N && y < N);
    }
}
