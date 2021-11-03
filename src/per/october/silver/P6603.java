package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P6603 {
    static int[] arrs = new int[13];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        while (true) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(input.nextToken());
            if (A == 0) break;
            for (int i = 1; i <= A; i++) {
                arrs[i] = Integer.parseInt(input.nextToken());
            }
            solution(new int[A + 1], 1, A, 1);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static StringBuilder sb = new StringBuilder();

    static void solution(int[] arr, int cnt, int N, int start) {
        if (cnt == 7) {
            for (int i = 1; i <= 6; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            arr[cnt] = arrs[i];
            solution(arr, cnt + 1, N, i + 1);
        }
    }
}
