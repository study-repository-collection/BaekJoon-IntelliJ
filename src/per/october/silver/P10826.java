package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.lang.System.in;

public class P10826 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //n번쨰 피보나치 수를 구하라
        System.out.println(solution(N).toString());
    }

    static BigInteger solution(int N) {
        BigInteger[] dp = new BigInteger[N + 3];
        dp[0] = BigInteger.ZERO;
        dp[1] = BigInteger.ONE;
        dp[2] = BigInteger.ONE;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 2].add(dp[i - 1]);
        }
        return dp[N];
    }


}
