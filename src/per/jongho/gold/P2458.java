package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2458 {
    static int[][] map;
    static final int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //학생들의 수
        int M = Integer.parseInt(st.nextToken()); //키를 비교한 횟수

        map = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) Arrays.fill(map[i], INF);

        for (int i = 0; i < M; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            map[a][b] = 1;
        }
        //입력 종료

        solution(N);
        System.out.println(answer(N));

    }

    static int answer(int N) {
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (map[i][j] != INF || map[j][i] != INF) count++;
            }
            if (count == N - 1) answer++;
        }
        return answer;
    }

    static void solution(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }
    }
}
