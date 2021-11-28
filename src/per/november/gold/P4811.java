package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P4811 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            sb.append(solution(N)).append("\n");
        }
        System.out.print(sb);
    }

    static long solution(int N) {
        long[][][] dp = new long[2 * N + 1][N + 1][N + 1];
        dp[0][N][0] = 1;
        for (int i = 1; i <= 2 * N; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= N; k++) {
                    if (j < N && k > 0)
                        dp[i][j][k] += dp[i - 1][j + 1][k - 1];
                    if (k < N)
                        dp[i][j][k] += dp[i - 1][j][k + 1];
                }
            }
        }
        return dp[2 * N][0][0];
    }
}
