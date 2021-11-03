package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P2670 {
    static double[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //나열된 양의 실수들의 개수
        arr = new double[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }
        System.out.printf("%.3f", solution(N));
    }

    static double solution(int N) {
        double[] dp = new double[N];
        dp[0] = arr[0];
        double max = dp[0];
        for (int i = 1; i < N; i++) {
            if (dp[i - 1] * arr[i] > arr[i]) {
                dp[i] = dp[i - 1] * arr[i];
            } else {
                dp[i] = arr[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
