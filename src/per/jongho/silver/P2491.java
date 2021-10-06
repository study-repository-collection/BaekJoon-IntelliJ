package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2491 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //수열의 길이
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(N == 1 ? 1 : solution(N));
    }

    static int solution(int N) {
        int max = -1;
        int[][] dp = new int[N][2];

        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }

        for (int i = 1; i < N; i++) {
            if (arr[i] >= arr[i - 1]) {
                dp[i][0] = dp[i - 1][0] + 1;
                max = Math.max(dp[i][0], max);
            }
            if (arr[i] <= arr[i - 1]) {
                dp[i][1] = dp[i - 1][1] + 1;
                max = Math.max(dp[i][1], max);
            }
        }
        return max;
    }
}
