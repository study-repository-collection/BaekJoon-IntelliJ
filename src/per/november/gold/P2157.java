package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P2157 {
    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //도시의 수
        int M = Integer.parseInt(input.nextToken()); //M 개 이하의 도시를 건너야 함
        int K = Integer.parseInt(input.nextToken()); //개설된 항공로의 개수

        adjacentList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        while (K-- > 0) {
            StringTokenizer roadInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(roadInfo.nextToken());
            int b = Integer.parseInt(roadInfo.nextToken());
            int c = Integer.parseInt(roadInfo.nextToken());
            adjacentList[a].add(new Edge(b, c, 0));
        }
        for (int i = 1; i <= N; i++) {
            adjacentList[i].sort(null);
        }

        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        int[][] dp = new int[N + 1][M + 1];
        dp[1][1] = 0;
        Queue<Edge> edges = new LinkedList<>();
        edges.add(new Edge(1, 0, 1));
        while (!edges.isEmpty()) {
            Edge temp = edges.poll();
            if (dp[temp.to][temp.number] > temp.score) {
                continue;
            }
            for (Edge edge : adjacentList[temp.to]) {
                if (edge.to < temp.to) {
                    continue;
                }
                if (temp.number + 1 <= M && dp[edge.to][temp.number + 1] < temp.score + edge.score) {
                    dp[edge.to][temp.number + 1] = temp.score + edge.score;
                    edges.add(new Edge(edge.to, dp[edge.to][temp.number + 1], temp.number + 1));
                }
            }
        }

        int ret = -1;
        for (int i = 2; i <= M; i++) {
            ret = Math.max(ret, dp[N][i]);
        }
        return ret;
    }

    static class Edge implements Comparable<Edge> {

        final int to;
        final int score;
        int number = 0;

        public Edge(int to, int score, int number) {
            this.to = to;
            this.score = score;
            this.number = number;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.score, o.score);
        }
    }
}
