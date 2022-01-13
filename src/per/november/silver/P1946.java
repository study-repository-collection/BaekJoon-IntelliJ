package per.november.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1946 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[] sequence = new int[100001];
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            for (int i = 1; i <= N; i++) {
                StringTokenizer info = new StringTokenizer(br.readLine());
                sequence[Integer.parseInt(info.nextToken())] = Integer.parseInt(info.nextToken());
            }
            sb.append(solution(N, sequence)).append("\n");
        }
        System.out.println(sb);
    }

    static int solution(int N, int[] sequence) {
        int ret = 1;
        int current = sequence[1];

        for (int i = 2; i <= N; i++) {
            if (sequence[i] < current) {
                ret++;
                current = sequence[i];
            }
        }
        return ret;
    }
}
