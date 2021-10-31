package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1738 {
    static class Edge {
        final int to;
        final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        adjacentList = new ArrayList[n + 1];

        while (m-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            int c = Integer.parseInt(edgeInfo.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            adjacentList[a].add(new Edge(b, c));
        }
        solution(n, 1, n);
    }

    static final int INF = -987654321;

    static void solution(int n, int start, int finish) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        int[] route = new int[n + 1];
        dist[start] = 0;
        ArrayList<Integer> cycleList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean isUpdate = false;
            for (int j = 1; j <= n; j++) {
                if (null != adjacentList[j]) {
                    for (Edge edge : adjacentList[j]) {
                        if (dist[j] != INF && dist[edge.to] < dist[j] + edge.weight) {
                            isUpdate = true;
                            dist[edge.to] = dist[j] + edge.weight;
                            route[edge.to] = j;
                            if (i == n) {
                                cycleList.add(j);
                            }
                        }
                    }
                }
            }
        }
        if (cycleList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Stack<Integer> answer = new Stack<>();
            int routes = finish;
            answer.add(routes);
            while (routes != start) {
                routes = route[routes];
                answer.add(routes);
            }
            while (!answer.isEmpty()) {
                sb.append(answer.pop()).append(" ");
            }
            System.out.println(sb);
        } else {
            for (int value : cycleList) {
                canReach = false;
                canReachFromCycle(value, finish, new boolean[n + 1]);
                if (canReach) {
                    System.out.println(-1);
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            Stack<Integer> answer = new Stack<>();
            int routes = finish;
            answer.add(routes);
            while (routes != start) {
                routes = route[routes];
                answer.add(routes);
            }
            while (!answer.isEmpty()) {
                sb.append(answer.pop()).append(" ");
            }
            System.out.println(sb);
        }
    }

    static boolean canReach = false;

    static void canReachFromCycle(int to, int finish, boolean[] visited) {
        visited[to] = true;
        if (to == finish) canReach = true;
        if (null != adjacentList[to]) {
            for (Edge goTo : adjacentList[to]) {
                if (!visited[goTo.to]) {
                    canReachFromCycle(goTo.to, finish, visited);
                }
            }
        }
    }

}
