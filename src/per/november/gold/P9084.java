package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P9084 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int M = Integer.parseInt(br.readLine());
            sb.append(solution(coins, N, M)).append("\n");
        }
        System.out.println(sb);
    }

    static int solution(int[] coins, int N, int M) {
        int[][] dp = new int[N][M + 1];

        for (int i = 0; coins[0] * i <= M; i++) {
            dp[0][coins[0] * i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; j - k * coins[i] >= 0; k++) {
                    dp[i][j] += dp[i - 1][j - k * coins[i]];
                }
            }
        }
        return dp[N - 1][M];
    }
}
