package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1719 {


    static int[][] graph;
    static int[][] route;

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //집하장의 개수
        int M = Integer.parseInt(input.nextToken()); //집하장간 경로의 개수
        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }
        route = new int[N + 1][N + 1];
        while (M-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            int c = Integer.parseInt(edgeInfo.nextToken());
            graph[a][b] = c;
            graph[b][a] = c;
            route[a][b] = b;
            route[b][a] = a;
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
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        //i에서 j로 가는길에 k를 거쳐서 가면 더 빠르다.
                        graph[i][j] = graph[i][k] + graph[k][j];
                        route[i][j] = route[i][k];
                    }
                }
            }
        }
    }

    static void printAnswer(int N) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (route[i][j] != 0) {
                    sb.append(route[i][j]).append(" ");
                } else {
                    sb.append("- ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
