package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1613 {

    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //사건의 개수
        int k = Integer.parseInt(st.nextToken()); //사건의 전후 관계 개수
        graph = new int[n + 1][n + 1];
        while (k-- > 0) {
            StringTokenizer info = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(info.nextToken());
            int b = Integer.parseInt(info.nextToken());
            //a가 b보다 먼저 일어난 사건이다.
            graph[a][b] = -1;
            graph[b][a] = 1;
        }
        int s = Integer.parseInt(br.readLine());
        solution(n);
        while (s-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            //a가 b보다 먼저 일어났으면 -1, b가 먼저 일어났으면 1,어떤지 모르면 0을 출력
            sb.append(graph[a][b]).append("\n");
        }
        System.out.println(sb);
    }

    static void solution(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if (k == j || i == j) continue;
                    else if (graph[i][j] != 0) continue;
                    else if (graph[i][k] != 0 && graph[i][k] == graph[k][j]) {
                        graph[i][j] = graph[i][k];
                    }
                }
            }
        }
    }
}
