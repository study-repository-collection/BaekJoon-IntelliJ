package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2228 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            arr[i] = a;
        }
        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        int[][] dp = new int[N][M + 1];

        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -123456789);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                int sum = 0;
                for (int k = i; k >= 0; k--) {
                    sum += arr[k];
                    dp[i][1] = Math.max(dp[i][1], sum);
                    for (int l = k - 2; l >= 0; l--) {
                        dp[i][j] = Math.max(dp[i][j], sum + dp[l][j - 1]);
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            ret = Math.max(dp[i][M], ret);
        }
        return ret;
    }
}
