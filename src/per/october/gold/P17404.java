package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17404 {
    static int[][] house;
    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        house = new int[N + 1][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer houseInfo = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(houseInfo.nextToken());
            int green = Integer.parseInt(houseInfo.nextToken());
            int blue = Integer.parseInt(houseInfo.nextToken());
            house[i + 1][RED] = red;
            house[i + 1][GREEN] = green;
            house[i + 1][BLUE] = blue;
        }
        System.out.println(solution(N));
    }

    static final int INF = 987654321;

    static int solution(int N) {
        int[][] dp = new int[N + 1][3];

        dp[1][RED] = house[1][RED];
        dp[1][GREEN] = INF;
        dp[1][BLUE] = INF;
        int min;

        for (int i = 2; i <= N; i++) {
            dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + house[i][RED];
            dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + house[i][GREEN];
            dp[i][BLUE] = Math.min(dp[i - 1][RED], dp[i - 1][GREEN]) + house[i][BLUE];
        }
        min = Math.min(dp[N][GREEN], dp[N][BLUE]);

        dp[1][RED] = INF;
        dp[1][GREEN] = house[1][GREEN];

        for (int i = 2; i <= N; i++) {
            dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + house[i][RED];
            dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + house[i][GREEN];
            dp[i][BLUE] = Math.min(dp[i - 1][RED], dp[i - 1][GREEN]) + house[i][BLUE];
        }
        int tempMin = Math.min(dp[N][RED], dp[N][BLUE]);
        min = Math.min(min, tempMin);

        dp[1][RED] = INF;
        dp[1][GREEN] = INF;
        dp[1][BLUE] = house[1][BLUE];
        for (int i = 2; i <= N; i++) {
            dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + house[i][RED];
            dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + house[i][GREEN];
            dp[i][BLUE] = Math.min(dp[i - 1][RED], dp[i - 1][GREEN]) + house[i][BLUE];
        }
        tempMin = Math.min(dp[N][RED], dp[N][GREEN]);
        return Math.min(min, tempMin);
    }
}
