package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2096 {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(input.nextToken());
            map[i][1] = Integer.parseInt(input.nextToken());
            map[i][2] = Integer.parseInt(input.nextToken());
        }
        dynamicProgramming(N);

    }

    static void dynamicProgramming(int N) {
        int[][] maxDp = new int[N][3];
        int[][] minDp = new int[N][3];

        maxDp[0][0] = map[0][0];
        maxDp[0][1] = map[0][1];
        maxDp[0][2] = map[0][2];

        minDp[0][0] = map[0][0];
        minDp[0][1] = map[0][1];
        minDp[0][2] = map[0][2];


        for (int i = 1; i < N; i++) {
            maxDp[i][0] = Math.max(maxDp[i - 1][0] + map[i][0], maxDp[i - 1][1] + map[i][0]);
            maxDp[i][2] = Math.max(maxDp[i - 1][2] + map[i][2], maxDp[i - 1][1] + map[i][2]);

            int numberA = Math.max(maxDp[i - 1][0] + map[i][1], maxDp[i - 1][1] + map[i][1]);
            numberA = Math.max(numberA, maxDp[i - 1][2] + map[i][1]);
            maxDp[i][1] = numberA;
            int numberB = Math.min(minDp[i - 1][0] + map[i][1], minDp[i - 1][1] + map[i][1]);
            numberB = Math.min(numberB, minDp[i - 1][2] + map[i][1]);
            minDp[i][1] = numberB;

            minDp[i][0] = Math.min(minDp[i - 1][0] + map[i][0], minDp[i - 1][1] + map[i][0]);
            minDp[i][2] = Math.min(minDp[i - 1][2] + map[i][2], minDp[i - 1][1] + map[i][2]);
        }

        int max;
        int min;
        max = Math.max(maxDp[N - 1][0], maxDp[N - 1][1]);
        max = Math.max(max, maxDp[N - 1][2]);
        min = Math.min(minDp[N - 1][0], minDp[N - 1][1]);
        min = Math.min(min, minDp[N - 1][2]);
        System.out.printf("%d %d", max, min);
    }
}
