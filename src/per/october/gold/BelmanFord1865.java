package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class BelmanFord1865 {
    static ArrayList<Edge>[] edges;
    static final int INF = 987654321;

    static class Edge {
        final int to;
        final int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer firstInput = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(firstInput.nextToken()); //정점의 개수
            int M = Integer.parseInt(firstInput.nextToken()); //도로의 개수
            int W = Integer.parseInt(firstInput.nextToken()); //웜홀의 개수
            edges = new ArrayList[N + 1];
            while (M-- > 0) {
                //도로 입력 (양방향성)
                StringTokenizer roadInfo = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(roadInfo.nextToken());//시작점
                int E = Integer.parseInt(roadInfo.nextToken());//종료점
                int T = Integer.parseInt(roadInfo.nextToken());//시간
                if (null == edges[S]) edges[S] = new ArrayList<>();
                if (null == edges[E]) edges[E] = new ArrayList<>();
                edges[S].add(new Edge(E, T));
                edges[E].add(new Edge(S, T));
            }
            while (W-- > 0) {
                StringTokenizer wormhole = null;
                try {
                    wormhole = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int S = Integer.parseInt(wormhole.nextToken());
                int E = Integer.parseInt(wormhole.nextToken());
                int T = -1 * Integer.parseInt(wormhole.nextToken());
                if (null == edges[S]) edges[S] = new ArrayList<>();
                edges[S].add(new Edge(E, T));
            }
            sb.append(belmanFord(1, N) ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }

    static boolean belmanFord(int start, int N) {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, INF);
        dp[start] = 0;

        boolean isUpdated;
        for (int i = 1; i <= N; i++) {
            isUpdated = false;
            for (int j = 1; j <= N; j++) {
                if (null != edges[j])
                    for (Edge edge : edges[j]) {
                        if (dp[edge.to] > dp[j] + edge.value) {
                            isUpdated = true;
                            dp[edge.to] = dp[j] + edge.value;
                        }
                    }
            }
            if (i == N && isUpdated) return true; //음수 사이클 발생
        }
        return false;
    }
}
