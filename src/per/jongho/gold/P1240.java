package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1240 {

    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //노드의 개수
        int M = Integer.parseInt(st.nextToken()); //연결된 노드

        adjacentList = new ArrayList[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int weight = Integer.parseInt(input.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
            adjacentList[a].add(new Edge(b, weight));
            adjacentList[b].add(new Edge(a, weight));
        }
        //입력 종료

        while (M-- > 0) {
            length = 0;
            StringTokenizer question = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(question.nextToken());
            int to = Integer.parseInt(question.nextToken());
            dfs(from, to, new boolean[N + 1], 0);
            sb.append(length).append("\n");
        }

        System.out.println(sb);
    }

    static int length = 0;

    static void dfs(int to, int destination, boolean[] visited, int sum) {
        if (to == destination) {
            length = sum;
            return;
        }
        visited[to] = true;
        if (null != adjacentList[to]) {
            for (int i = 0; i < adjacentList[to].size(); i++) {
                Edge edge = adjacentList[to].get(i);
                if (!visited[edge.to]) {
                    dfs(edge.to, destination, visited, sum + edge.weight);
                }
            }
        }
    }

    static class Edge {
        final int to;
        final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
