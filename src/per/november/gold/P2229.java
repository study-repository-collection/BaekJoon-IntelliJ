package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P2229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(sequence, N));
    }

    static int solution(int[] sequence, int N) {
        int[] dp = new int[N];
        dp[0] = 0;

        for (int i = 1; i < N; i++) {
            int max = sequence[i];
            int min = sequence[i];
            int Score = dp[i - 1];
            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(max, sequence[j]);
                min = Math.min(min, sequence[j]);
                if (j >= 1)
                    Score = Math.max(Score, (max - min) + dp[j - 1]);
                else {
                    Score = Math.max(Score, (max - min));
                }
            }
            dp[i] = Score;
        }

        return dp[N - 1];
    }
}
