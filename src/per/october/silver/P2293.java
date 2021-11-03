package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //동전의 개수
        int k = Integer.parseInt(st.nextToken()); //동전의 합

        int[] coinValues = new int[n];
        for (int i = 0; i < n; i++) coinValues[i] = Integer.parseInt(br.readLine());
        System.out.println(solution(coinValues, k));
    }

    static int solution(int[] coinValues, int k) {
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0; i < coinValues.length; i++) {
            for (int j = coinValues[i]; j <= k; j++) {
                try {
                    dp[j] += dp[j - coinValues[i]];
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        return dp[k];
    }
}
