package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P10974 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int N = Integer.parseInt(br.readLine());
        solution(1, N, new int[N + 1], new boolean[N + 1]);
        System.out.print(sb);
    }

    static StringBuilder sb = new StringBuilder();

    static void solution(int cnt, int N, int[] arr, boolean[] visited) {
        if (cnt == N + 1) {
            for (int i = 1; i <= N; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[cnt] = i;
                solution(cnt + 1, N, arr, visited);
                visited[i] = false;
            }
        }
    }
}
