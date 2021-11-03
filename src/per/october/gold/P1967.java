package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1967 {
    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //노드의 개수
        adjacentList = new ArrayList[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer nodeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(nodeInfo.nextToken());
            int b = Integer.parseInt(nodeInfo.nextToken());
            int value = Integer.parseInt(nodeInfo.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
            adjacentList[a].add(new Edge(b, value));
            adjacentList[b].add(new Edge(a, value));
        }

        dfs(1, new boolean[N + 1], 0);
        dfs(maxNode, new boolean[N + 1], 0);
        System.out.println(max);
    }

    static int max = 0;
    static int maxNode = 0;

    static void dfs(int to, boolean[] visited, int sum) {
        visited[to] = true;
        if (null != adjacentList[to]) {
            for (Edge edge : adjacentList[to]) {
                if (!visited[edge.to]) {
                    dfs(edge.to, visited, sum + edge.value);
                }
            }
            if (max < sum) {
                max = sum;
                maxNode = to;
            }
        }
    }

    static class Edge {
        int to;
        int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }
}
