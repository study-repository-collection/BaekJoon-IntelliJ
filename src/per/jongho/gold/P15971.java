package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15971 {
    static ArrayList<Edge>[] adjacentList;


    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //동굴의 개수
        int A = Integer.parseInt(input.nextToken()); //로봇 A의 초기 위치
        int B = Integer.parseInt(input.nextToken()); //로봇 B의 초기 위치
        adjacentList = new ArrayList[N + 1];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer caveInfo = new StringTokenizer(br.readLine());
            int cave1 = Integer.parseInt(caveInfo.nextToken());
            int cave2 = Integer.parseInt(caveInfo.nextToken());
            int weight = Integer.parseInt(caveInfo.nextToken());
            if (null == adjacentList[cave1]) adjacentList[cave1] = new ArrayList<>();
            if (null == adjacentList[cave2]) adjacentList[cave2] = new ArrayList<>();
            Edge edge = new Edge(cave2, weight);
            Edge edge1 = new Edge(cave1, weight);
            adjacentList[cave1].add(edge);
            adjacentList[cave2].add(edge1);
        }
        dfs(A, B, 0, new boolean[N + 1], new ArrayList<>());
        System.out.println(min);
    }

    static void solution(int sum, ArrayList<Integer> list) {
        if (!list.isEmpty()) {
            min = sum - list.stream().max(Integer::compareTo).get();
        } else {
            min = sum;
        }

    }

    static void bfs(int to, int B, int N) {
        boolean[] visited = new boolean[N + 1];
        Queue<Edge> edges = new LinkedList<>();
        for (Edge edge : adjacentList[to]) edges.add(edge);

        while (!edges.isEmpty()) {

        }

    }

    static void dfs(int to, int B, int sum, boolean[] visited, ArrayList<Integer> list) {
        visited[to] = true;
        if (to == B) {
            solution(sum, list);
            return;
        }
        if (adjacentList[to] != null) {
            for (int i = 0; i < adjacentList[to].size(); i++) {
                if (min != Integer.MAX_VALUE) return;
                Edge edge = adjacentList[to].get(i);
                if (!visited[edge.mTo]) {
                    list.add(edge.mWeight);
                    dfs(edge.mTo, B, sum + edge.mWeight, visited, list);
                    list.remove(Integer.valueOf(edge.mWeight));
                }
            }
        }
    }

    static class Edge {
        private final int mTo;
        private final int mWeight;

        public Edge(int to, int weight) {
            mTo = to;
            mWeight = weight;
        }
    }
}
