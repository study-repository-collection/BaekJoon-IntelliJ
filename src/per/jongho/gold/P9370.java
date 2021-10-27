package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P9370 {

    static ArrayList<Edge>[] adjacentList;
    static ArrayList<Integer> guessAnswer;


    static class Edge {
        final int to;
        final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static final int INF = 2000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); //교차로 개수
            int m = Integer.parseInt(st.nextToken()); //도로 개수
            int t = Integer.parseInt(st.nextToken()); //목적지 후보 개수
            StringTokenizer secondInput = new StringTokenizer(br.readLine());

            adjacentList = new ArrayList[n + 1];
            guessAnswer = new ArrayList<>();
            int s = Integer.parseInt(secondInput.nextToken()); //예술가들의 출발지
            int g = Integer.parseInt(secondInput.nextToken()); //지나간 것을 아는 교차로 중 한 점
            int h = Integer.parseInt(secondInput.nextToken()); //지나간 것을 아는 교차로 중 한 점

            while (m-- > 0) {
                StringTokenizer roadInfo = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(roadInfo.nextToken());
                int b = Integer.parseInt(roadInfo.nextToken());
                int d = Integer.parseInt(roadInfo.nextToken());
                if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
                if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
                d *= 2;
                if ((a == g && b == h) || (a == h && b == g)) {
                    d -= 1;
                }
                adjacentList[a].add(new Edge(b, d));
                adjacentList[b].add(new Edge(a, d));
            }
            //교차로 입력 종료
            while (t-- > 0) {
                guessAnswer.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(guessAnswer);
            dijkstra(n, s);
        }
    }

    static void dijkstra(int n, int start) {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dist[o1] - dist[o2];
            }
        });
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            int index = priorityQueue.poll();
            if (visited[index]) continue;
            visited[index] = true;
            if (null != adjacentList[index]) {
                for (Edge edge : adjacentList[index]) {
                    if (dist[edge.to] > dist[index] + edge.weight) {
                        dist[edge.to] = dist[index] + edge.weight;
                        priorityQueue.add(edge.to);
                    }
                }
            }
        }
        for (int city : guessAnswer) {
            if ((dist[city] & 1) == 1) {
                sb.append(city).append(" ");
            }
        }
        System.out.println(sb);
        sb = new StringBuilder();
    }
}
