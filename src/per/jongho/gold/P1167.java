package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1167 {

    static ArrayList<Node>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //정점의 개수
        adjacentList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            adjacentList[index] = new ArrayList<>();
            while (true) {
                int A = Integer.parseInt(st.nextToken());
                if (A == -1) break;
                int B = Integer.parseInt(st.nextToken());
                Node node = new Node(A, B);
                adjacentList[index].add(node);
            }
        }
        //입력 종료
        dfs(1, 0, new boolean[N + 1]);
        max = -1;
        dfs(maxNode, 0, new boolean[N + 1]);
        System.out.println(max);

    }

    static int maxNode = -1;
    static int max = -1;

    static void dfs(int to, int sum, boolean[] visited) {
        visited[to] = true;
        if (max < sum) {
            max = sum;
            maxNode = to;
        }
        if (!adjacentList[to].isEmpty()) {
            for (int i = 0; i < adjacentList[to].size(); i++) {
                Node node = adjacentList[to].get(i);
                if (!visited[node.to]) {
                    dfs(node.to, sum + node.weight, visited);
                }
            }
        }
    }

    static class Node {
        final int to;
        final int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
