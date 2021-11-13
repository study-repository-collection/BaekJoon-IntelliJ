package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P18427 {
    static ArrayList<ArrayList<Integer>> map;
    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            map.add(new ArrayList<>());
            for (int j = 1; input.hasMoreTokens(); j++) {
                map.get(i - 1).add(Integer.parseInt(input.nextToken()));
            }
        }
        System.out.println(solution(N, M, H));
    }

    static int solution(int N, int M, int H) {
        int[][] dp = new int[N + 1][H + 1];
        dp[1][0] = 1;
        for (int value : map.get(0)) {
            dp[1][value] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= H; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int value : map.get(i - 1)) {
                    if (j - value >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - value]) % MOD;
                    }
                }
            }
        }
        return dp[N][H];
    }
}
