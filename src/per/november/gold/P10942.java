package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P10942 {
    static int[] sequence;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //수열의 크기
        sequence = new int[N + 1];
        StringTokenizer sequenceInfo = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) sequence[i] = Integer.parseInt(sequenceInfo.nextToken());

        int M = Integer.parseInt(br.readLine()); //질문의 개수
        solution(N);
        while (M-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(input.nextToken()); //범위 시작
            int finish = Integer.parseInt(input.nextToken());//범위 끝
            sb.append(dp[start][finish]).append("\n");
        }
        System.out.print(sb);
    }

    static void solution(int N) {
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) dp[i][i] = 1;

        for (int i = 1; i < N; i++) {
            if (sequence[i] == sequence[i + 1]) dp[i][i + 1] = 1;
        }

        for (int i = 3; i <= N; i++) {
            for (int j = 1; j <= N - i + 1; j++) {
                if (sequence[j] == sequence[j + i - 1] && dp[j + 1][j + i - 2] == 1) {
                    dp[j][j + i - 1] = 1;
                }
            }
        }
    }
}
