package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P15990 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        int[] input = new int[T];
        for (int i = 0; i < T; i++) input[i] = Integer.parseInt(br.readLine());
        solution(Arrays.stream(input).max().getAsInt(), input);
    }

    static final int MOD = 1000000009;

    static void solution(int A, int[] input) {
        int[][] dp = new int[A + 4][4];
        dp[1][1] = 1;
        dp[1][0] = 1;
        dp[2][0] = 1;
        dp[2][2] = 1;
        dp[3][0] = 3;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= A; i++) {
            int a = (dp[i - 1][2] + dp[i - 1][3]) % MOD;
            int b = (dp[i - 2][1] + dp[i - 2][3]) % MOD;
            int c = (dp[i - 3][1] + dp[i - 3][2]) % MOD;
            dp[i][0] = (a + b) % MOD;
            dp[i][0] = (dp[i][0] + c) % MOD;
            dp[i][1] = a;
            dp[i][2] = b;
            dp[i][3] = c;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) sb.append(dp[input[i]][0]).append("\n");
        System.out.println(sb);
    }
}
