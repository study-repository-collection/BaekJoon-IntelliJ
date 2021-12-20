package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P1563 {
    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        System.out.println(solution(N));
    }

    static int solution(int N) {
        //2차 : 결석연속일수!
        //3차 : 지각 총 일수!
        int[][][] dp = new int[N + 1][3][2];
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][2][0] + dp[i - 1][1][0]) % MOD;
            dp[i][0][1] = (dp[i - 1][0][1] + dp[i - 1][1][1] + dp[i - 1][2][1] + dp[i - 1][0][0] + dp[i - 1][1][0] + dp[i - 1][2][0]) % MOD;
            dp[i][1][1] = dp[i - 1][0][1];
            dp[i][2][1] = dp[i - 1][1][1];
            dp[i][1][0] = dp[i - 1][0][0];
            dp[i][2][0] = dp[i - 1][1][0];
        }
        return (dp[N][0][0] + dp[N][0][1] + dp[N][1][1] + dp[N][2][1] + dp[N][1][0] + dp[N][2][0]) % MOD;
    }
}
