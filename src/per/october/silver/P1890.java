package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1890 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //판의 크기
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(map));
    }

    static long solution(int[][] map) {
        long[][] dp = new long[map.length][map.length];
        dp[0][0] = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (dp[i][j] == 0) continue;
                if (i == map.length - 1 && j == map.length - 1) continue;
                int right = j + map[i][j];
                int down = i + map[i][j];
                if (right < map.length) {
                    dp[i][right] += dp[i][j];
                }
                if (down < map.length) {
                    dp[down][j] += dp[i][j];
                }
            }
        }
        return dp[map.length - 1][map.length - 1];
    }

}
