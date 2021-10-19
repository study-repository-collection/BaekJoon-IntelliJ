package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14889 {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i + 1][j + 1] = Integer.parseInt(st.nextToken());
            }
        }
        makeCombination(1, N, 0, new int[N / 2 + 1], new boolean[N + 1]);
        System.out.println(min);
    }

    static int min = Integer.MAX_VALUE;

    public static void makeCombination(int index, int N, int count, int[] teamA, boolean[] use) {

        if (count == N / 2) {
            int[] teamB = new int[N / 2 + 1];
            int teamBIndex = 1;
            for (int i = 1; i <= N; i++) {
                if (!use[i]) teamB[teamBIndex++] = i;
            }
            min = Math.min(min, calculate(teamA, teamB, N));
            return;
        }
        if (index == N + 1) {
            return;
        }
        use[index] = true;
        teamA[++count] = index;
        makeCombination(index + 1, N, count, teamA, use);
        use[index] = false;
        makeCombination(index + 1, N, count - 1, teamA, use);
    }

    public static int calculate(int[] teamA, int[] teamB, int N) {
        int sumA = 0;
        for (int i = 1; i <= N / 2; i++) {
            for (int j = 1; j <= N / 2; j++) {
                sumA += map[teamA[i]][teamA[j]];
            }
        }
        int sumB = 0;
        for (int i = 1; i <= N / 2; i++) {
            for (int j = 1; j <= N / 2; j++) {
                sumB += map[teamB[i]][teamB[j]];
            }
        }
        return Math.abs(sumA - sumB);
    }
}
