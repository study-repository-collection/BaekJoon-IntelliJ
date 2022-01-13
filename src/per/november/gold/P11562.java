package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11562 {

    static int[][] graph;
    static final int INF = 123456789;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(input.nextToken()); //건물의 수
        int m = Integer.parseInt(input.nextToken()); //길의 수

        graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); //시작 건물
            int v = Integer.parseInt(st.nextToken()); //도착 건물d
            int b = Integer.parseInt(st.nextToken()); //0이면 일방통행, 1이면 양방향통행
            if (b == 0) {
                graph[u][v] = 0;
                graph[v][u] = 1;
            } else {
                graph[u][v] = 0;
                graph[v][u] = 0;
            }
        }
        floyd(n);
        int k = Integer.parseInt(br.readLine()); //질문의 개수
        while (k-- > 0) {
            StringTokenizer question = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(question.nextToken());
            int f = Integer.parseInt(question.nextToken());
            sb.append(solution(s, f)).append("\n");
        }
        System.out.print(sb);
    }


    static void floyd(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if (k == j || i == j) continue;
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    static int solution(int s, int f) {
        return graph[s][f];
    }


}
