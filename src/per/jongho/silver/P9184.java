package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P9184 {

    static int[][][] dp = new int[51][51][51];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1) break;
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(solution(a, b, c)).append("\n");
        }
        System.out.println(sb);
    }

    static int solution(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }
        if (dp[a][b][c] != 0) {
            return dp[a][b][c];
        }
        if (a > 20 || b > 20 || c > 20) {
            return ((dp[a][b][c] = solution(20, 20, 20)));
        } else if (a < b && b < c) {
            return ((dp[a][b][c] = solution(a, b, c - 1) + solution(a, b - 1, c - 1) - solution(a, b - 1, c)));
        } else {
            return ((dp[a][b][c] = solution(a - 1, b, c) + solution(a - 1, b - 1, c) + solution(a - 1, b, c - 1) - solution(a - 1, b - 1, c - 1)));
        }
    }
}
