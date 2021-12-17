package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2624 {
    static Coin[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); //지폐의 금액
        int k = Integer.parseInt(br.readLine()); //동전의 가지 수
        coins = new Coin[k + 1];
        coins[0] = new Coin(0, 0);
        for (int i = 1; i <= k; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(input.nextToken());//동전의 금액
            int n = Integer.parseInt(input.nextToken());//동전의 개수
            coins[i] = new Coin(p, n);
        }
        Arrays.sort(coins, Comparator.comparingInt(o -> o.value));

        System.out.println(solution(T, k));
    }

    static int solution(int T, int k) {
        int[][] dp = new int[k + 1][T + 1];
        for (int i = 1; i <= k; i++) {
            Coin coin = coins[i];
            for (int l = 1; l * coin.value <= T && l <= coin.count; l++) {
                dp[i][l * coin.value] = 1;
            }
            for (int j = 1; j <= T; j++) {
                dp[i][j] += dp[i - 1][j];
                for (int l = 1; l * coin.value <= j && l <= coin.count; l++) {
                    if (j - l * coin.value >= 0) {
                        dp[i][j] += dp[i - 1][j - l * coin.value];
                    }
                }
            }
        }

        return dp[k][T];
    }

    static class Coin {
        int value;
        int count;

        public Coin(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
