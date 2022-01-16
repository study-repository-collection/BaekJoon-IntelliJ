package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2613 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //구슬의 개수
        int M = Integer.parseInt(input.nextToken()); //그룹의 수

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        solution(N, M);
    }


    static void solution(int N, int M) {
        int[][] dp = new int[N][M + 1]; //N개의 수를 M개의 그룹으로 나누었을 때, 그룹의 합의 최댓값의 최솟값
        int[][] count = new int[N][M + 1]; //N개의 수를 M개의 그룹으로 나누었을 때 제일 우측 그룹의 원소의 수
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], 987653321);
        for (int i = 0, sum = 0; i < N; i++) {
            sum += arr[i];
            dp[i][1] = sum;
            count[i][1] = i + 1; //N개의 수를 1개의 그룹으로 나누면 최댓값은 N개의 수의 합과 같다.
        }
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                int sum = 0;
                for (int k = i; k >= 1; k--) {
                    sum += arr[k]; //제일 우측 집합을 하나씩 추가한다.
                    int thisNumber = Math.max(dp[k - 1][j - 1], sum); //제일 우측 집합과 그 집합을 빼고 만들 수 있는 j-1개 그룹의 최댓값을 비교
                    if (dp[i][j] > thisNumber) {
                        count[i][j] = i - k + 1;
                        dp[i][j] = thisNumber;
                    }
                }
            }
        }
        System.out.println(dp[N - 1][M]);

        //제일 우측 집합의 개수부터 역추적해가며 구함
        Stack<Integer> stack = new Stack<>();
        for (int i = M, temp = N - 1; i >= 1; i--) {
            stack.add(count[temp][i]);
            temp -= count[temp][i];
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.print(sb);
    }
}
