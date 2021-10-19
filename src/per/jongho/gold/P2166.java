package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2166 {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }
        arr[N][0] = arr[0][0];
        arr[N][1] = arr[0][1];
        long sum = 0;
        long sum2 = 0;
        for (int i = 0; i < N; i++) {
            sum += (long) arr[i][0] * arr[i + 1][1];
            sum2 += (long) arr[i][1] * arr[i + 1][0];
        }
        sum2 -= sum;

        System.out.printf("%.1f", Math.abs((double) sum2 / 2));
    }
}
