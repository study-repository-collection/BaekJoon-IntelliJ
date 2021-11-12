package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15591 {
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//동영상의 개수
        int Q = Integer.parseInt(st.nextToken());//질문의 개수
        adjacentList = new ArrayList[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer UsadoInfo = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(UsadoInfo.nextToken());
            int q = Integer.parseInt(UsadoInfo.nextToken());
            int weight = Integer.parseInt(UsadoInfo.nextToken());
            if (null == adjacentList[p]) adjacentList[p] = new ArrayList<>();
            if (null == adjacentList[q]) adjacentList[q] = new ArrayList<>();
            adjacentList[p].add(new Edge(q, weight));
            adjacentList[q].add(new Edge(p, weight));
        }

        while (Q-- > 0) {
            StringTokenizer Query = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(Query.nextToken());
            int v = Integer.parseInt(Query.nextToken());
            sb.append(solution(k, v, N)).append("\n");
        }
        System.out.println(sb);
    }

    static int solution(int k, int start, int N) {
        int ret = -1;
        boolean[] visited = new boolean[N + 1];
        dfs(start, 1500000000, k, visited);
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                ret++;
            }
        }
        return ret;
    }

    static void dfs(int to, int minUsado, int K, boolean[] visited) {
        visited[to] = true;
        for (Edge edge : adjacentList[to]) {
            int minTemp = Math.min(minUsado, edge.weight);
            if (minTemp >= K && !visited[edge.to]) {
                dfs(edge.to, minUsado, K, visited);
            }
        }
    }


}
