package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15486 {
    static Consultant[] calendar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        calendar = new Consultant[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer consultantInfo = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(consultantInfo.nextToken());
            int value = Integer.parseInt(consultantInfo.nextToken());
            calendar[i] = new Consultant(length, value);
        }
        System.out.println(solution(N));
    }

    static int solution(int N) {
        int[] dp = new int[N + 2];
        int currentMax = -1;
        for (int i = 1; i <= N; i++) {
            try {
                if (dp[i] > currentMax) {
                    currentMax = dp[i];
                }
                dp[i] = currentMax;
                dp[i + calendar[i].length] = Math.max(dp[i + calendar[i].length], dp[i] + calendar[i].value);
            } catch (IndexOutOfBoundsException ignored) {
            }

        }
        int answer = -1;
        for (int i = 1; i <= N + 1; i++) {
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }

    static class Consultant {
        final int length;
        final int value;

        public Consultant(int length, int value) {
            this.length = length;
            this.value = value;
        }
    }
}
