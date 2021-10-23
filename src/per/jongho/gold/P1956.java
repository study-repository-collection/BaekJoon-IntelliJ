package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1956 {
    static int[][] dist;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        dist = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], INF);
        }
        while (E-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(edgeInfo.nextToken());
            int finish = Integer.parseInt(edgeInfo.nextToken());
            int weight = Integer.parseInt(edgeInfo.nextToken());
            dist[start][finish] = weight;
        }
        floyid(V);
        System.out.println(findAnswer(V));
    }


    static void floyid(int V) {
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) continue;
                for (int k = 1; k <= V; k++) {
                    if (i == k || j == k) continue;
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }
    }

    static int findAnswer(int V) {
        int min = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) continue;
                min = Math.min(min, dist[i][j] + dist[j][i]);
            }
        }
        return min == INF ? -1 : min;
    }
}
