package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P12851 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //수빈이가 있는 위치
        int K = Integer.parseInt(st.nextToken()); //동생이 있는 위치
        bfs(N, K);
    }

    static void bfs(int N, int K) {
        int[] dp = new int[100002];
        Arrays.fill(dp, 987654321);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        int loopCount = 0;
        int answerCount = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                dp[temp] = Math.min(loopCount, dp[temp]);
                if (temp == K) {
                    answerCount++;
                    while (!queue.isEmpty() && i < size) {
                        if (queue.poll() == K) {
                            answerCount++;
                        }
                        i++;
                    }
                    System.out.println(loopCount);
                    System.out.println(answerCount);
                    return;
                }
                if (temp > 0 && dp[temp - 1] > loopCount) {
                    queue.add(temp - 1);
                }
                if (temp < 100000 && dp[temp + 1] > loopCount) {
                    queue.add(temp + 1);
                }
                if (temp * 2 <= 100000 && dp[temp * 2] > loopCount) {
                    queue.add(temp * 2);
                }
            }
            loopCount++;
        }
    }

}
