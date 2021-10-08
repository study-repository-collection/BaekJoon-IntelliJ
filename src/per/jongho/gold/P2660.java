package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

import static java.lang.System.in;

public class P2660 {
    static int[][] map;
    static final int MAX = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(map[i], MAX);
        for (int i = 1; i <= N; i++) map[i][i] = 0;
        while (true) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            if (a == -1 && b == -1) break;
            map[a][b] = 1;
            map[b][a] = 1;
        }
        solution(N);
        int[] person = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int max = -1;
            for (int j = 1; j <= N; j++) {
                max = Math.max(map[i][j], max);
            }
            person[i] = max;
        }
        person[0] = Integer.MAX_VALUE;
        int min = Arrays.stream(person).min().getAsInt();
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= N; i++) if (person[i] == min) answer.add(i);
        sb.append(min).append(" ").append(answer.size()).append("\n");
        for (int i = 0; i < answer.size(); i++) sb.append(answer.get(i)).append(" ");
        System.out.println(sb);
    }

    static void solution(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                for (int k = 1; k <= N; k++) {
                    if (i == k || j == k) continue;
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }
    }
}
