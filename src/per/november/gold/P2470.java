package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P2470 {
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(sequence);
        solution(N, sequence);

    }

    static void solution(int N, int[] sequence) {
        int start = 0;
        int end = N - 1;
        int solutionA = 0;
        int solutionB = 0;
        int minDiff = Integer.MAX_VALUE;

        while (start < end) {
            int diff = sequence[start] + sequence[end];
            if (minDiff > Math.abs(diff)) {
                solutionA = sequence[start];
                solutionB = sequence[end];
                minDiff = Math.abs(diff);
            }
            if (diff > 0) {
                end--;
            } else {
                start++;
            }
        }
        System.out.println(solutionA + " " + solutionB);
    }
}
