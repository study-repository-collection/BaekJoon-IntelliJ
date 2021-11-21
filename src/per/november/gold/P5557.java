package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.LinkedTransferQueue;

import static java.lang.System.in;

public class P5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N, sequence));
    }

    static long solution(int N, int[] sequence) {
        long[][] dp = new long[N][21];
        dp[0][sequence[0]] = 1;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (j - sequence[i] >= 0) {
                    dp[i][j] += dp[i - 1][j - sequence[i]];
                }
                if (j + sequence[i] <= 20) {
                    dp[i][j] += dp[i - 1][j + sequence[i]];
                }
            }
        }
        return dp[N - 2][sequence[N - 1]];
    }
}
