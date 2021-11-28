package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P5582 {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        String input2 = br.readLine();
        map = new char[input.length()][input2.length()];
        System.out.println(solution(input.length(), input2.length(), input, input2));
    }

    static int solution(int length1, int length2, String input1, String input2) {
        int[][] dp = new int[length1][length2];
        int max = 0;
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if (input1.charAt(i) == input2.charAt(j)) {
                    if (i > 0 && j > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        max = Math.max(dp[i][j], max);
                    } else {
                        dp[i][j] = 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
        }
        return max;
    }
}
