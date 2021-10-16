package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P10819 {
    static int[] arr = new int[8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer input = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input.nextToken());
        solution(1, new int[N + 1], new boolean[N + 1], N);

        System.out.println(MAX);
    }

    static void solution(int cnt, int[] answer, boolean[] visited, int N) {
        if (cnt == N + 1) {
            getMax(answer, N);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[cnt] = arr[i];
                solution(cnt + 1, answer, visited, N);
                visited[i] = false;
            }
        }
    }

    static int MAX = -1;

    static void getMax(int[] answer, int N) {
        int sum = 0;
        for (int i = 1; i <= N - 1; i++) {
            sum += Math.abs(answer[i] - answer[i + 1]);
        }
        MAX = Math.max(MAX, sum);
    }
}
