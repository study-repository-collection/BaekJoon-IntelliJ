package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P6497 {


    static class Edge {
        final int a;
        final int b;
        final int value;

        public Edge(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }
    }

    static PriorityQueue<Edge> edges;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer graphInfo = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(graphInfo.nextToken()); //도시의 개수
            int n = Integer.parseInt(graphInfo.nextToken()); //길의 수
            if (m == 0 && n == 0) break;
            int sum = 0;
            edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
            while (n-- > 0) {
                StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(edgeInfo.nextToken());
                int y = Integer.parseInt(edgeInfo.nextToken());
                int z = Integer.parseInt(edgeInfo.nextToken());
                sum += z;
                edges.add(new Edge(x, y, z));
            }
            System.out.println(solution(m, sum));
        }
    }


    static int find(int a, int[] parent) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a], parent);
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static int solution(int m, int total) {
        int sum = 0;
        int[] parent = new int[m];
        for (int i = 0; i < m; i++) parent[i] = i;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int aRoot = find(edge.a, parent);
            int bRoot = find(edge.b, parent);

            if (aRoot != bRoot) {
                union(edge.a, edge.b, parent);
                sum += edge.value;
            }
        }
        return total - sum;
    }
}
