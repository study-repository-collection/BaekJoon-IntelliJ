package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P16940 {
    static ArrayList<Integer>[] adjacentList;
    static int[] priority;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        adjacentList = new ArrayList[N + 1];
        priority = new int[N + 1];
        for (int i = 1; i <= N; i++) adjacentList[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            adjacentList[a].add(b);
            adjacentList[b].add(a);
        }
        StringTokenizer sequenceInfo = new StringTokenizer(br.readLine());
        int[] sequence = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(sequenceInfo.nextToken());
            priority[sequence[i]] = i;
        }
        for (int i = 1; i <= N; i++) {
            adjacentList[i].sort(Comparator.comparingInt(o -> priority[o]));
        }
        System.out.println(solution(N, sequence) ? "1" : "0");
    }


    static boolean solution(int N, int[] sequence) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        queue.add(1);
        int index = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                if (sequence[index++] != temp) {
                    return false;
                }
                for (int to : adjacentList[temp]) {
                    if (!visited[to]) {
                        visited[to] = true;
                        queue.add(to);
                    }
                }
            }
        }
        return true;
    }

    static class Edge {
        int to;
        int priority;

        public Edge(int to, int priority) {
            this.to = to;
            this.priority = priority;
        }
    }
}
