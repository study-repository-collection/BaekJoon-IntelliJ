package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P2473 {
    static long[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(sequence);
        solution(N);
    }

    static void solution(int N) {
        long min = Long.MAX_VALUE;
        int first = 0;
        int mid = N / 2;
        int second = N - 1;

        long answerA = 0;
        long answerB = 0;
        long answerC = 0;

        while (true) {
            long sum = sequence[first] + sequence[mid] + sequence[second];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                answerA = sequence[first];
                answerB = sequence[mid];
                answerC = sequence[second];
            }
            if (sum == 0) {
                System.out.println(answerA + " " + answerB + " " + answerC);
                return;
            } else if (sum > 0) {
                if (second > mid + 1) {
                    second--;
                } else if (mid > first + 1) {
                    mid--;
                } else {
                    break;
                }
            } else {
                if (first < mid - 1) {
                    first++;
                } else if (mid < second - 1) {
                    mid++;
                } else {
                    break;
                }
            }
        }
        System.out.println(answerA + " " + answerB + " " + answerC);
    }
}
