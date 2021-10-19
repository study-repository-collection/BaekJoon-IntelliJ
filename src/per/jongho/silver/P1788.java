package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P1788 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int result = solution(N);
        sb.append(Integer.compare(result, 0)).append("\n").append(Math.abs(result));
        System.out.println(sb);
    }

    static int solution(int N) {
        int[] dp = new int[20000001];
        dp[1000000] = 0;
        dp[1000001] = 1;
        N += 1000000;
        if (N > 1000000) {
            for (int i = 1000002; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000000;
            }
            return dp[N];

        } else {
            for (int i = 1000001; i >= N + 2; i--) {
                dp[i - 2] = (dp[i] - dp[i - 1]) % 1000000000;
            }
            return dp[N];
        }
    }
}
