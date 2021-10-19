package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P9613 {
    static int[] arr = new int[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            for (int i = 0; i < A; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            sb.append(sumOfGCD(A)).append("\n");
        }
        System.out.print(sb);
    }

    static long sumOfGCD(int N) {
        long sum = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int ret = getGCD(Math.max(arr[i], arr[j]), Math.min(arr[i], arr[j]));
                sum += ret;
            }
        }
        return sum;
    }

    static int getGCD(int a, int b) {
        int r = a % b;
        if (r == 0) return b;
        else return getGCD(b, r);
    }
}
