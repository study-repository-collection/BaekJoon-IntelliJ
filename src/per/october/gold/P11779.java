package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P11779 {

    static ArrayList<Edge>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //도시의 개수
        int M = Integer.parseInt(br.readLine()); //버스의 개수

        adjacentList = new ArrayList[N + 1];

        while (M-- > 0) {
            StringTokenizer bus = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(bus.nextToken());
            int finish = Integer.parseInt(bus.nextToken());
            int value = Integer.parseInt(bus.nextToken());

            if (null == adjacentList[start]) {
                adjacentList[start] = new ArrayList<>();
            }
            adjacentList[start].add(new Edge(finish, value));
        }
        StringTokenizer startFinish = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(startFinish.nextToken());
        int finish = Integer.parseInt(startFinish.nextToken());
        dijkstra(start, finish, N);

    }


    static final long INF = 11109876543210L;

    static void dijkstra(int start, int finish, int N) {
        long[] dijkstra = new long[N + 1];
        boolean[] visited = new boolean[N + 1];
        int[] fromNode = new int[N + 1];
        Arrays.fill(dijkstra, INF);
        fromNode[start] = 0;
        dijkstra[start] = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(o -> dijkstra[o]));
        priorityQueue.add(start);
        while (!priorityQueue.isEmpty()) {
            int index = priorityQueue.poll();
            if (visited[index]) continue;
            visited[index] = true;
            if (null != adjacentList[index]) {
                for (Edge edge : adjacentList[index]) {
                    if (dijkstra[edge.to] > dijkstra[index] + edge.value) {
                        dijkstra[edge.to] = dijkstra[index] + edge.value;
                        if (!visited[edge.to]) {
                            priorityQueue.add(edge.to);
                        }
                        fromNode[edge.to] = index;
                    }
                }
            }
        }
        System.out.println(dijkstra[finish]);
        printRoute(start, finish, fromNode);
    }

    static void printRoute(int start, int finish, int[] route) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        arrayList.add(finish);
        int nextIndex = route[finish];
        while (nextIndex != 0) {
            arrayList.add(nextIndex);
            nextIndex = route[nextIndex];
        }
        sb.append(arrayList.size()).append("\n");
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            sb.append(arrayList.get(i)).append(" ");
        }
        System.out.print(sb);
    }

    static class Edge {
        final int to;
        final int value;

        public Edge(int to, int value) {
            this.to = to;
            this.value = value;
        }
    }
}
