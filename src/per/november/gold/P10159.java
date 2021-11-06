package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P10159 {

    static int[][] graph;

    static final int Heavier = 1;
    static final int light = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //물건의 개수
        int M = Integer.parseInt(br.readLine()); //미리 측정된 물건 쌍의 개수

        graph = new int[N + 1][N + 1];
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 2;
            //a가 b 보다 무겁다

        }
        solution(N);
        printAnswer(N);
    }

    static void solution(int N) {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if (k == j || i == j) continue;
                    if (graph[i][k] == graph[k][j] && graph[i][k] != 0) {
                        graph[i][j] = graph[i][k];
                    }
                }
            }
        }
    }

    static void printAnswer(int N) {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (graph[i][j] == 0) count++;
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
