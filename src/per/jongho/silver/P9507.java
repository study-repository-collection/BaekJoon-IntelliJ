package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.in;

public class P9507 {
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        ArrayList<Integer> inputs = new ArrayList<>();
        while (T-- > 0) {
            inputs.add(Integer.parseInt(br.readLine()));
        }
        int max = inputs.stream().max(Integer::compareTo).get();
        dp = new long[max + 1];
        solution(max);
        for (int input : inputs) sb.append(dp[input]).append("\n");
        System.out.println(sb);
    }

    static void solution(int N) {
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i - 4] + dp[i - 3] + dp[i - 2] + dp[i - 1];
        }
    }
}
