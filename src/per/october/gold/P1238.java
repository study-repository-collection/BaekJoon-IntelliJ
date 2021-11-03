package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1238 {
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //학생의 수 , 마을의 수
        int M = Integer.parseInt(input.nextToken()); //단방향 도로
        int X = Integer.parseInt(input.nextToken()); //도착하는 곳
        graph = new int[N + 1][N + 1];
        initGraph(graph, N);

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph[start][end] = time;
        }
        System.out.println(floyid(N, X));
    }


    static int floyid(int N, int X) {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                for (int k = 1; k <= N; k++) {
                    if (j == k || i == k) continue;
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }
        return findAnswer(X, N);
    }

    static final int INF = 987654321;

    static int findAnswer(int X, int N) {
        int max = -1;
        for (int i = 1; i <= N; i++) {
            if (X == i) continue;
            max = Math.max(max, graph[i][X] + graph[X][i]);
        }
        return max;
    }

    static void initGraph(int[][] graph, int N) {
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }
    }
}
