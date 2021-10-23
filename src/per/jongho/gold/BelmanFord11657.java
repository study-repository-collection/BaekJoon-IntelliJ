package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class BelmanFord11657 {
    static class Edge {
        int to;
        int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }

    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken()); //도시의 개수
        int M = Integer.parseInt(input.nextToken()); //버스의 개수
        adjacentList = new ArrayList[N + 1];
        while (M-- > 0) {
            StringTokenizer road = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(road.nextToken());
            int B = Integer.parseInt(road.nextToken());
            int C = Integer.parseInt(road.nextToken());

            if (null == adjacentList[A]) {
                adjacentList[A] = new ArrayList<>();
            }
            adjacentList[A].add(new Edge(B, C));
        }
        belmanFord(N, 1);
    }

    static final long INF = 1110987654321L;

    static void belmanFord(int N, int start) {
        long[] dist = new long[N + 1];
        //최단 거리 테이블 초기화
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 1; i <= N; i++) {
            //갱신 발생 여부를 저장하는 boolean 변수
            boolean isUpdate = false;
            for (int j = 1; j <= N; j++) {
                //모든 정점에 대해, 연결된 간선이 존재한다면, 해당 정점부터 간선의 도착지까지의 최단 거리를 갱신
                if (null != adjacentList[j]) {
                    for (Edge edge : adjacentList[j]) {
                        //dist[j] 가 INF 면, 시작점에서 j 정점까지의 경로가 아직 없다는 뜻이므로, 갱신을 수행하지 않는다.
                        if (dist[j] != INF && dist[edge.to] > dist[j] + edge.value) {
                            dist[edge.to] = dist[j] + edge.value;
                            isUpdate = true;
                        }
                    }
                }
            }
            //정점의 개수 N 번째 갱신 작업 수행 중 갱신이 발생한 경우 -1 출력
            if (i == N && isUpdate) {
                System.out.println(-1);
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
        }
        System.out.print(sb);
    }
}
