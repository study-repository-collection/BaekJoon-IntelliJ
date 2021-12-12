package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2616 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //소형 기관차가 끌고가던 객차의 수 50000
        StringTokenizer input = new StringTokenizer(br.readLine());
        arr = new int[N + 1]; //한 객차에 타고 있는 손님의 수  100 이하임
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(input.nextToken());
        }
        int K = Integer.parseInt(br.readLine());//최대로 끌 수 있는 객차의 수
        System.out.println(solution(N, K));
    }


    static int solution(int N, int K) {
        //dp[i][j] i번째 기차가 j번째 객차까지 봤을 때, 태울 수 있는 승객의 최대의 수
        int[][] dp = new int[4][N + 1];
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= N; j++) {
                if (j - K + 1 >= 1) {
                    dp[i][j] = Math.max(dp[i-1][j-K]+(arr[j]-arr[j-K]),dp[i][j-1]);
                }
            }
        }

        return dp[3][N];
    }
}
