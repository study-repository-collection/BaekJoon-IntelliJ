package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14938 {
    static int[] itemNumber;
    static int[][] graph;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //정점의 개수
        int M = Integer.parseInt(input.nextToken()); //수색범위
        int R = Integer.parseInt(input.nextToken()); //간선의 개수

        itemNumber = new int[N + 1];
        graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        StringTokenizer itemNumberInfo = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) itemNumber[i] = Integer.parseInt(itemNumberInfo.nextToken());

        while (R-- > 0) {
            StringTokenizer roadInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(roadInfo.nextToken());
            int b = Integer.parseInt(roadInfo.nextToken());
            int c = Integer.parseInt(roadInfo.nextToken());
            graph[a][b] = c;
            graph[b][a] = c;
        }
        floyid(N);

        int max = -1;
        for (int i = 1; i <= N; i++) {
            max = Math.max(getItemCount(i, N, M), max);
        }
        System.out.println(max);
    }

    static void floyid(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                for (int k = 1; k <= N; k++) {
                    if (j == k || i == k) continue;
                    if (graph[j][k] > graph[j][i] + graph[i][k]) {
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }
    }

    static int getItemCount(int index, int N, int M) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (graph[index][i] <= M) {
                count += itemNumber[i];
            }
        }
        return count;
    }
}
