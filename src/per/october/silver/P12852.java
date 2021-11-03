package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.in;

public class P12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        solution(N);
    }

    static final int INF = 987654321;

    static void solution(int N) {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, INF);
        int[] index = new int[N + 1];

        dp[N] = 0;


        for (int i = N - 1; i >= 1; i--) {
            if (dp[i] > dp[i + 1] + 1) {
                dp[i] = dp[i + 1] + 1;
                index[i] = i + 1;
            }
            //짝수면
            if ((i << 1) <= N) {
                if (dp[i] > dp[i << 1] + 1) {
                    dp[i] = dp[i << 1] + 1;
                    index[i] = i << 1;
                }
            }
            if (i * 3 <= N) {
                if (dp[i] > dp[i * 3] + 1) {
                    dp[i] = dp[i * 3] + 1;
                    index[i] = i * 3;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dp[1]).append("\n");
        ArrayList<Integer> list = new ArrayList<>();
        int nextIndex = 1;
        do {
            list.add(nextIndex);
            nextIndex = index[nextIndex];
        } while (nextIndex != 0);

        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i)).append(" ");
        }
        System.out.print(sb);
    }
}
