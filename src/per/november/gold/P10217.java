package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.util.*;

import static java.lang.System.in;

public class P10217 {


    static class Edge {
        final int to;
        final int price;
        final int time;

        public Edge(int to, int price, int time) {
            this.to = to;
            this.price = price;
            this.time = time;
        }
    }

    static ArrayList<Edge>[] adjacentList;

    static final String CANT_REACH = "Poor KCM";

    //인천은 언제나 1번도시, LA는 언제나 N번 도시이다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        while (T-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(input.nextToken()); //공항의 수
            int M = Integer.parseInt(input.nextToken()); //총 지원 비용
            int K = Integer.parseInt(input.nextToken()); //티켓정보의 수
            adjacentList = new ArrayList[N + 1];


            while (K-- > 0) {
                StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(edgeInfo.nextToken()); //출발 공항
                int v = Integer.parseInt(edgeInfo.nextToken()); //도착 공항
                int c = Integer.parseInt(edgeInfo.nextToken()); //비용
                int d = Integer.parseInt(edgeInfo.nextToken()); //소요시간
                if (null == adjacentList[u]) adjacentList[u] = new ArrayList<>();
                adjacentList[u].add(new Edge(v, c, d));
            }
            int ret;
            sb.append((ret = dijkstra(1, N, M)) == INF ? CANT_REACH : ret).append("\n");
        }
        System.out.println(sb);
    }


    static final int INF = 987654321;

    static int dijkstra(int start, int N, int M) {
        int[][] dist = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(dist[i], INF);
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
        if (null != adjacentList[start]) {
            for (Edge edge : adjacentList[start]) {
                if (edge.price <= M) {
                    dist[edge.to][edge.price] = edge.time;
                    edges.add(edge);
                }
            }
        }
        dist[start][0] = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (dist[edge.to][edge.price] < edge.time) continue;

            if (null != adjacentList[edge.to]) {
                for (Edge temp : adjacentList[edge.to]) {
                    if (edge.price + temp.price <= M && dist[temp.to][edge.price + temp.price] > dist[edge.to][edge.price] + temp.time) {
                        dist[temp.to][edge.price + temp.price] = dist[edge.to][edge.price] + temp.time;
                        edges.add(new Edge(temp.to, edge.price + temp.price, dist[edge.to][edge.price] + temp.time));
                    }
                }
            }
        }
        int min = INF;
        for (int i = 0; i <= M; i++) {
            min = Math.min(min, dist[N][i]);
        }

        return min;
    }
}
