package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P1660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        System.out.println(solution(N));
    }


    static int solution(int N) {
        int[] dp = new int[122];
        int[] realDp = new int[N + 1];
        Arrays.fill(realDp, 300000);
        dp[1] = 1;
        for (int i = 2; i < 122; i++) {
            dp[i] = dp[i - 1] + (dp[i - 1] - dp[i - 2] + i);
        }

        for (int i = 1; i <= 122; i++) {
            try {
                realDp[dp[i]] = 1;
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        for (int i = 1; i < 122; i++) {
            for (int j = dp[i]; j <= N; j++) {
                realDp[j] = Math.min(realDp[j], realDp[j - dp[i]] + 1);
            }
        }
        return realDp[N];
    }
}
