package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1219 {


    static class Edge {
        final int to;
        int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

    static int[] cityMoney;
    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken()); //도시의수
        int start = Integer.parseInt(input.nextToken()); //시작 도시
        int finish = Integer.parseInt(input.nextToken());//종료 도시
        int M = Integer.parseInt(input.nextToken());//교통 수단의 개수
        adjacentList = new ArrayList[N];
        cityMoney = new int[N];
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            adjacentList[a].add(new Edge(b, -weight));
        }
        StringTokenizer cityMoneyInfo = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cityMoney[i] = Integer.parseInt(cityMoneyInfo.nextToken());
        initEdge(N);
        long result = belmanFord(start, finish, N);
        if (result == INF) {
            System.out.println("gg");
        } else if (result == INF - 1) {
            System.out.println("Gee");
        } else {
            System.out.println(result);
        }
    }

    static final long INF = -121110987654321L;

    static void initEdge(int N) {
        for (int i = 0; i < N; i++) {
            if (null != adjacentList[i]) {
                for (Edge edge : adjacentList[i]) {
                    edge.value += cityMoney[edge.to];
                }
            }
        }
    }

    static long belmanFord(int start, int finish, int N) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = cityMoney[start];

        int cycleVertex = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (null != adjacentList[j]) {
                    for (Edge edge : adjacentList[j]) {
                        if (dist[j] != INF && dist[edge.to] < dist[j] + edge.value) {
                            dist[edge.to] = dist[j] + edge.value;
                            if (i == N - 1) {
                                cycleVertex = j;
                            }
                        }
                    }
                }
            }
            if (i == N - 1) {
                if (dist[finish] == INF)
                    return INF;
                else if (cycleVertex != -1) {
                    dfs(cycleVertex, new boolean[N], finish);
                    if (canReach) return INF - 1;
                }
            }
        }
        return dist[finish];
    }


    static boolean canReach = false;

    static void dfs(int to, boolean[] visited, int finish) {
        if (to == finish) canReach = true;
        visited[to] = true;
        if (null != adjacentList[to]) {
            for (Edge edge : adjacentList[to]) {
                if (!visited[edge.to]) {
                    dfs(edge.to, visited, finish);
                }
            }
        }
    }
}
