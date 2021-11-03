package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15651 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        solution(0, N, M);
        System.out.println(answer);
    }

    static StringBuilder sb = new StringBuilder();
    static StringBuilder answer = new StringBuilder();

    static void solution(int index, int N, int M) {
        for (int i = 1; i <= N; i++) {
            sb.append(i).append(" ");
            if (index < M - 1) {
                solution(index + 1, N, M);
            } else {
                answer.append(sb).append("\n");
            }
            sb.delete(sb.length() - 2, sb.length());

        }
    }
}
