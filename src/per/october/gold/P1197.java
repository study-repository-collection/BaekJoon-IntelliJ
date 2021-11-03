package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1197 {

    static ArrayList<Edge> edges = new ArrayList<>();

    static class Edge {
        final int a;
        final int b;
        final int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); //정점의 개수
        int E = Integer.parseInt(st.nextToken()); //간선의 개수,

        while (E-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            int weight = Integer.parseInt(edgeInfo.nextToken());
            edges.add(new Edge(a, b, weight));
        }

        sortEdgesByWeight();
        System.out.println(kruskal(V));

    }

    static int find(int a, int[] parent) {
        if (a == parent[a]) return a;
        parent[a] = find(parent[a], parent);
        return parent[a];
    }

    static void union(int a, int b, int[] parent) {
        int aRoot = find(a, parent);
        int bRoot = find(b, parent);
        if (aRoot != bRoot) {
            parent[aRoot] = b;
        } else {
            return;
        }
    }

    static int kruskal(int V) {
        int[] parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;
        int sum = 0;
        for (Edge edge : edges) {
            int a = find(edge.a, parent);
            int b = find(edge.b, parent);
            if (a != b) {
                union(a, b, parent);
                sum += edge.weight;
            }
        }
        return sum;
    }

    /**
     * 간선들을 가중치의 오름차순으로 정렬한다.
     */
    static void sortEdgesByWeight() {
        edges.sort(Comparator.comparingInt(o -> o.weight));
    }


}
