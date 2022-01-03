package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2662 {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution(N, M);
    }

    static void solution(int N, int M) {
        int[][] dp = new int[M + 1][N + 1];
        int[][] route = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= j; k++) {
                    if (dp[i][j] < dp[i - 1][j - k] + arr[k][i]) {
                        dp[i][j] = dp[i - 1][j - k] + arr[k][i];
                        route[i][j] = k;
                    }
                }
            }
        }
        System.out.println(dp[M][N]);
        Stack<Integer> stack = new Stack<>();
        int money = N;
        for (int i = M; i >= 1; i--) {
            stack.add(route[i][money]);
            money -= route[i][money];
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
