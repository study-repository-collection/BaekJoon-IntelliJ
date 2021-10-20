package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1806 {
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int S = Integer.parseInt(input.nextToken());

        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N, S));
    }

    static int solution(int N, int S) {
        int first = 0;
        int second = 0;
        int currentSum = sequence[0];
        int min = Integer.MAX_VALUE;

        do {
            while (currentSum < S && second < N - 1) {
                second++;
                currentSum += sequence[second];
            }
            while (currentSum >= S && first <= second) {
                min = Math.min(min, second - first + 1);
                first++;
                currentSum -= sequence[first - 1];
            }
        } while (second != N - 1);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
