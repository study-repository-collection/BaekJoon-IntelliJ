package per.november.codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1 {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(A));

    }

    public static int solution(int[] A) {
        int length = A.length;
        int maxOdd = 0;
        int maxEven = 0;

        for (int i = 0; i < length; i++) {
            if ((A[i] & 1) == 1 && maxOdd < A[i]) {
                maxOdd = A[i];
            } else if ((A[i] & 1) == 0 && maxEven < A[i]) {
                maxEven = A[i];
            }
        }
        return maxOdd + maxEven;
    }
}
