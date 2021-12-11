package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1082 {
    static int[] arr;
    static String[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer input = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(br.readLine()); //준비한 금액
        dp = new String[N][M + 1];

        initDp(N, M);
        if (N != 1) {
            System.out.println(solution(N, M));
        } else {
            System.out.println(0);
        }

    }

    static String max = "";

    static String solution(int N, int M) {
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    String a = dp[i][j - arr[i]] + i;
                    if (dp[i][j - arr[i]].length() == 1 && dp[i][j - arr[i]].charAt(0) == '0') {
                        a = "0";
                    }
                    String b = dp[i + 1][j - arr[i]] + i;
                    if (whoIsBigger(a, b)) {
                        if (whoIsBigger(a, dp[i][j])) {
                            dp[i][j] = a;
                        }
                    } else {
                        if (whoIsBigger(b, dp[i][j]))
                            dp[i][j] = b;
                    }
                    if (!whoIsBigger(max, dp[i][j])) {
                        max = dp[i][j];
                    }
                }
            }
        }
        return max;
    }

    static void initDp(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = "";
            }
        }
        for (int i = 1; arr[N - 1] * i <= M; i++) {
            for (int j = 1; j <= i; j++) {
                dp[N - 1][arr[N - 1] * i] += (N - 1);
                max = dp[N - 1][arr[N - 1] * i];
            }
        }
    }

    //true -> a is Bigger
    //false -> b is Bigger
    static boolean whoIsBigger(String a, String b) {
        if (a.length() > b.length()) {
            return true;
        } else if (a.length() == b.length()) {
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) > b.charAt(i)) {
                    return true;
                } else if (a.charAt(i) < b.charAt(i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
