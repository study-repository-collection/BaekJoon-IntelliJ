package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P2482 {

    static final int MOD = 1000000003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        if (N / K < 2) {
            System.out.println(0);
            return;
        } else {
            System.out.println(solution(N, K, new int[N + 1][N + 1]));
        }
    }

    static int solution(int N, int K, int[][] dp) {
        for (int i = 0; i <= N; i++) dp[i][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; 2 * j <= i + 1; j++) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        return (dp[N - 3][K - 1] + dp[N - 1][K]) % MOD;
    }
}
