package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11060 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //미로의 크기
        arr = new int[N];
        StringTokenizer input = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input.nextToken());
        }
        System.out.println(solution(N));
    }


    static int solution(int N) {
        int[] dp = new int[N];
        final int MAX = Integer.MAX_VALUE-1000;
        for (int i = 0; i < N; i++) dp[i] = MAX;
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int A = arr[i];
            for (int j = 1; j <= A; j++) {
                try {
                    dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
                } catch (IndexOutOfBoundsException ignored) {

                }
            }
        }
        return dp[N - 1] == MAX ? -1 : dp[N - 1];
    }
}
