package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P2473 {
    static long[] sequence;
    static long[] answer = new long[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(sequence);
        solution(N);
        Arrays.sort(answer);

        for (long value : answer) sb.append(value).append(" ");
        System.out.println(sb);
    }

    static void solution(int N) {
        long min = Long.MAX_VALUE;

        for (int i = 0; i < N - 2; i++) {
            min = Math.min(min, findMinimum(i, N, min));
        }
    }

    static long findMinimum(int index, int N, long min) {
        int start = index + 1;
        int second = N - 1;

        while (start < second) {
            long sum = sequence[index] + sequence[start] + sequence[second];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                answer[0] = sequence[index];
                answer[1] = sequence[start];
                answer[2] = sequence[second];
            }

            if (sum > 0) {
                second--;
            } else if (sum < 0) {
                start++;
            } else {
                return min;
            }
        }
        return min;
    }
}
