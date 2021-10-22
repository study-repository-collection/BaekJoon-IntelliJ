package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String input1 = br.readLine();
        String input2 = br.readLine();
        solution(input1, input2);
    }


    static void solution(String input1, String input2) {
        int length = input1.length();
        int length2 = input2.length();
        int[][] dp = new int[length + 1][length2 + 1];
        String[][] answer = new String[length + 1][length2 + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length2; j++) {
                answer[i][j] = "";
            }
        }
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length2; j++) {
                if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    answer[i][j] = answer[i - 1][j - 1] + input1.charAt(i - 1);
                } else {
                    if (dp[i][j - 1] > dp[i - 1][j]) {
                        dp[i][j] = dp[i][j - 1];
                        answer[i][j] = answer[i][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                        answer[i][j] = answer[i - 1][j];
                    }
                }
            }
        }
        System.out.println(dp[length][length2]);
        if (dp[length][length2] != 0) {
            System.out.println(answer[length][length2]);
        }
    }

}
