package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P2467 {
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        solution(N);
    }

    public static void solution(int N) {
        int min = Integer.MAX_VALUE;
        int minA = 0;
        int minB = 0;
        int a = 0;
        int b = N - 1;
        while (a < b) {
            int tempA = sequence[a];
            int tempB = sequence[b];

            if (min > Math.abs(tempA + tempB)) {
                minA = tempA;
                minB = tempB;
                min = Math.abs(tempA + tempB);
            }
            if (tempA + tempB > 0) {
                b--;
            } else if (tempA + tempB < 0) {
                a++;
            } else {
                System.out.println(minA + " " + minB);
                return;
            }
        }
        System.out.println(minA + " " + minB);
    }
}
