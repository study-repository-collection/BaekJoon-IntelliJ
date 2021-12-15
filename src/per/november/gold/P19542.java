package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P19542 {

    static ArrayList<Integer>[] adjacentList;
    static Stack<Integer> leafNodes = new Stack();
    static int[] parent = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //노드의 개수
        int S = Integer.parseInt(st.nextToken()); // 케니소프트의 위치
        int D = Integer.parseInt(st.nextToken()); //힘

        adjacentList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adjacentList[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            adjacentList[a].add(b);
            adjacentList[b].add(a);
        }
        bfs(S, N);
        boolean[] visited = new boolean[N + 1];
        while (!leafNodes.isEmpty()) {
            toParent(leafNodes.pop(), 0, visited, D, S);
        }
        System.out.println(sum);
    }

    static void toParent(int to, int distance, boolean[] visited, int D, int S) {
        visited[to] = true;
        if (to == S) {
            sum += Math.max(distance - D, 0) * 2;
        } else if (!visited[parent[to]]) {
            toParent(parent[to], distance + 1, visited, D, S);
        } else {
            sum += Math.max((distance + 1) - D, 0) * 2;
        }
    }

    static int sum = 0;

    static void bfs(int start, int N) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                boolean isLeap = false;
                for (int next : adjacentList[temp]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                        parent[next] = temp;
                        isLeap = true;
                    }
                }
                if (!isLeap) {
                    leafNodes.add(temp);
                }
            }
        }
    }
}

