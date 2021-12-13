package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11062 {
    static int[] arr;
    static int[][] dp;
    static final int GUN = 0;
    static final int MYUNG = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine()); //카드의 개수
            arr = new int[N + 1];
            dp = new int[N + 1][N + 1];
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(input.nextToken());
            }
            System.out.println(solution(1, N, GUN));
        }
    }

    static int solution(int left, int right, int turn) {
        if (left > right) return 0;
        if (dp[left][right] != 0) return dp[left][right];
        int nextTurn = turn == GUN ? MYUNG : GUN;
        if (turn == GUN) {
            dp[left][right] = Math.max(arr[left] + solution(left + 1, right, nextTurn), arr[right] + solution(left, right - 1, nextTurn));
        } else {
            dp[left][right] = Math.min(+solution(left + 1, right, nextTurn), solution(left, right - 1, nextTurn));
        }
        return dp[left][right];
    }
}
