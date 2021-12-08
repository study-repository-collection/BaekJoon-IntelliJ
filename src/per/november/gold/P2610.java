package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P2610 {
    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //회의에 참석하는 사람의 수
        int M = Integer.parseInt(br.readLine()); //서로 알고 있는 관계의 수
        adjacentList = new ArrayList[N + 1];

        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        while (M-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
            adjacentList[a].add(b);
            adjacentList[b].add(a);
            union(a, b, parent);
        }
        solution(parent, N);
    }

    static int bfs(int start, int N) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int count = -1;
        visited[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                if (null != adjacentList[temp]) {
                    for (int to : adjacentList[temp]) {
                        if (!visited[to]) {
                            visited[to] = true;
                            queue.add(to);
                        }
                    }
                }
            }
            count++;
        }
        return count;
    }

    static void solution(int[] parent, int N) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int root = find(i, parent);
            if (hashMap.containsKey(root)) {
                if (bfs(hashMap.get(root), N) > bfs(i, N)) {
                    hashMap.put(root, i);
                }
            } else {
                hashMap.put(root, i);
            }
        }
        ArrayList<Integer> daepyo = new ArrayList<>();
        for (int value : hashMap.values()) {
            daepyo.add(value);
        }
        daepyo.sort(null);
        sb.append(hashMap.keySet().size()).append("\n");
        for (int value : daepyo) {
            sb.append(value).append("\n");
        }
        System.out.print(sb);
    }

    static int find(int a, int[] parent) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a], parent);
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        if (a == b) return;
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}